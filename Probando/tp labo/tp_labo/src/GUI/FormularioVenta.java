package GUI;

import Clases.Evento;
import Clases.Vendedor;
import Clases.Venta;
import Clases.ZonaEvento;
import Exceptions.ServiceException;
import Services.EventoService;
import Services.VentaService;
import Services.ZonaEventoService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class FormularioVenta extends JPanel{
    PanelManager panelManager;

    JLabel idVenta;
    JTextField idVentaField;
    JLabel dniCliente;
    JTextField dniClienteField;
    JLabel nombreCliente;
    JTextField nombreClienteField;
    JLabel apellidoCliente;
    JTextField apellidoClienteField;
    JLabel fecha;
    JTextField fechaField;


    JLabel zonas;
    JComboBox zonaBox;

    JLabel cantidadEntradas;
    JSpinner cantidadEntradasSpin;
    JLabel getCantidadEntradasDisponibles;

    JLabel totalPagado;




    public void crearFormularioVenta(PanelManager panelManager, Evento evento, Vendedor vendedor) throws ServiceException {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        EventoService eventoService = new EventoService();

        evento = eventoService.buscarEvento(evento.getIdEvento());

        JLabel labelSuperior = new JLabel("Registrar Venta");


        //PANEL CENTRAL
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(4,4));
        idVenta = new JLabel("N° de venta");
        idVentaField = new JTextField(20);
        dniCliente = new JLabel("DNI del comprador");
        dniClienteField = new JTextField(20);
        nombreCliente = new JLabel("Nombre del cliente");
        nombreClienteField = new JTextField(20);
        apellidoCliente = new JLabel("Apellido del cliente");
        apellidoClienteField = new JTextField(20);
        fecha = new JLabel("Fecha yyyy/MM/dd");
        fechaField = new JTextField(20);
        zonas = new JLabel("zona");
        zonaBox = new JComboBox();
        totalPagado = new JLabel();


        ZonaEventoService zonaEventoService = new ZonaEventoService();

        ArrayList<ZonaEvento> arrayZonas = new ArrayList<ZonaEvento>();
        try {
            arrayZonas = zonaEventoService.buscarZonasEvento(evento.getIdEvento());
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

        for(int i = 0; i<arrayZonas.size();i++){
            zonaBox.addItem(arrayZonas.get(i).getNombreZona());
        }

        cantidadEntradas = new JLabel("Cantidad Entradas");
        cantidadEntradasSpin = new JSpinner();
        final int[] precioZona = {0};
        ArrayList<ZonaEvento> finalArrayZonas = arrayZonas;
        cantidadEntradasSpin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                for(int i = 0; i< finalArrayZonas.size(); i++){
                    if(finalArrayZonas.get(i).getNombreZona().equals(zonaBox.getSelectedItem())){
                        precioZona[0] = finalArrayZonas.get(i).getPrecioZona();
                    }
                }


                //int zonaPrecio = zonaBox
                int total = Integer.parseInt(cantidadEntradasSpin.getValue().toString()) * precioZona[0];

                totalPagado.setText("Total: "+String.valueOf(total));


            }
        });
        getCantidadEntradasDisponibles = new JLabel(String.valueOf(evento.getEntradasDisponibles()));


        panelCentral.add(idVenta);
        panelCentral.add(idVentaField);
        panelCentral.add(dniCliente);
        panelCentral.add(dniClienteField);
        panelCentral.add(nombreCliente);
        panelCentral.add(nombreClienteField);
        panelCentral.add(apellidoCliente);
        panelCentral.add(apellidoClienteField);
        panelCentral.add(fecha);
        panelCentral.add(fechaField);
        panelCentral.add(zonas);
        panelCentral.add(zonaBox);
        panelCentral.add(cantidadEntradas);
        panelCentral.add(cantidadEntradasSpin);

        panelCentral.add(totalPagado);

        //botonera
        JPanel panelBotones = new JPanel();
        JButton botonCancelar = new JButton("Cancelar");
        JButton botonRegistrar = new JButton("Registrar venta");

        panelBotones.add(botonCancelar);
        panelBotones.add(botonRegistrar);

        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarVentanaEventosVend(vendedor);
            }
        });

        Evento finalEvento = evento;
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Venta venta = new Venta(finalEvento, vendedor);

                VentaService ventaService = new VentaService();
                venta.setIdCompra(Integer.parseInt(idVentaField.getText()));
                venta.setDniCliente(Long.parseLong(dniClienteField.getText()));
                venta.setNombreCliente(nombreClienteField.getText());
                venta.setApellidoCliente(apellidoClienteField.getText());
                venta.setFecha(Date.valueOf(fechaField.getText()));
                venta.setZona(String.valueOf(zonaBox.getSelectedItem()));
                venta.setCantidadEntradas(Integer.parseInt(cantidadEntradasSpin.getValue().toString()));
                venta.setTotalPagado( Integer.parseInt(cantidadEntradasSpin.getValue().toString()) * precioZona[0]);

                System.out.println(venta.getEvento().getIdEvento());
                System.out.println(venta.getEvento().getNombre());
                System.out.println(venta.getDniCliente());
                System.out.println(venta.getNombreCliente());
                System.out.println(venta.getApellidoCliente());
                System.out.println(venta.getFecha());
                System.out.println(venta.getCantidadEntradas());
                System.out.println(venta.getTotalPagado());

                try {
                    ventaService.registrarVenta(venta);
                    JOptionPane.showMessageDialog(null,"Venta registrada con éxito");
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        add(labelSuperior, BorderLayout.NORTH);
        add(panelCentral,BorderLayout.CENTER);
        add(panelBotones,BorderLayout.SOUTH);

    }
    public void vaciarComponentes(){
        idVentaField.setText("");
        dniClienteField.setText("");
    }
}
