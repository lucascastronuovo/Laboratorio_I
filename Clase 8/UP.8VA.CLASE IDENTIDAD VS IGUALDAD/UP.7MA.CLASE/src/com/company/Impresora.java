package com.company;

import java.util.Objects;

public class Impresora extends Object{
    private String color;
    private boolean tieneTinta;

    public void imprimirTexto(String texto){
        System.out.println("Imprimiento en una impresora: " + texto);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isTieneTinta() {
        return tieneTinta;
    }

    public void setTieneTinta(boolean tieneTinta) {
        this.tieneTinta = tieneTinta;
    }

    //Sobreescribiendo el método equals
    //comparar igualdad
    @Override
    public boolean equals(Object obj)
    {
        boolean respuesta = false;

        if(obj == null)
            respuesta = false;

        if(obj instanceof Impresora)
        {
            Impresora imp = (Impresora) obj;
            respuesta = this.color.toUpperCase().equals(imp.getColor().toUpperCase());
        }

        return respuesta;
    }

    //comparar igualdad
    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
