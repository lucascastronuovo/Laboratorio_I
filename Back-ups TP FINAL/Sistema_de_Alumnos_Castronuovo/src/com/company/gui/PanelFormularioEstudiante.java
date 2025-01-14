package com.company.gui;

import com.company.entidades.Estudiante;
import com.company.service.EstudianteService;
import com.company.service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioEstudiante extends JPanel {


    private JPanel panelComponentes;
    private JPanel panelBotonera;
    private JPanel panelTituloFormulario;


    JLabel jlabelLegajo;
    JLabel jlabelNombre;
    JLabel jlabelApellido;
    JLabel jlabelCantMaxCursos;
    JLabel jlabelDinero;


    JTextField jTextFieldLegajo;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;
    JTextField jTextFieldCantMaxCursos;
    JTextField jTextFieldDinero;


    JButton jButtonGuardar;
    JButton jButtonCancelar;


    JLabel jlabelTituloFormulario;

    //PanelManager
    PanelManager panelManager;

    private boolean modificacion;

    public void armarPanelFormularioEstudiante(Estudiante estudiante){

        jTextFieldLegajo.setText(String.valueOf(estudiante.getLegajo()));
        jTextFieldNombre.setText(estudiante.getNombre());
        jTextFieldApellido.setText(estudiante.getApellido());
        jTextFieldCantMaxCursos.setText(String.valueOf(estudiante.getCantMaxCursos()));
        jTextFieldDinero.setText(String.valueOf(estudiante.getDinero()));


        modificacion = true;



    }


    public void vaciarComponentes(){
        jTextFieldLegajo.setText("");
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");
        jTextFieldCantMaxCursos.setText("");
        jTextFieldDinero.setText("");

        modificacion = false;
    }

    public void armarPanelFormularioEstudiante(final PanelManager panelManager){

        this.panelManager = panelManager;

        jlabelLegajo = new JLabel(" Legajo: ");
        jTextFieldLegajo = new JTextField(10);

        jlabelNombre = new JLabel(" Nombre: ");
        jTextFieldNombre = new JTextField(40);

        jlabelApellido = new JLabel(" Apellido: ");
        jTextFieldApellido = new JTextField(40);

        jlabelCantMaxCursos = new JLabel(" Cantidad Máxima de Cursos: ");
        jTextFieldCantMaxCursos = new JTextField(10);

        jlabelDinero = new JLabel(" Dinero: ");
        jTextFieldDinero = new JTextField(10);



        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(3,2));

        jlabelLegajo.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelNombre.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelApellido.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelCantMaxCursos.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelDinero.setFont(new Font("Calibri", Font.BOLD, 20));

        panelComponentes.add(jlabelLegajo);
        panelComponentes.add(jTextFieldLegajo);

        panelComponentes.add(jlabelNombre);
        panelComponentes.add(jTextFieldNombre);

        panelComponentes.add(jlabelApellido);
        panelComponentes.add(jTextFieldApellido);

        panelComponentes.add(jlabelCantMaxCursos);
        panelComponentes.add(jTextFieldCantMaxCursos);

        panelComponentes.add(jlabelDinero);
        panelComponentes.add(jTextFieldDinero);


        setLayout(new BorderLayout());

        add(panelComponentes, BorderLayout.CENTER);



        panelBotonera = new JPanel();


        jButtonCancelar = new JButton("Cancelar");
        jButtonGuardar = new JButton("Guardar");

        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);


        jlabelTituloFormulario = new JLabel("Formulario de Estudiantes");
        jlabelTituloFormulario.setFont(new Font("Calibri",Font.BOLD, 30));

        panelTituloFormulario = new JPanel();



        panelTituloFormulario.add(jlabelTituloFormulario);

        add(panelTituloFormulario, BorderLayout.NORTH);

        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Estudiante estudiante = new Estudiante();

                estudiante.setLegajo(Integer.parseInt(jTextFieldLegajo.getText()));
                estudiante.setNombre(jTextFieldNombre.getText());
                estudiante.setApellido(jTextFieldApellido.getText());
                estudiante.setCantMaxCursos(Integer.parseInt(jTextFieldCantMaxCursos.getText()));
                estudiante.setDinero(Float.parseFloat(jTextFieldDinero.getText()));

                EstudianteService estudianteService = new EstudianteService();

                try {

                    if(modificacion) {
                        estudianteService.modificarEstudiante(estudiante);
                        JOptionPane.showMessageDialog(null, "El estudiante ha sido modificado correctamente", "ESTUDIANTE GUARDADO", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        estudianteService.guardarEstudiante(estudiante);
                        JOptionPane.showMessageDialog(null, "Se ha guardado el estudiante correctamente", "ESTUDIANTE GUARDADO", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "No se ha podido guardar al estudiante correctamente: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarListadoEstudiantes();
            }
        });






        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarListadoEstudiantes();
            }
        });

    }

}
