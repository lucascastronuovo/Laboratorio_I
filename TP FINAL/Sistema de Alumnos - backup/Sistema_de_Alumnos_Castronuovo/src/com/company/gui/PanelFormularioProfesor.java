package com.company.gui;

import com.company.entidades.Profesor;
import com.company.service.ProfesorService;
import com.company.service.ServiceException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioProfesor extends JPanel {



    private JPanel panelComponentes;
    private JPanel panelBotonera;
    private JPanel panelTitulo;


    JLabel jlabelId;
    JLabel jlabelNombre;
    JLabel jlabelApellido;
    JTextField jTextFieldId;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;


    JButton jButtonGuardar;
    JButton jButtonCancelar;


    JLabel jlabelTitulo;


    public void armarPanelFormularioProfesor(){

        jlabelId = new JLabel(" ID: ");
        jTextFieldId = new JTextField(10);

        jlabelNombre = new JLabel(" Nombre: ");
        jTextFieldNombre = new JTextField(40);

        jlabelApellido = new JLabel(" Apellido: ");
        jTextFieldApellido = new JTextField(40);



        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(3,2));

        jlabelId.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelNombre.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelApellido.setFont(new Font("Calibri", Font.BOLD, 20));

        panelComponentes.add(jlabelId);
        panelComponentes.add(jTextFieldId);

        panelComponentes.add(jlabelNombre);
        panelComponentes.add(jTextFieldNombre);

        panelComponentes.add(jlabelApellido);
        panelComponentes.add(jTextFieldApellido);


        setLayout(new BorderLayout());

        add(panelComponentes, BorderLayout.CENTER);



        panelBotonera = new JPanel();


        jButtonCancelar = new JButton("Cancelar");
        jButtonGuardar = new JButton("Guardar");

        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);


        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Profesor profesor = new  Profesor();

                profesor.setId(Integer.parseInt(jTextFieldId.getText()));
                profesor.setNombre(jTextFieldNombre.getText());
                profesor.setApellido(jTextFieldApellido.getText());

                ProfesorService profesorService = new ProfesorService();

                try {
                    profesorService.guardarProfesor(profesor);
                    JOptionPane.showMessageDialog(null, "Se ha guardado el profesor correctamente", "PROFESOR GUARDADO", JOptionPane.INFORMATION_MESSAGE);
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "No se ha podido guardar al profesor correctamente: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }


            }
        });


        jlabelTitulo = new JLabel("Formulario de Profesores");
        jlabelTitulo.setFont(new Font("Calibri",Font.BOLD, 30));

        panelTitulo = new JPanel();



        panelTitulo.add(jlabelTitulo);

        add(panelTitulo, BorderLayout.NORTH);



    }

}
