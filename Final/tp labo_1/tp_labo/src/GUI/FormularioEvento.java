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

    //panel superior
    private JPanel panelSuperior;
    JLabel labelTitulo;

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
    private JPanel botonera;
    JButton botonAgregarEvento;
    JButton botonCancelar;


    private boolean modificacion;




    public void vaciarComponentes(){
        idField.setText("");
        nombreEvento.setText("");
        fechaField.setText("");
        ubicacionField.setText("");
        modificacion = false;
    }

    public void crearFormulario(PanelManager panelManager, Administrador creador){

        this.panelManager = panelManager;

        //panel superior
        panelSuperior = new JPanel();
        labelTitulo = new JLabel("Agregar Evento");
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);

        panelSuperior.add(labelTitulo);
        panelSuperior.setBackground(Color.RED);

        //panel principal

        idEvento = new JLabel("       ID");
        idEvento.setForeground(Color.WHITE);

        idField = new JTextField(10);
        idField.setBackground(new Color(151, 160, 202));

        nombreEvento = new JLabel("       Nombre del evento");
        nombreEvento.setForeground(Color.WHITE);

        nombreEventoField = new JTextField(30);
        nombreEventoField.setBackground(new Color(151, 160, 202));

        fecha = new JLabel("       Fecha (aaaa-mm-dd)");
        fecha.setForeground(Color.WHITE);

        fechaField = new JTextField(30);
        fechaField.setBackground(new Color(151, 160, 202));

        ubicacion = new JLabel("       Ubicación");
        ubicacion.setForeground(Color.WHITE);

        ubicacionField = new JTextField(30);
        ubicacionField.setBackground(new Color(151, 160, 202));

        capacidad = new JLabel("       Cantidad total de entradas");
        capacidad.setForeground(Color.WHITE);

        capacidadField = new JTextField(20);
        capacidadField.setBackground(new Color(151, 160, 202));




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

        panelComponentes.setBackground(Color.BLUE);

        //botonera
        botonera = new JPanel();

        botonAgregarEvento = new JButton("Agregar Evento");
        botonAgregarEvento.setBackground(Color.RED);
        botonAgregarEvento.setForeground(Color.WHITE);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.setBackground(Color.RED);
        botonCancelar.setForeground(Color.WHITE);

        botonera.add(botonAgregarEvento);
        botonera.add(botonCancelar);

        botonera.setBackground(new Color(0, 28, 167));

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(panelComponentes, BorderLayout.CENTER);
        add(botonera, BorderLayout.SOUTH);


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
