package com.company.gui;

import com.company.entidades.Cursada;
import com.company.service.CursadaService;
import com.company.service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioCursada extends JPanel{

    private JPanel panelComponentes;
    private JPanel panelBotonera;
    private JPanel panelTituloFormulario;


    JLabel jlabelCodCursada;
    JLabel jlabelCupoMax;
    JLabel jlabelPrecio;
    JLabel jlabelNotaParaAprobar;



    JTextField jTextFieldCodCursada;
    JTextField jTextFieldCupoMax;
    JTextField jTextFieldPrecio;
    JTextField jTextFieldNotaParaAprobar;



    JButton jButtonGuardar;
    JButton jButtonCancelar;


    JLabel jlabelTituloFormulario;

    //PanelManager
    PanelManager panelManager;

    private boolean modificacion;

    public void armarPanelFormularioCursada(Cursada cursada){

        jTextFieldCodCursada.setText(String.valueOf(cursada.getCodCursada()));
        jTextFieldCupoMax.setText(String.valueOf(cursada.getCupoMax()));
        jTextFieldPrecio.setText(String.valueOf(cursada.getPrecio()));
        jTextFieldNotaParaAprobar.setText(String.valueOf(cursada.getNotaParaAprobar()));


        modificacion = true;


    }

    public void vaciarComponentes(){
        jTextFieldCodCursada.setText("");
        jTextFieldCupoMax.setText("");
        jTextFieldPrecio.setText("");
        jTextFieldNotaParaAprobar.setText("");

        modificacion = false;
    }

    public void armarPanelFormularioCursada(final PanelManager panelManager){

        this.panelManager = panelManager;

        jlabelCodCursada = new JLabel(" Código de Cursada: ");
        jTextFieldCodCursada = new JTextField(10);

        jlabelCupoMax = new JLabel(" Cupo Máximo: ");
        jTextFieldCupoMax = new JTextField(40);

        jlabelPrecio = new JLabel(" Precio: ");
        jTextFieldPrecio = new JTextField(40);

        jlabelNotaParaAprobar = new JLabel(" Nota mínima para aprobar: ");
        jTextFieldNotaParaAprobar = new JTextField(10);



        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(4,2));

        jlabelCodCursada.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelCupoMax.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelPrecio.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelNotaParaAprobar.setFont(new Font("Calibri", Font.BOLD, 20));


        panelComponentes.add(jlabelCodCursada);
        panelComponentes.add(jTextFieldCodCursada);

        panelComponentes.add(jlabelCupoMax);
        panelComponentes.add(jTextFieldCupoMax);

        panelComponentes.add(jlabelPrecio);
        panelComponentes.add(jTextFieldPrecio);

        panelComponentes.add(jlabelNotaParaAprobar);
        panelComponentes.add(jTextFieldNotaParaAprobar);


        setLayout(new BorderLayout());

        add(panelComponentes, BorderLayout.CENTER);



        panelBotonera = new JPanel();


        jButtonCancelar = new JButton("Cancelar");
        jButtonGuardar = new JButton("Guardar");

        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);


        jlabelTituloFormulario = new JLabel("Formulario de Cursadas");
        jlabelTituloFormulario.setFont(new Font("Calibri",Font.BOLD, 30));

        panelTituloFormulario = new JPanel();



        panelTituloFormulario.add(jlabelTituloFormulario);

        add(panelTituloFormulario, BorderLayout.NORTH);

        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Cursada cursada = new Cursada();

                cursada.setCodCursada(Integer.parseInt(jTextFieldCodCursada.getText()));
                cursada.setCupoMax(Integer.parseInt(jTextFieldCupoMax.getText()));
                cursada.setPrecio(Float.parseFloat(jTextFieldPrecio.getText()));
                cursada.setNotaParaAprobar(Float.parseFloat(jTextFieldNotaParaAprobar.getText()));

                CursadaService cursadaService = new CursadaService();

                try {

                    if(modificacion) {
                        cursadaService.modificarCursada(cursada);
                        JOptionPane.showMessageDialog(null, "La cursada ha sido modificada correctamente", "CURSADA GUARDADA", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        cursadaService.guardarCursada(cursada);
                        JOptionPane.showMessageDialog(null, "Se ha guardado la cursada correctamente", "CURSADA GUARDADA", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "No se ha podido guardar la cursada correctamente: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarListadoCursadas();
            }
        });






        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarListadoCursadas();
            }
        });

    }



}
