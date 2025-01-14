package GUI;

import Clases.Administrador;
import Clases.Usuario;
import Clases.Vendedor;
import Exceptions.ServiceException;
import Services.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginRegister extends  JPanel{
    Usuario usuario;
    PanelManager panelManager;
    //frame
    //parte central
    JPanel panelCentral;
    JLabel userLabel;
    JTextField userField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    //parte inferior
    private JPanel botonera;
    JButton button1;
    JButton button2;


    public void armarPantallaLogueo(PanelManager panelManager){
        this.panelManager = panelManager;

        //panel2
        userLabel = new JLabel("Nombre de usuario");
        userField = new JTextField(20);

        passwordLabel = new JLabel("Contraseña");
        passwordField = new JPasswordField(20);

        panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout());
        panelCentral.add(userLabel);
        panelCentral.add(userField);
        panelCentral.add(passwordLabel);
        panelCentral.add(passwordField);

        //layoutForm.putConstraint(SpringLayout.VERTICAL_CENTER,userLabel,6,SpringLayout.VERTICAL_CENTER,userField);
        //layoutForm.putConstraint(SpringLayout.VERTICAL_CENTER,passwordLabel,6,SpringLayout.VERTICAL_CENTER,passwordField);

        //panel 3
        button1 = new JButton("Registrarse");
        button2 = new JButton("Iniciar sesión");

        button1.setBackground(Color.CYAN);
        button2.setBackground(Color.CYAN);

        botonera = new JPanel();
        botonera.setLayout(new FlowLayout());
        botonera.add(button1);
        botonera.add(button2);

        //panel principal
        setLayout(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);


        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //VentanaRegister ventanaRegister = new VentanaRegister();
                //ventanaRegister.armarVentanaRegister(panelManager);
                panelManager.mostrarVentanaRegister();


            }
        });

        button2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    UsuarioService usuarioService = new UsuarioService();
                    usuario = usuarioService.buscarUsuario(userField.getText(), String.valueOf(passwordField.getPassword()));

                    if (usuario != null) {

                        if(usuario.getTipo().equals("administrador")){
                            Administrador userAdmin = new Administrador(usuario.getUsuario(),usuario.getPassword());
                            panelManager.mostrarVentanaEventos(userAdmin);
                        } else if (usuario.getTipo().equals("vendedor")){
                            Vendedor userVendedor  = new Vendedor(usuario.getUsuario(),usuario.getPassword());
                            panelManager.mostrarVentanaEventosVend(userVendedor);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null,"Ingrese un nombre de usuario o contraseña válidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
    }

    public void refrezcar(PanelManager panelManager){
        removeAll();
        armarPantallaLogueo(panelManager);
        validate();
        repaint();

    }


}
