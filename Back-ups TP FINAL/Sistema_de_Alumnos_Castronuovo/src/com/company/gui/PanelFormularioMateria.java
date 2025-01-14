package com.company.gui;

import com.company.entidades.Materia;
import com.company.service.MateriaService;
import com.company.service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioMateria extends JPanel{

    private JPanel panelComponentes;
    private JPanel panelBotonera;
    private JPanel panelTituloFormulario;




    JLabel jlabelCodMateria;
    JLabel jlabelNombre;
    JTextField jTextFieldCodMateria;
    JTextField jTextFieldNombre;



    JButton jButtonGuardar;
    JButton jButtonCancelar;


    JLabel jlabelTituloFormulario;

    //PanelManager
    PanelManager panelManager;

    private boolean modificacion;

    public void armarPanelFormularioMateria(Materia materia){

        jTextFieldCodMateria.setText(String.valueOf(materia.getCodMateria()));
        jTextFieldNombre.setText(materia.getNombreMateria());

        modificacion = true;

    }

    public void vaciarComponentes(){
        jTextFieldCodMateria.setText("");
        jTextFieldNombre.setText("");


        modificacion = false;
    }


    public void armarPanelFormularioMateria(final PanelManager panelManager){
        this.panelManager = panelManager;

        jlabelCodMateria = new JLabel(" Código de Materia: ");
        jTextFieldCodMateria = new JTextField(10);

        jlabelNombre = new JLabel(" Nombre: ");
        jTextFieldNombre = new JTextField(40);



        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(3,2));

        jlabelCodMateria.setFont(new Font("Calibri", Font.BOLD, 20));
        jlabelNombre.setFont(new Font("Calibri", Font.BOLD, 20));


        panelComponentes.add(jlabelCodMateria);
        panelComponentes.add(jTextFieldCodMateria);

        panelComponentes.add(jlabelNombre);
        panelComponentes.add(jTextFieldNombre);



        setLayout(new BorderLayout());

        add(panelComponentes, BorderLayout.CENTER);



        panelBotonera = new JPanel();


        jButtonCancelar = new JButton("Cancelar");
        jButtonGuardar = new JButton("Guardar");

        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);


        jlabelTituloFormulario = new JLabel("Formulario de Materias");
        jlabelTituloFormulario.setFont(new Font("Calibri",Font.BOLD, 30));

        panelTituloFormulario = new JPanel();



        panelTituloFormulario.add(jlabelTituloFormulario);

        add(panelTituloFormulario, BorderLayout.NORTH);

        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Materia materia = new Materia();

                materia.setCodMateria(Integer.parseInt(jTextFieldCodMateria.getText()));
                materia.setNombreMateria(jTextFieldNombre.getText());


                MateriaService materiaService = new MateriaService();

                try {

                    if(modificacion) {
                        materiaService.modificarMateria(materia);
                        JOptionPane.showMessageDialog(null, "La materia ha sido modificada correctamente", "MATERIA GUARDADA", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        materiaService.guardarMateria(materia);
                        JOptionPane.showMessageDialog(null, "Se ha guardado la materia correctamente", "MATERIA GUARDADA", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(null, "No se ha podido guardar la materia correctamente: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarListadoMaterias();
            }
        });






        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarListadoMaterias();
            }
        });
    }



}
