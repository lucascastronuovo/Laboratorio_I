package gui;

import entidades.Estudiante;

import javax.swing.*;

public class PanelManager {

    //El panel manager es quien conoce a todos los paneles
    private PanelListaEstudiantes panelListaEstudiantes;
    private PanelFormularioEstudiante panelFormularioEstudiante;
    //El panel manager es el unico que tiene al frame. Ya que en la aplicación abrá un único frame
    //es decir una unica ventana.
    private JFrame frame;

    public void armarPanelManager(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelFormularioEstudiante = new PanelFormularioEstudiante();
        panelFormularioEstudiante.armarPanelFormularioEstudiante(this);

        panelListaEstudiantes = new PanelListaEstudiantes();
        panelListaEstudiantes.armarPanelListadoEstudiantes(this);

        frame.setVisible(true);
        frame.pack();
    }

    public void mostrarFormularioEstudiante(){
        panelFormularioEstudiante.vaciarComponentes();
        mostrar(panelFormularioEstudiante);
    }

    public void mostrarFormularioEstudiante(Estudiante estudiante){
        panelFormularioEstudiante.armarPanelFormularioEstudiante(estudiante);
        mostrar(panelFormularioEstudiante);
    }

    public void mostrarListaEstudiantes(){
        panelListaEstudiantes.refrezcarListado();
        mostrar(panelListaEstudiantes);
    }

    private void mostrar(JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        frame.pack();
    }

}
