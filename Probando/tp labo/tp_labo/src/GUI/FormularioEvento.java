package GUI;

import Clases.Administrador;
import Clases.Evento;
import Exceptions.ServiceException;
import Services.EventoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class FormularioEvento extends JPanel{

    PanelManager panelManager;

    //panelprincipal
    private JPanel panelComponentes;
    JLabel idEvento;
    JTextField idField;
    JLabel nombreEvento;
    JTextField nombreEventoField;
    JLabel fecha;
    JTextField fechaField;
    JLabel ubicacion;
    JTextField ubicacionField;
    JLabel capacidad;
    JTextField capacidadField;


    //panelBotones
    private JPanel contBoton;
    JButton botonAgregarEvento;
    JButton botonCancelar;


    private boolean modificacion;

    /*
    public void crearFormulario(Evento evento, Administrador creador){

        idField.setText(String.valueOf(evento.getIdEvento()));
        nombreEventoField.setText(evento.getNombre());
        fechaField.setText(String.valueOf(evento.getFecha()));
        ubicacionField.setText(evento.getDireccion());
        modificacion = true;
    }

     */



    public void vaciarComponentes(){
        idField.setText("");
        nombreEvento.setText("");
        fechaField.setText("");
        ubicacionField.setText("");
        modificacion = false;
    }

    public void crearFormulario(PanelManager panelManager, Administrador creador){

        this.panelManager = panelManager;

        //panel principal

        idEvento = new JLabel("ID");
        idField = new JTextField(10);
        nombreEvento = new JLabel("Nombre del evento");
        nombreEventoField = new JTextField(30);
        fecha = new JLabel("Fecha");
        fechaField = new JTextField(30); //cambiar a formattedfield
        ubicacion = new JLabel("Ubicación");
        ubicacionField = new JTextField(30);
        capacidad = new JLabel("Cantidad total de entradas");
        capacidadField = new JTextField(20);




        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(5,2));
        panelComponentes.add(idEvento);
        panelComponentes.add(idField);
        panelComponentes.add(nombreEvento);
        panelComponentes.add(nombreEventoField);
        panelComponentes.add(fecha);
        panelComponentes.add(fechaField);
        panelComponentes.add(ubicacion);
        panelComponentes.add(ubicacionField);
        panelComponentes.add(capacidad);
        panelComponentes.add(capacidadField);

        setLayout(new BorderLayout());
        add(panelComponentes, BorderLayout.CENTER);

        //botonera
        contBoton = new JPanel();
        botonAgregarEvento = new JButton("AgregarEvento");
        botonCancelar = new JButton("cancelar");

        contBoton.add(botonAgregarEvento);
        contBoton.add(botonCancelar);

        add(contBoton, BorderLayout.SOUTH);


        botonAgregarEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evento evento =new Evento();
                evento.setIdEvento(Integer.parseInt(idField.getText()));
                evento.setNombre(nombreEventoField.getText());
                evento.setFecha(Date.valueOf((fechaField.getText())));
                evento.setDireccion(ubicacionField.getText());
                evento.setCreador(creador.getUsuario());
                evento.setCapacidad(Integer.parseInt(capacidadField.getText()));


                EventoService eventoService = new EventoService();
                try {
                    eventoService.guardarEvento(evento);
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Ha sucedido un error al guardar el evento");
                }
                panelManager.mostrarVentanaEventos(creador);

            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarVentanaEventos(creador);

            }
        });
    }
}
