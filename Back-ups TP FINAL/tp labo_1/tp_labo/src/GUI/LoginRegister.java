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

    //parte superior
    private JPanel panelSuperior;
    JLabel tituloLabel;

    //parte central
    private JPanel panelCentral;
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

        //panel1
        tituloLabel = new JLabel("Iniciar Sesión");
        tituloLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        tituloLabel.setForeground(Color.WHITE);

        panelSuperior = new JPanel();
        panelSuperior.add(tituloLabel);

        panelSuperior.setBackground(Color.RED);

        //panel2
        userLabel = new JLabel("       Nombre de usuario");
        userLabel.setForeground(Color.WHITE);
        userField = new JTextField(20);
        userField.setBackground(new Color(151, 160, 202 ));


        passwordLabel = new JLabel("       Contraseña");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(151, 160, 202 ));


        panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(2,2));
        panelCentral.add(userLabel);
        panelCentral.add(userField);
        panelCentral.add(passwordLabel);
        panelCentral.add(passwordField);

        panelCentral.setBackground(Color.BLUE);

        //layoutForm.putConstraint(SpringLayout.VERTICAL_CENTER,userLabel,6,SpringLayout.VERTICAL_CENTER,userField);
        //layoutForm.putConstraint(SpringLayout.VERTICAL_CENTER,passwordLabel,6,SpringLayout.VERTICAL_CENTER,passwordField);

        //panel 3
        button1 = new JButton("Registrarse");
        button2 = new JButton("Iniciar sesión");

        button1.setBackground(Color.RED);
        button1.setForeground(Color.WHITE);
        button2.setBackground(Color.RED);
        button2.setForeground(Color.WHITE);

        botonera = new JPanel();
        botonera.setLayout(new FlowLayout());
        botonera.add(button2);
        botonera.add(button1);

        botonera.setBackground(new Color(0, 28, 167));


        //panel principal
        setLayout(new BorderLayout());
        add(panelSuperior,BorderLayout.NORTH);
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
