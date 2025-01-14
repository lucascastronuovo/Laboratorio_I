package GUI;

import Clases.Administrador;
import Clases.Evento;
import Clases.Usuario;
import Clases.Vendedor;
import Exceptions.ServiceException;
import Services.EventoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VentanaEventos extends JPanel {
    private PanelManager panelManager;
    private Administrador administrador;


    //panel superior
    JPanel panelSuperior;
    private JButton botonCerrarSesion;

    //panel central

    private JTable listaEventos;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;
    //botonera



    public VentanaEventos(PanelManager panelManager, Administrador administrador) {
        this.panelManager = panelManager;
        this.administrador = administrador;
    }



    public void crearPantallaEventos(PanelManager panelManager, Administrador admin) {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        //panel superior
        botonCerrarSesion = new JButton("Cerrar sesión");

        panelSuperior =new JPanel();
        JLabel labelUser = new JLabel(admin.getUsuario());

        panelSuperior.add(botonCerrarSesion);
        panelSuperior.add(labelUser);




        //panel central - grilla


        contenidoTabla = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listaEventos = new JTable(contenidoTabla);
        listaEventos.setRowSelectionAllowed(true);


        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("NOMBRE EVENTO");
        contenidoTabla.addColumn("FECHA");
        contenidoTabla.addColumn("UBICACION");
        contenidoTabla.addColumn("CREADOR");
        contenidoTabla.addColumn("TOTAL ENTRADAS");




        EventoService eventoService = new EventoService();
        ArrayList eventos = null;

        try {
            eventos = eventoService.buscarTodos();
        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Ha sucedido un error al traer los eventos de la base de datos");
        }

        for(Object obj:eventos){
            Evento evento = (Evento) obj;
            Object[] row = new Object[6];
            row[0] = evento.getIdEvento();
            row[1] = evento.getNombre();
            row[2] = String.valueOf(evento.getFecha());
            row[3] = evento.getDireccion();
            row[4] = evento.getCreador();
            row[5] = evento.getCapacidad();

            contenidoTabla.addRow(row);
        }
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(listaEventos);


        //botonera
        JPanel botonera;
        JButton botonAgregar;
        JButton botonModificar;
        JButton botonEliminar;
        JButton setEntradas;
        botonera = new JPanel();
        botonAgregar = new JButton("Agregar evento");
        botonModificar = new JButton("Modificar evento");
        botonEliminar = new JButton("Eliminar evento");
        setEntradas = new JButton("Configurar Entradas");


        botonera.add(botonAgregar);
        //botonera.add(botonModificar);
        botonera.add(botonEliminar);
        botonera.add(setEntradas);

        //vista ventana

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botonera, BorderLayout.SOUTH);



        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idModificar = (int)listaEventos.getValueAt(listaEventos.getEditingRow(), 0);
                EventoService eventoService1 = new EventoService();

                Evento evento = null;
                try {
                    evento = eventoService1.buscarEvento(idModificar);
                } catch (ServiceException serviceException) {
                    serviceException.printStackTrace();
                    //Dialog
                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al traer un estudiante para modificarlo. Por favor contactar al administrador: " + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
                panelManager.mostrarFormularioEvento(administrador);
            }
        });

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarFormularioEvento(administrador);


            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventoService eventoService2 = new EventoService();


                int idEliminar = (int)(listaEventos.getValueAt(listaEventos.getSelectedRow(),0));

                try {
                    eventoService2.eliminarEvento(idEliminar);
                    refrezcarListado();

                } catch (ServiceException serviceException) {
                    serviceException.printStackTrace();
                    //Dialog
                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al eliminar el evento");

                }


            }});

        setEntradas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Evento eventoSeleccionado = eventoService.buscarEvento((int)listaEventos.getValueAt(listaEventos.getSelectedRow(),0));
                    panelManager.mostrarFormularioEntradas(eventoSeleccionado, administrador);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRegister loginRegister = new LoginRegister();
                loginRegister.armarPantallaLogueo(panelManager);
                panelManager.mostrarLoginRegister();

            }
        });
    }


    public void refrezcarListado() {
        removeAll();
        crearPantallaEventos(panelManager, administrador);
        validate();
        repaint();
    }
}

