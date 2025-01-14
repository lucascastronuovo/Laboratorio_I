package com.company.gui;

import com.company.entidades.Estudiante;
import com.company.service.EstudianteService;
import com.company.service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListadoEstudiantes extends JPanel {

    private JTable jtable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;



    JButton jButtonEliminar;
    JButton jButtonModificar;
    JButton jButtonNuevo;
    private JPanel panelBotonera;

    private JPanel panelTituloListado;


    JLabel jlabelTituloListado;


    //PanelManager
    private PanelManager panelManager;

    public void armarPanelListadoEstudiantes(final PanelManager panelManager){

        this.panelManager = panelManager;

        setLayout(new BorderLayout());

        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);

        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("NOMBRE");
        contenidoTabla.addColumn("APELLIDO");
        contenidoTabla.addColumn("CANTIDAD MÁXIMA DE CURSOS");
        contenidoTabla.addColumn("DINERO");


        jlabelTituloListado = new JLabel("Listado de Estudiantes");
        jlabelTituloListado.setFont(new Font("Calibri", Font.BOLD, 30));

        panelTituloListado = new JPanel();

        panelTituloListado.add(jlabelTituloListado);

        add(panelTituloListado, BorderLayout.NORTH);





        final EstudianteService estudianteService = new EstudianteService();

        ArrayList estudiantes = null;

        try {

            estudiantes = estudianteService.buscarTodos();

        }
        catch (ServiceException e1){

            JOptionPane.showMessageDialog(null, "Se ha producido un error al traer a los estudiantes registrados: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }


        for (Object obj: estudiantes){
            Estudiante estudiante = (Estudiante) obj;
            Object [] row = new Object[5];
            row[0] = estudiante.getLegajo();
            row[1] = estudiante.getNombre();
            row[2] = estudiante.getApellido();
            row[3] = estudiante.getCantMaxCursos();
            row[4] = estudiante.getDinero();
            contenidoTabla.addRow(row);
        }


        panelBotonera = new JPanel();
        jButtonEliminar = new JButton("Eliminar");
        jButtonModificar = new JButton("Modificar");
        jButtonNuevo = new JButton("Nuevo");

        panelBotonera.add(jButtonEliminar);
        panelBotonera.add(jButtonModificar);
        panelBotonera.add(jButtonNuevo);


        add(scrollPane, BorderLayout.CENTER);
        add(panelBotonera, BorderLayout.SOUTH);



        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteService estudianteService1 = new EstudianteService();

                int legajoEliminar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                try {
                    estudianteService1.eliminarEstudiante(legajoEliminar);
                    refrescarListado();
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al eliminar a un estudiante registrado: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }



            }
        });


        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarFormularioEstudiante();

            }
        });


        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                int legajoModificar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                EstudianteService estudianteService1 = new EstudianteService();

               Estudiante estudiante = null;

                try {
                    estudiante = estudianteService1.buscarEstudiante(legajoModificar);
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al intentar modificar a un estudiante registrado: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarFormularioEstudiante(estudiante);

            }




        });






    }

    public void refrescarListado(){

        removeAll();
        armarPanelListadoEstudiantes(panelManager);
        validate();
        repaint();


    }

}

