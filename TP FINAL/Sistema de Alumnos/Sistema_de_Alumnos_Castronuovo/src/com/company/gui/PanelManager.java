package com.company.gui;

import com.company.entidades.Cursada;
import com.company.entidades.Estudiante;
import com.company.entidades.Materia;
import com.company.entidades.Profesor;
import com.company.gui.PanelFormularioProfesor;
import com.company.gui.PanelListadoProfesores;

import javax.swing.*;

public class PanelManager {

    private PanelListadoProfesores panelListadoProfesores;
    private PanelFormularioProfesor panelFormularioProfesor;

    private PanelListadoEstudiantes panelListadoEstudiantes;
    private PanelFormularioEstudiante panelFormularioEstudiante;

    private PanelListadoMaterias panelListadoMaterias;
    private PanelFormularioMateria panelFormularioMateria;

    private PanelListadoCursadas panelListadoCursadas;
    private PanelFormularioCursada panelFormularioCursada;
    private PanelAsignacionEstudiante panelAsignacionEstudiante;


    private JFrame frame;



    public void armarPanelManager(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelFormularioProfesor = new PanelFormularioProfesor();
        panelFormularioProfesor.armarPanelFormularioProfesor(this);


        panelListadoProfesores = new PanelListadoProfesores();
        panelListadoProfesores.armarPanelListadoProfesores(this);

        panelFormularioEstudiante = new PanelFormularioEstudiante();
        panelFormularioEstudiante.armarPanelFormularioEstudiante(this);

        panelListadoEstudiantes = new PanelListadoEstudiantes();
        panelListadoEstudiantes.armarPanelListadoEstudiantes(this);


        panelFormularioMateria = new PanelFormularioMateria();
        panelFormularioMateria.armarPanelFormularioMateria(this);

        panelListadoMaterias = new PanelListadoMaterias();
        panelListadoMaterias.armarPanelListadoMaterias(this);


        panelFormularioCursada = new PanelFormularioCursada();
        panelFormularioCursada.armarPanelFormularioCursada(this);

        panelListadoCursadas = new PanelListadoCursadas();
        panelListadoCursadas.armarPanelListadoCursadas(this);

        panelAsignacionEstudiante = new PanelAsignacionEstudiante();
        panelAsignacionEstudiante.armarPanelAsignacionEstudiante(this);

        frame.setVisible(true);
        frame.pack();

    }


    public void mostrarFormularioProfesor(){
        panelFormularioProfesor.vaciarComponentes();
        mostrar(panelFormularioProfesor);
    }

    public void mostrarFormularioProfesor(Profesor profesor){
        panelFormularioProfesor.armarPanelFormularioProfesor(profesor);
        mostrar(panelFormularioProfesor);
    }

    public void mostrarFormularioEstudiante(){
        panelFormularioEstudiante.vaciarComponentes();
        mostrar(panelFormularioEstudiante);
    }

    public void mostrarFormularioEstudiante(Estudiante estudiante){
        panelFormularioEstudiante.armarPanelFormularioEstudiante(estudiante);
        mostrar(panelFormularioEstudiante);
    }

    public void mostrarFormularioMateria(){
        panelFormularioMateria.vaciarComponentes();
        mostrar(panelFormularioMateria);
    }

    public void mostrarFormularioMateria(Materia materia){
        panelFormularioMateria.armarPanelFormularioMateria(materia);
        mostrar(panelFormularioMateria);
    }


    public void mostrarFormularioCursada(){
        panelFormularioCursada.vaciarComponentes();
        mostrar(panelFormularioCursada);
    }



    public void mostrarFormularioCursada(Cursada cursada){
        panelFormularioCursada.armarPanelFormularioCursada(cursada);
        mostrar(panelFormularioCursada);
    }


    public  void mostrarListadoProfesores(){
        panelListadoProfesores.refrescarListado();
        mostrar(panelListadoProfesores);
    }


    public void mostrarListadoEstudiantes(){
        panelListadoEstudiantes.refrescarListado();
        mostrar(panelListadoEstudiantes);
    }

    public void mostrarListadoMaterias(){
        panelListadoMaterias.refrescarListado();
        mostrar(panelListadoMaterias);
    }


    public void mostrarListadoCursadas(){
        panelListadoCursadas.refrescarListado();
        mostrar(panelListadoCursadas);
    }



    //Paneles aparte

    public void mostrarAsignacionEstudiante(){
        panelFormularioCursada.vaciarComponentes();
        mostrar(panelAsignacionEstudiante);
    }

    public void mostrarAsignacionEstudiante(Cursada cursada){

        panelAsignacionEstudiante.armarPanelAsignacionEstudiante(cursada);
        mostrar(panelAsignacionEstudiante);

    }

    private void mostrar(JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        frame.pack();
    }

}
