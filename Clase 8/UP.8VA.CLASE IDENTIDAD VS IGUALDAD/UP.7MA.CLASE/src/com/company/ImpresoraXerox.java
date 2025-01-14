package com.company;

public class ImpresoraXerox extends Impresora{
    @Override
    public void imprimirTexto(String texto) {
        System.out.println("Imprimiendo en una imprsora Xerox :" + texto);
    }
}
