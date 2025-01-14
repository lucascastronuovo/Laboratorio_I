package GUI;

import Clases.Administrador;
import Clases.Evento;
import Clases.Usuario;
import Clases.Vendedor;
import Exceptions.ServiceException;
import Services.EventoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
    private JLabel labelTitulo;
    private JLabel labelUser;

    //panel central


    private JTable listaEventos;
    private JTableHeader header;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;

    //botonera

    private JPanel botonera;
    private JButton botonAgregar;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton agregarZonas;
    private JButton botonCerrarSesion;


    public VentanaEventos(PanelManager panelManager, Administrador administrador) {
        this.panelManager = panelManager;
        this.administrador = administrador;
    }



    public void crearPantallaEventos(PanelManager panelManager, Administrador admin) {
        this.panelManager = panelManager;


        //panel superior
        labelTitulo = new JLabel("Eventos (Vista ADMINISTRADOR)    ");
        labelTitulo.setFont(new Font("Calibri", Font.BOLD,20));
        labelTitulo.setForeground(Color.WHITE);

        labelUser = new JLabel(admin.getUsuario());
        labelUser.setFont(new Font("Calibri", Font.HANGING_BASELINE,20));
        labelUser.setForeground(new Color(255, 245, 147 ));

        panelSuperior = new JPanel();

        panelSuperior.add(labelTitulo);
        panelSuperior.add(labelUser);

        panelSuperior.setBackground(Color.RED);


        //panel central - grilla


        contenidoTabla = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listaEventos = new JTable(contenidoTabla);
        listaEventos.setRowSelectionAllowed(true);
        listaEventos.setBackground(new Color(151, 160, 202 ));
        listaEventos.setFont(new Font("Calibri", Font.BOLD, 15));

        header = listaEventos.getTableHeader();
        header.setBackground(Color.BLUE);
        header.setFont(new Font("Calibri", Font.BOLD, 15));
        header.setForeground(Color.WHITE);

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

        botonera = new JPanel();
        botonera.setLayout(new FlowLayout());

        botonAgregar = new JButton("Agregar Evento");
        botonAgregar.setBackground(Color.RED);
        botonAgregar.setForeground(Color.WHITE);

        botonEliminar = new JButton("Eliminar Evento");
        botonEliminar.setBackground(Color.RED);
        botonEliminar.setForeground(Color.WHITE);


        agregarZonas = new JButton("Agregar zonas");
        agregarZonas.setBackground(Color.RED);
        agregarZonas.setForeground(Color.WHITE);


        botonCerrarSesion = new JButton("Cerrar sesión");
        botonCerrarSesion.setBackground(new Color(241, 77, 24 ));

        botonera.setBackground(new Color(0, 28, 167));

        botonera.add(botonAgregar);
        botonera.add(botonEliminar);
        botonera.add(agregarZonas);
        botonera.add(botonCerrarSesion);

        //vista ventana

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botonera, BorderLayout.SOUTH);




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

                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al eliminar el evento");

                }


            }});

        agregarZonas.addActionListener(new ActionListener() {
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

