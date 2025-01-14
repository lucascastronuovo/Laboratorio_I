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
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaVentas extends JPanel{
    private PanelManager panelManager;

    Vendedor vendedor;

    //panel superior
    JPanel panelSuperior;
    JLabel labelTitulo;


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

        //panel superior 1
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());

        JPanel panelSuperior1 = new JPanel();
        JPanel panelSuperior2 = new JPanel();

        labelTitulo = new JLabel("Registro de ventas");
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);

        panelSuperior1.add(labelTitulo);

        panelSuperior1.setBackground(Color.RED);



        //panel superior 2
        nombreEvento = new JLabel("Nombre del evento: ");
        nombreEvento.setForeground(Color.WHITE);

        eventosBox = new JComboBox();
        eventosBox.setBackground(new Color(151, 160, 202));

        EventoService eventoService = new EventoService();
        ArrayList<Evento> eventos = eventoService.buscarTodos();
        int idEventoSeleccionado;

        for(int i=0;i<eventos.size();i++){
            eventosBox.addItem(String.valueOf(eventos.get(i).getNombre()));
        }

        fechaDesde = new JLabel("Desde: ");
        fechaDesde.setForeground(Color.WHITE);

        fechaDesdeField = new JTextField(20);
        fechaDesdeField.setBackground(new Color(151, 160, 202));

        fechaHasta = new JLabel("Hasta: ");
        fechaHasta.setForeground(Color.WHITE);

        fechaHastaField = new JTextField(20);
        fechaHastaField.setBackground(new Color(151, 160, 202));

        totalLabel = new JLabel();
        totalLabel.setForeground(Color.WHITE);

        panelSuperior2.add(nombreEvento);
        panelSuperior2.add(eventosBox);
        panelSuperior2.add(fechaDesde);
        panelSuperior2.add(fechaDesdeField);
        panelSuperior2.add(fechaHasta);
        panelSuperior2.add(fechaHastaField);
        panelSuperior2.add(totalLabel);

        panelSuperior2.setBackground(Color.BLUE);

        panelSuperior.add(panelSuperior1, BorderLayout.NORTH);
        panelSuperior.add(panelSuperior2, BorderLayout.SOUTH);

        //panel central
        JTableHeader header = new JTableHeader();

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
        tablaventas.setBackground(new Color(151, 160, 202));
        tablaventas.setFont(new Font("Calibri", Font.BOLD,15));

        header = tablaventas.getTableHeader();
        header.setBackground(Color.BLUE);
        header.setFont(new Font("Calibri", Font.BOLD, 15));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tablaventas);



        //panel inferior

        botonera = new JPanel();

        botonCalcularTotal = new JButton("Mostar Total Vendido");
        botonCalcularTotal.setForeground(Color.WHITE);
        botonCalcularTotal.setBackground(Color.RED);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.setForeground(Color.WHITE);
        botonCancelar.setBackground(Color.RED);


        botonera.add(botonCalcularTotal);
        botonera.add(botonCancelar);

        botonera.setBackground(new Color(0, 28, 167));

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
