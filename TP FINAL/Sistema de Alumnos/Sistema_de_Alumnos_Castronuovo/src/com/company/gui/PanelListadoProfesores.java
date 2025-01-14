package com.company.gui;

import com.company.entidades.Profesor;
import com.company.service.ProfesorService;
import com.company.service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListadoProfesores extends JPanel {


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

    public void armarPanelListadoProfesores(final PanelManager panelManager){

        this.panelManager = panelManager;

        setLayout(new BorderLayout());

        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);

        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("NOMBRE");
        contenidoTabla.addColumn("APELLIDO");


        jlabelTituloListado = new JLabel("Listado de Profesores");
        jlabelTituloListado.setFont(new Font("Calibri", Font.BOLD, 30));

        panelTituloListado = new JPanel();

        panelTituloListado.add(jlabelTituloListado);

        add(panelTituloListado, BorderLayout.NORTH);





        final ProfesorService profesorService = new ProfesorService();

        ArrayList profesores = null;

        try {

            profesores = profesorService.buscarTodos();

        }
        catch (ServiceException e1){

            JOptionPane.showMessageDialog(null, "Se ha producido un error al traer a los profesores registrados: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }


        for (Object obj: profesores){
            Profesor profesor = (Profesor) obj;
            Object [] row = new Object[3];
            row[0] = profesor.getId();
            row[1] = profesor.getNombre();
            row[2] = profesor.getApellido();
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
                ProfesorService profesorService1 =  new ProfesorService();

                int idEliminar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                try {
                    profesorService1.eliminarProfesor(idEliminar);
                    refrescarListado();
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al eliminar a un profesor registrado: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }



            }
        });


        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarFormularioProfesor();

            }
        });


        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                int idModificar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                ProfesorService profesorService1 = new ProfesorService();

                Profesor profesor = null;

                try {
                    profesor = profesorService1.buscarProfesor(idModificar);
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al intentar modificar a un profesor registrado: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarFormularioProfesor(profesor);

            }




        });






    }

    public void refrescarListado(){

        removeAll();
        armarPanelListadoProfesores(panelManager);
        validate();
        repaint();


    }

}
