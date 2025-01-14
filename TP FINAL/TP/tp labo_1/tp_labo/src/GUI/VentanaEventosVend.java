package GUI;

import Clases.Administrador;
import Clases.Evento;
import Clases.Vendedor;
import Clases.Venta;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import Services.EventoService;
import Services.VentaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaEventosVend extends JPanel {
    private PanelManager panelManager;

    private Vendedor vendedor;

    //panel superior
    JPanel panelSuperior;
    private JButton botonCerrarSesion;

    //panel central

    private JTable listaEventos;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;

    public VentanaEventosVend(PanelManager panelManager, Vendedor vendedor) {
        this.panelManager = panelManager;
        this.vendedor = vendedor;
    }

    public void crearPantallaEventosVend(PanelManager panelManager, Vendedor vendedor) {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        //panel superior
        botonCerrarSesion = new JButton("Cerrar sesión");

        panelSuperior =new JPanel();
        JLabel test = new JLabel(vendedor.getUsuario());

        panelSuperior.add(botonCerrarSesion);
        panelSuperior.add(test);

        //panel central - grilla

        contenidoTabla = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };



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
        listaEventos = new JTable(contenidoTabla);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(listaEventos);

        //botonera
        JPanel botonera = new JPanel();

        JButton botonRegistrar = new JButton("Registrar Venta");
        JButton registroVentas = new JButton("Ver reporte de ventas") ;

        botonera.add(botonRegistrar);
        botonera.add(registroVentas);


        //vista ventana


        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRegister loginRegister = new LoginRegister();
                loginRegister.armarPantallaLogueo(panelManager);
                panelManager.mostrarLoginRegister();
            }
        });

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioVenta formularioVenta = new FormularioVenta();
                EventoService eventoService1 = new EventoService();
                try {
                    Evento evento = eventoService1.buscarEvento((int)listaEventos.getValueAt(listaEventos.getSelectedRow(),0));
                    panelManager.mostrarFormularioVenta(evento, vendedor);

                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        registroVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VentaService ventaService = new VentaService();

                    ArrayList ventas = new ArrayList();
                    ventas = ventaService.traerTodo();
                    if(ventas != null){
                        panelManager.mostrarListaVentas(vendedor);
                    }

                } catch (ServiceException | DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botonera, BorderLayout.SOUTH);

    }
    public void refrezcarListado() {
        removeAll();
        crearPantallaEventosVend(panelManager, vendedor);
        validate();
        repaint();
    }
}
