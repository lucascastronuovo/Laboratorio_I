package com.company;

public class ImpresoraHP extends Impresora{

    @Override
    public void imprimirTexto(String texto) {
        //super.imprimirTexto(texto);
        System.out.println("Imprimiendo en una imprsora HP :" + texto);
    }

    public void impresionPruebaHP(){
        System.out.println("Impresion de prueba para impresora HP");
    }
}
