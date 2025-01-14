package com.company.gui;

import com.company.entidades.Materia;
import com.company.service.MateriaService;
import com.company.service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListadoMaterias extends JPanel{
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


    public void armarPanelListadoMaterias(final PanelManager panelManager){
        this.panelManager = panelManager;

        setLayout(new BorderLayout());

        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);

        contenidoTabla.addColumn("COD_MATERIA");
        contenidoTabla.addColumn("NOMBRE");



        jlabelTituloListado = new JLabel("Listado de Materias");
        jlabelTituloListado.setFont(new Font("Calibri", Font.BOLD, 30));

        panelTituloListado = new JPanel();

        panelTituloListado.add(jlabelTituloListado);

        add(panelTituloListado, BorderLayout.NORTH);





        final MateriaService materiaService = new MateriaService();

        ArrayList materias = null;

        try {

            materias = materiaService.buscarTodas();

        }
        catch (ServiceException e1){

            JOptionPane.showMessageDialog(null, "Se ha producido un error al traer las materias registradas: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }


        for (Object obj: materias){
            Materia materia = (Materia) obj;
            Object [] row = new Object[2];
            row[0] = materia.getCodMateria();
            row[1] = materia.getNombreMateria();

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
                MateriaService materiaService1 =  new MateriaService();

                int codMateriaEliminar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                try {
                    materiaService.eliminarMateria(codMateriaEliminar);
                    refrescarListado();
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al eliminar a una materia registrada: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }



            }
        });


        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarFormularioMateria();

            }
        });


        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                int codMateriaModificar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                MateriaService materiaService1= new MateriaService();

                Materia materia = null;

                try {
                    materia = materiaService1.buscarMateria(codMateriaModificar);
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al intentar modificar una materia registrada: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarFormularioMateria(materia);

            }




        });







    }

    public void refrescarListado(){

        removeAll();
        armarPanelListadoMaterias(panelManager);
        validate();
        repaint();


    }
}
