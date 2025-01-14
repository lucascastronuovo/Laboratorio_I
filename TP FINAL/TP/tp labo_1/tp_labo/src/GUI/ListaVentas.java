package GUI;

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

public class ListaVentas extends JPanel{
    private PanelManager panelManager;

    Vendedor vendedor;

    //panel superior
    JPanel panelSuperior;
    JLabel lista;

    //panel central
    JPanel panelCentral;

    JLabel nombreEvento;
    JComboBox eventosBox;
    JLabel fechaDesde;
    JTextField fechaDesdeField;
    JLabel fechaHasta;
    JTextField fechaHastaField;
    JLabel totalLabel;

    //panel inferior

    JPanel botonera;
    JButton botonCancelar;
    JButton botonCalcularTotal;

    public void armarListaVentas(final  PanelManager panelManager, final Vendedor vendedor) throws ServiceException {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        //panel superior
        panelSuperior = new JPanel();
        lista = new JLabel("Registro de ventas");
        panelSuperior.add(lista);


        //panel central
        panelCentral = new JPanel();
        JPanel panelCentral1 = new JPanel();
        JPanel panelCentral2 = new JPanel();

        //panel1
        nombreEvento = new JLabel("Nombre del evento: ");
        eventosBox = new JComboBox();
        EventoService eventoService = new EventoService();
        ArrayList<Evento> eventos = eventoService.buscarTodos();
        int idEventoSeleccionado;

        for(int i=0;i<eventos.size();i++){
            eventosBox.addItem(String.valueOf(eventos.get(i).getNombre()));
        }
        fechaDesde = new JLabel("Desde: ");
        fechaDesdeField = new JTextField(20);
        fechaHasta = new JLabel("Hasta: ");
        fechaHastaField = new JTextField(20);
        totalLabel = new JLabel();
        panelCentral1.add(nombreEvento);
        panelCentral1.add(eventosBox);
        panelCentral1.add(fechaDesde);
        panelCentral1.add(fechaDesdeField);
        panelCentral1.add(fechaHasta);
        panelCentral1.add(fechaHastaField);
        panelCentral1.add(totalLabel);

        //panel2

        DefaultTableModel contenidoTabla = new DefaultTableModel();
        contenidoTabla.addColumn("id compra");
        contenidoTabla.addColumn("evento");
        contenidoTabla.addColumn("dni cliente");
        contenidoTabla.addColumn("nombre cliente");
        contenidoTabla.addColumn("apellido cliente");
        contenidoTabla.addColumn("fecha");
        contenidoTabla.addColumn("zona");
        contenidoTabla.addColumn("cantidad entradas");
        contenidoTabla.addColumn("total pagado");
        ArrayList ventas = new ArrayList();
        VentaService ventaService = new VentaService();
        try {
            ventas = ventaService.traerTodo();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        for(Object obj:ventas){
            Venta venta = (Venta) obj;
            Object[] row = new Object[9];
            row[0] = venta.getIdCompra();
            row[1] = venta.getEvento().getNombre();
            row[2] = venta.getDniCliente();
            row[3] = venta.getNombreCliente();
            row[4] = venta.getApellidoCliente();
            row[5] = venta.getFecha();
            row[6] = venta.getZona();
            row[7] = venta.getCantidadEntradas();
            row[8] = venta.getTotalPagado();

            contenidoTabla.addRow(row);


        JTable tablaventas = new JTable(contenidoTabla);
        JScrollPane scrollPane = new JScrollPane(tablaventas);




        panelSuperior.add(panelCentral1);
        //panelCentral.add(panelCentral2,BorderLayout.CENTER);




        //panel inferior

        botonera = new JPanel();
        botonCancelar = new JButton("Cancelar");
        botonCalcularTotal = new JButton("Mostar Total Vendido");

        botonera.add(botonCancelar);
        botonera.add(botonCalcularTotal);
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarVentanaEventosVend(vendedor);
            }
        });

        botonCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentaService ventaService = new VentaService();
                EventoService eventoService1 = new EventoService();
                Evento eventoSeleccionado = new Evento();
                int total=0;
                try {
                    eventoSeleccionado = eventoService1.buscarEvento(String.valueOf(eventosBox.getSelectedItem()));
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

                totalLabel.setText("total recaudado: "+ventaService.calcularTotalVendido(eventoSeleccionado.getIdEvento(),fechaDesdeField.getText(),fechaHastaField.getText()));


            }

        });

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);
    }

}
}
