package GUI;

import Clases.Administrador;
import Clases.Evento;
import Clases.ZonaEvento;
import DAO.ZonaEventoDAO;
import Exceptions.ServiceException;
import Services.ZonaEventoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FormularioEntradas extends JPanel{
    PanelManager panelManager;

    //panel superior
    private JPanel panelSuperior;
    private JLabel labelTitulo;

    //panel central
    JPanel panelCentral;
    JPanel panelTexto;
    JLabel zonaLabel;
    JTextField zonaField;
    JLabel entradaLabel;
    JTextField entradaField;
    JLabel precioLabel;
    JTextField precioField;



    JLabel capacidad;
    JTextField capacidadField;
    JTable zonasTabla;
    JLabel numeroEntradas;
    JTextField numeroEntradasField;
    JLabel precioEntradas;
    JTextField precioEntradasField;
    Administrador admin;


    public void armarFormularioEntradas(PanelManager panelManager, Evento evento, Administrador admin){
        this.panelManager = panelManager;

        panelSuperior = new JPanel();

        labelTitulo = new JLabel("Zonas");
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);

        panelSuperior.add(labelTitulo);
        panelSuperior.setBackground(Color.RED);


        //panel central

        /*
        capacidad = new JLabel("Capacidad");
        capacidadField = new JTextField(20);

         */



        zonaLabel = new JLabel("       Zonas");
        zonaLabel.setForeground(Color.WHITE);

        zonaField = new JTextField(20);
        zonaField.setBackground(new Color(151, 160, 202));

        precioLabel = new JLabel("       Precio de la Zona");
        precioLabel.setForeground(Color.WHITE);

        precioField = new JTextField(20);
        precioField.setBackground(new Color(151, 160, 202));

        entradaLabel = new JLabel("       Número de Entradas");
        entradaLabel.setForeground(Color.WHITE);

        entradaField = new JTextField(20);
        entradaField.setBackground(new Color(151, 160, 202));

        panelTexto = new JPanel();
        panelTexto.setLayout(new GridLayout(3,2));

        panelTexto.add(zonaLabel);
        panelTexto.add(zonaField);
        panelTexto.add(precioLabel);
        panelTexto.add(precioField);
        panelTexto.add(entradaLabel);
        panelTexto.add(entradaField);

        panelTexto.setBackground(Color.BLUE);

        String[] columnas = {"Zonas","Precio de la zona", "N° de entradas"};
        DefaultTableModel contenidoTabla = new DefaultTableModel(columnas,0);

        JTableHeader header = new JTableHeader();

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
        zonasTabla.setBackground(new Color(151, 160, 202));
        zonasTabla.setFont(new Font("Calibri", Font.BOLD, 15));

        header = zonasTabla.getTableHeader();
        header.setForeground(Color.WHITE);
        header.setBackground(Color.BLUE);
        header.setFont(new Font("Calibri", Font.BOLD, 15));

        JScrollPane scrollPane = new JScrollPane(zonasTabla);



        //panelCentral.setLayout(new GridLayout(5,2));
        /*
        panelCentral.add(capacidad);
        panelCentral.add(capacidadField);

         */
        panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());

        panelCentral.add(panelTexto, BorderLayout.NORTH);
        panelCentral.add(scrollPane, BorderLayout.SOUTH);

        panelCentral.setBackground(Color.BLUE);

        //botonera
        JPanel botonera = new JPanel();

        JButton cancelar = new JButton("Cancelar");
        cancelar.setForeground(Color.WHITE);
        cancelar.setBackground(Color.RED);

        JButton agregar = new JButton("Agregar");
        agregar.setForeground(Color.WHITE);
        agregar.setBackground(Color.RED);

        JButton actualizar = new JButton("Actualizar");
        actualizar.setForeground(Color.WHITE);
        actualizar.setBackground(Color.RED);

        botonera.add(actualizar);
        botonera.add(agregar);
        botonera.add(cancelar);

        botonera.setBackground(new Color(0, 28, 167));

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(botonera, BorderLayout.SOUTH);

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
