package com.company.gui;

import com.company.entidades.Cursada;
import com.company.service.CursadaService;
import com.company.service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListadoCursadas extends JPanel{

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

    public void armarPanelListadoCursadas(final PanelManager panelManager){

        this.panelManager = panelManager;

        setLayout(new BorderLayout());

        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);

        contenidoTabla.addColumn("CÓDIGO DE CURSADA");
        contenidoTabla.addColumn("CUPO MÁXIMO");
        contenidoTabla.addColumn("LEGAJO DEL ESTUDIANTE");
        contenidoTabla.addColumn("NOMBRE DEL ESTUDIANTE");
        contenidoTabla.addColumn("APELLIDO DEL ESTUDIANTE");
        contenidoTabla.addColumn("PRECIO");
        contenidoTabla.addColumn("ID DEL PROFESOR");
        contenidoTabla.addColumn("NOMBRE DEL PROFESOR");
        contenidoTabla.addColumn("APELLIDO DEL PROFESOR");
        contenidoTabla.addColumn("CÓDIGO DE LA MATERIA");
        contenidoTabla.addColumn("NOMBRE DE LA MATERIA");
        contenidoTabla.addColumn("NOTA PARA APROBAR");
        contenidoTabla.addColumn("NOTA DEL ESTUDIANTE");


        jlabelTituloListado = new JLabel("Listado de Cursadas");
        jlabelTituloListado.setFont(new Font("Calibri", Font.BOLD, 30));

        panelTituloListado = new JPanel();

        panelTituloListado.add(jlabelTituloListado);

        add(panelTituloListado, BorderLayout.NORTH);





        final CursadaService cursadaService = new CursadaService();

        ArrayList cursadas = null;

        try {

            cursadas = cursadaService.buscarTodas();

        }
        catch (ServiceException e1){

            JOptionPane.showMessageDialog(null, "Se ha producido un error al traer las cursadas registradas: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }


        for (Object obj: cursadas){
            Cursada cursada = (Cursada) obj;
            Object [] row = new Object[13];
            row[0] = cursada.getCodCursada();
            row[1] = cursada.getCupoMax();
            row[2] = 0;
            row[3] = "";
            row[4] = "";
            row[5] = cursada.getPrecio();
            row[6] = 0;
            row[7] = "";
            row[8] = "";
            row[9] = 0;
            row[10] = "";
            row[11] = cursada.getNotaParaAprobar();
            row[12] = 0f;

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
                CursadaService cursadaService1 = new CursadaService();

                int codCursadaEliminar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                try {
                    cursadaService1.eliminarCursada(codCursadaEliminar);
                    refrescarListado();
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al eliminar una cursada registrada: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }



            }
        });


        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarFormularioCursada();

            }
        });


        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                int codCursadaModificar = (Integer) jtable.getValueAt( jtable.getSelectedRow(), 0);
                CursadaService cursadaService1 = new CursadaService();

                Cursada cursada = null;

                try {
                    cursada = cursadaService1.buscarCursada(codCursadaModificar);
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error al intentar modificar una cursada registrada: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarFormularioCursada(cursada);

            }




        });






    }

    public void refrescarListado(){

        removeAll();
        armarPanelListadoCursadas(panelManager);
        validate();
        repaint();


    }
}
