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

        //PANEL SUPERIOR
        JPanel panelSuperior = new JPanel();

        JLabel labelSuperior = new JLabel("Registrar Venta");
        labelSuperior.setFont(new Font("Calibri", Font.BOLD, 20));
        labelSuperior.setForeground(Color.WHITE);

        panelSuperior.add(labelSuperior);

        panelSuperior.setBackground(Color.RED);



        //PANEL CENTRAL
        JPanel panelCentral = new JPanel();

        panelCentral.setLayout(new FlowLayout());

        //PANEL OESTE
        JPanel panelOeste = new JPanel();

        panelOeste.setLayout(new GridLayout(4,2));

        idVenta = new JLabel("       N° de venta");
        idVenta.setForeground(Color.WHITE);

        idVentaField = new JTextField(20);
        idVentaField.setBackground(new Color(151, 160, 202));

        dniCliente = new JLabel("       DNI del comprador");
        dniCliente.setForeground(Color.WHITE);

        dniClienteField = new JTextField(20);
        dniClienteField.setBackground(new Color(151, 160, 202));

        nombreCliente = new JLabel("       Nombre del cliente");
        nombreCliente.setForeground(Color.WHITE);

        nombreClienteField = new JTextField(20);
        nombreClienteField.setBackground(new Color(151, 160, 202));

        apellidoCliente = new JLabel("       Apellido del cliente");
        apellidoCliente.setForeground(Color.WHITE);

        apellidoClienteField = new JTextField(20);
        apellidoClienteField.setBackground(new Color(151, 160, 202));


        //PANEL ESTE

        JPanel panelEste = new JPanel();

        panelEste.setLayout(new GridLayout(4,2));

        fecha = new JLabel("       Fecha (aaaa-mm-dd)");
        fecha.setForeground(Color.WHITE);

        fechaField = new JTextField(20);
        fechaField.setBackground(new Color(151, 160, 202));

        zonas = new JLabel("       zona");
        zonas.setForeground(Color.WHITE);

        zonaBox = new JComboBox();
        zonaBox.setBackground(new Color(151, 160, 202));

        totalPagado = new JLabel();
        totalPagado.setForeground(Color.WHITE);


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

        cantidadEntradas = new JLabel("       Cantidad Entradas");
        cantidadEntradas.setForeground(Color.WHITE);

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



                int total = Integer.parseInt(cantidadEntradasSpin.getValue().toString()) * precioZona[0];

                totalPagado.setText("Total: "+String.valueOf(total));


            }
        });
        getCantidadEntradasDisponibles = new JLabel(String.valueOf(evento.getEntradasDisponibles()));
        getCantidadEntradasDisponibles.setForeground(Color.WHITE);


        panelOeste.add(idVenta);
        panelOeste.add(idVentaField);
        panelOeste.add(dniCliente);
        panelOeste.add(dniClienteField);
        panelOeste.add(nombreCliente);
        panelOeste.add(nombreClienteField);
        panelOeste.add(apellidoCliente);
        panelOeste.add(apellidoClienteField);

        panelOeste.setBackground(Color.BLUE);

        panelEste.add(fecha);
        panelEste.add(fechaField);
        panelEste.add(zonas);
        panelEste.add(zonaBox);
        panelEste.add(cantidadEntradas);
        panelEste.add(cantidadEntradasSpin);

        panelEste.add(totalPagado);

        panelEste.setBackground(Color.BLUE);

        panelCentral.add(panelOeste);
        panelCentral.add(panelEste);

        panelCentral.setBackground(Color.BLUE);

        //botonera
        JPanel botonera = new JPanel();


        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setBackground(Color.RED);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setForeground(Color.WHITE);
        botonCancelar.setBackground(Color.RED);

        botonera.add(botonRegistrar);
        botonera.add(botonCancelar);

        botonera.setBackground(new Color(0, 28, 167));


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
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral,BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);

    }
    public void vaciarComponentes(){
        idVentaField.setText("");
        dniClienteField.setText("");
    }
}
