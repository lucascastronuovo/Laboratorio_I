package GUI;

import Clases.Administrador;
import Clases.Evento;
import Clases.ZonaEvento;
import DAO.ZonaEventoDAO;
import Exceptions.ServiceException;
import Services.ZonaEventoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FormularioEntradas extends JPanel{
    PanelManager panelManager;
    JLabel capacidad;
    JTextField capacidadField;
    JLabel zonas;
    JTable zonasTabla;
    JLabel numeroEntradas;
    JTextField numeroEntradasField;
    JLabel precioEntradas;
    JTextField precioEntradasField;
    Administrador admin;


    public void armarFormularioEntradas(PanelManager panelManager, Evento evento, Administrador admin){
        this.panelManager = panelManager;
        setLayout(new BorderLayout());
        //pael central
        JPanel panelCentral = new JPanel();
        /*
        capacidad = new JLabel("Capacidad");
        capacidadField = new JTextField(20);

         */
        zonas = new JLabel("Zonas");


        JTextField zonaField, entradaField, precioField;
        JPanel panelTexto = new JPanel();


        zonaField = new JTextField(20);
        entradaField = new JTextField(20);
        precioField = new JTextField(20);

        panelTexto.add(zonaField);
        panelTexto.add(precioField);
        panelTexto.add(entradaField);
        String[] columnas = {"Zonas","Precio de la zona", "N° de entradas"};
        DefaultTableModel contenidoTabla = new DefaultTableModel(columnas,0);

        //get datos evento

        ZonaEventoService zonaEventoService = new ZonaEventoService();
        try {
            evento.setUbicaciones(zonaEventoService.buscarZonasEvento(evento.getIdEvento()));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


        if (evento.getUbicaciones() != null){
            for(int i=0; i< evento.getUbicaciones().size(); i++){
                Object[] row = new Object[3];
                row[0] =evento.getUbicaciones().get(i).getNombreZona();
                row[1] = evento.getUbicaciones().get(i).getPrecioZona();
                row[2] = evento.getUbicaciones().get(i).getCantidadEntradasZona();
                contenidoTabla.addRow(row);

            }
        }


        zonasTabla = new JTable(contenidoTabla);
        JScrollPane scrollPane = new JScrollPane(zonasTabla);



        panelCentral.setLayout(new GridLayout(5,2));
        /*
        panelCentral.add(capacidad);
        panelCentral.add(capacidadField);

         */
        panelCentral.add(zonas);
        panelCentral.add(panelTexto);
        panelCentral.add(scrollPane);

        //botonera
        JPanel botonera = new JPanel();
        JButton cancelar = new JButton("Cancelar");
        JButton agregar = new JButton("Agregar fila");
        JButton actualizar = new JButton("Actualizar");
        botonera.add(cancelar);
        botonera.add(actualizar);
        botonera.add(agregar);

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarVentanaEventos(admin);
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ZonaEvento> zonas = new ArrayList();




                ZonaEventoService zonaEventoService = new ZonaEventoService();

                for(int i = 0; i < zonasTabla.getModel().getRowCount();i++){

                    ZonaEvento zonaEvento = new ZonaEvento();

                    zonaEvento.setNombreZona((String) zonasTabla.getModel().getValueAt(i,0));
                    zonaEvento.setPrecioZona(Integer.parseInt(zonasTabla.getModel().getValueAt(i,1).toString()));
                    zonaEvento.setCantidadEntradasZona(Integer.parseInt(zonasTabla.getValueAt(i,2).toString()));
                    zonaEvento.setEvento(evento);

                    zonas.add(zonaEvento);

                    try {
                        zonaEventoService.guardarInfoZona(zonaEvento);
                        JOptionPane.showMessageDialog(null,"Actualizado con  exito");

                    } catch (ServiceException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println(zonaEvento.getEvento().getNombre());
                    System.out.println(evento.getCreador());
                    System.out.println(evento.getCapacidad());
                    System.out.println(zonaEvento.getEvento().getCreador());
                    System.out.println(zonaEvento.getEvento().getCapacidad());

                }


                evento.setUbicaciones(zonas);

                //zonaEventoService.guardarZonas(evento);




            }
        });

        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenidoTabla.addRow(
                        new Object[]{
                                zonaField.getText(),
                                precioField.getText(),
                                entradaField.getText(),

                        }
                );
                zonaField.setText("");
                precioField.setText("");
                entradaField.setText("");
            }
        });

        add(panelCentral, BorderLayout.CENTER);
        add(botonera, BorderLayout.SOUTH);



    }
}
