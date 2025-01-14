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

public class VentanaRegister extends JPanel {
    PanelManager panelManager;

    //panel 1
    JPanel panel1;
    private  JLabel labelTitulo;

    //panel 2

    JPanel panel2;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel typeLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JComboBox typeBox;

    //
    JPanel panel3;
    private JButton botonCancelar;
    private  JButton botonRegistrar;


    public void armarVentanaRegister(PanelManager panelManager) {
        this.panelManager = panelManager;


        //panel1

        labelTitulo = new JLabel("Registrarse");
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);

        panel1 = new JPanel();
        panel1.add(labelTitulo);
        panel1.setBackground(Color.RED);

        //panel2

        typeLabel = new JLabel("Tipo");
        typeLabel.setForeground(Color.WHITE);
        userLabel = new JLabel("Nombre de usuario");
        userLabel.setForeground(Color.WHITE);
        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setForeground(Color.WHITE);

        typeBox = new JComboBox();
        typeBox.addItem("ADMINISTRADOR");
        typeBox.addItem("VENDEDOR");
        typeBox.setBackground(new Color(151, 160, 202 ));


        userField = new JTextField(20);
        userField.setBackground(new Color(151, 160, 202 ));


        passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(151, 160, 202 ));



        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,2));

        panel2.add(typeLabel);
        panel2.add(typeBox);
        panel2.add(userLabel);
        panel2.add(userField);
        panel2.add(passwordLabel);
        panel2.add(passwordField);

        panel2.setBackground(Color.BLUE);

        //panel3

        botonCancelar = new JButton("Cancelar");
        botonCancelar.setForeground(Color.WHITE);
        botonCancelar.setBackground(Color.RED);

        botonRegistrar = new JButton("Registrarse");
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setBackground(Color.RED);

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(botonCancelar);
        panel3.add(botonRegistrar);

        panel3.setBackground(new Color(0, 28, 167));

        //panel principal
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userField.setText("");
                passwordField.setText("");

                panelManager.mostrarLoginRegister();

                }
        });

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Usuario usuario = new Usuario(userField.getText(),String.valueOf(passwordField.getPassword()));
                usuario.setTipo((String)typeBox.getSelectedItem());

                if(userField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Ingrese un nombre de usuario o contraseña válidos", "Error", JOptionPane.ERROR_MESSAGE);

                }

                if(usuario != null || (usuario.getUsuario() != "")&& (usuario.getPassword() != "")){
                    if(usuario.getTipo() == "administrador"){
                        Administrador userAdmin = new Administrador(usuario.getUsuario(),usuario.getPassword());
                        panelManager.mostrarVentanaEventos(userAdmin);
                    } else{
                        Vendedor userVendedor = new Vendedor(usuario.getUsuario(),usuario.getPassword());
                    }

                } else{
                    JOptionPane.showMessageDialog(null,"Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
                }

                UsuarioService usuarioService = new UsuarioService();

                try {
                    usuarioService.guardarAUsuario(usuario);
                } catch (ServiceException ex) {
                    //throw new RuntimeException(ex);
                    JOptionPane.showMessageDialog(null,"Error al registrar" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);
    }

    public void refrezcar(PanelManager panelManager){
        removeAll();
        armarVentanaRegister(panelManager);
        validate();
        repaint();

    }

}
