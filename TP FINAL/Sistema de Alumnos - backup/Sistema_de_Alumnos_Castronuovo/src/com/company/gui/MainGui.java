package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainGui {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        PanelListadoProfesores panelListadoProfesores = new PanelListadoProfesores();
        panelListadoProfesores.armarPanelListadoProfesores();
        jFrame.getContentPane().add(panelListadoProfesores);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}
