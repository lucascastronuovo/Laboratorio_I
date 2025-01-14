package com.company;

import java.util.Objects;

public class Pimiento implements Comparable{
    private String color;
    private double peso;
    private double alto;
    private double ancho;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }


    public int compareTo(Object o) {

        if(o instanceof Pimiento)
        {
            Pimiento p2 = (Pimiento) o;
            return     (int) this.getPeso() - (int) p2.getPeso();
        }

        return -1;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pimiento)) return false;
        Pimiento pimiento = (Pimiento) o;
        return Double.compare(pimiento.peso, peso) == 0 && Objects.equals(color, pimiento.color);
    }


    public int hashCode() {
        return Objects.hash(color, peso);
    }
}
