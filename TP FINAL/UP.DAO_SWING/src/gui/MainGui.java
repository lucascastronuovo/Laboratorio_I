package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelListaEstudiantes panelListaEstudiantes = new PanelListaEstudiantes();
        panelListaEstudiantes.armarPanelListadoEstudiantes();
        jFrame.getContentPane().add(panelListaEstudiantes);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}
