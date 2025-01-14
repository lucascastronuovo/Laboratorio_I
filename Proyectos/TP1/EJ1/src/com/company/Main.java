package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        java.lang.Object System;
        System.out.println("Ejercicio 1 TP1:");

        Rueda rueda1 = new Rueda(5.5f, "NEGRO", "ACERO");
        Rueda rueda2 = new Rueda (3.5f, "BLANCO", "ALEACIÓN");

        rueda1.girar();
        rueda2.girar();

        System.out.println("Rueda 1:");
        System.out.println("Radio:" + rueda1.getRadio());
        System.out.println("Color:" + rueda1.getColor());
        System.out.println("Tipo de Material:" + rueda1.getTipoMaterial());
        System.out.println("Girando:" + rueda1.getGirando());

        System.out.println("Rueda 2:");
        System.out.println("Radio:" + rueda2.getRadio());
        System.out.println("Color:" + rueda2.getColor());
        System.out.println("Tipo de Material:" + rueda2.getTipoMaterial());
        System.out.println("Girando:" + rueda2.getGirando());

    }
}
