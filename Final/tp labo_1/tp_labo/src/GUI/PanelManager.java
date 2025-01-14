package GUI;

import Clases.Administrador;
import Clases.Evento;
import Clases.Vendedor;
import Exceptions.ServiceException;

import javax.swing.*;

public class PanelManager {
    private JFrame frame;
    private LoginRegister panelLogueo;

    private VentanaRegister ventanaRegister;
    private VentanaEventos ventanaEventos;
    private FormularioEvento formularioEvento;
    private FormularioVenta formularioVenta;
    private  FormularioEntradas formularioEntradas;
    private ListaVentas listaVentas;


    public void armarPanelManager(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventanaRegister = new VentanaRegister();
        ventanaRegister.armarVentanaRegister(this);

        panelLogueo = new LoginRegister();
        panelLogueo.armarPantallaLogueo(this);


        frame.setVisible(true);
        frame.pack();

    }

    public void mostrarLoginRegister(){
        panelLogueo.armarPantallaLogueo(this);
        panelLogueo.refrezcar(this);
        mostrar(panelLogueo);
    }

    public void mostrarVentanaRegister(){
        ventanaRegister.armarVentanaRegister(this);
        ventanaRegister.refrezcar(this);
        mostrar(ventanaRegister);
    }

    public void mostrarVentanaEventos(Administrador administrador){
        ventanaEventos = new VentanaEventos(this, administrador);
        ventanaEventos.crearPantallaEventos(this, administrador);
        ventanaEventos.refrezcarListado();
        mostrar(ventanaEventos);
    }


    public void mostrarVentanaEventosVend(Vendedor vendedor){
        VentanaEventosVend ventanaEventosVend = new VentanaEventosVend(this, vendedor);
        ventanaEventosVend.crearPantallaEventosVend(this, vendedor);
        ventanaEventosVend.refrezcarListado();
        mostrar(ventanaEventosVend);
    }





    public void mostrarFormularioEvento(Administrador usuario){
        formularioEvento = new FormularioEvento();
        formularioEvento.crearFormulario(this, usuario);
        mostrar(formularioEvento);
    }





    public void mostrarFormularioVenta(Evento evento, Vendedor vendedor) throws ServiceException {
        formularioVenta = new FormularioVenta();
        formularioVenta.crearFormularioVenta(this,evento,vendedor);
        mostrar(formularioVenta);
    }

    public void mostrarFormularioEntradas(Evento evento, Administrador administrador){
        formularioEntradas = new FormularioEntradas();
        formularioEntradas.armarFormularioEntradas(this,evento, administrador);
        mostrar(formularioEntradas);

    }

    public void mostrarListaVentas(Vendedor vendedor) throws ServiceException {
        listaVentas = new ListaVentas();
        listaVentas.armarListaVentas(this, vendedor);
        mostrar(listaVentas);
    }



    private void mostrar(JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        frame.pack();
        frame.setVisible(true);
    }
}
