package com.company.gui;

import com.company.entidades.Cursada;
import com.company.service.CursadaService;
import com.company.service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAsignacionEstudiante extends JPanel {
    private JPanel panelComponentes;
    private JPanel panelBotonera;
    private JPanel panelTituloAsignacionEstudiante;



    JLabel jlabelCodCursada;
    JLabel jlabelLegajo;
    JLabel jlabelNombre;
    JLabel jlabelApellido;
    JTextField jTextFieldCodCursada;
    JTextField jTextFieldLegajo;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;


    JButton jButtonAsignar;
    JButton jButtonCancelar;


    JLabel jlabelTituloAsignacionEstudiante;

    //PanelManager
    PanelManager panelManager;


    public void armarPanelAsignacionEstudiante(Cursada cursada){

        jTextFieldCodCursada.setText(String.valueOf(cursada.getCodCursada()));





    }


    public void vaciarComponentes(){
        jTextFieldCodCursada.setText("");
        jTextFieldLegajo.setText("");
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");

        //modificacion = false;
    }

    public void armarPanelAsignacionEstudiante(final PanelManager panelManager){

        this.panelManager = panelManager;

        jlabelLegajo = new JLabel(" ID: ");
        jTextFieldLegajo = new JTextField(10);

        jlabelNombre = new JLabel(" Nombre: ");
        jTextFieldNombre = new JTextField(40);

        jlabelApellido = new JLabel(" Apellido: ");
        jTextFieldApellido = new JTextField(40);



        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(3,2));

        jlabelLegajo.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelNombre.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelApellido.setFont(new Font("Calibri", Font.BOLD, 20));

        panelComponentes.add(jlabelLegajo);
        panelComponentes.add(jTextFieldLegajo);

        panelComponentes.add(jlabelNombre);
        panelComponentes.add(jTextFieldNombre);

        panelComponentes.add(jlabelApellido);
        panelComponentes.add(jTextFieldApellido);


        setLayout(new BorderLayout());

        add(panelComponentes, BorderLayout.CENTER);



        panelBotonera = new JPanel();


        jButtonCancelar = new JButton("Cancelar");
        jButtonAsignar = new JButton("Asignar");

        panelBotonera.add(jButtonAsignar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);


        jlabelTituloAsignacionEstudiante = new JLabel("Asignar Estudiante");
        jlabelTituloAsignacionEstudiante.setFont(new Font("Calibri",Font.BOLD, 30));

        panelTituloAsignacionEstudiante = new JPanel();



        panelTituloAsignacionEstudiante.add(jlabelTituloAsignacionEstudiante);

        add(panelTituloAsignacionEstudiante, BorderLayout.NORTH);

        jButtonAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Cursada cursada = new Cursada();

                cursada.getEstudiante().setLegajo(Integer.parseInt(jTextFieldLegajo.getText()));
                cursada.getEstudiante().setNombre(jTextFieldApellido.getText());
                cursada.getEstudiante().setApellido(jTextFieldNombre.getText());


                CursadaService cursadaService = new CursadaService();

                try {

                    cursadaService.asignarAlumno(cursada);
                    JOptionPane.showMessageDialog(null, "El estudiante ha sido asignado correctamente", "PROFESOR GUARDADO", JOptionPane.INFORMATION_MESSAGE);


                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "No se ha podido asignar al estudiante correctamente: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarListadoCursadas();
            }
        });






        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarListadoProfesores();
            }
        });

    }

}


