package com.company;

public class Cajadevelocidades {

    private String fabricante;
    private int cantMarchas;
    private char tipoRelacion;
    public int marchaActual;

    public Cajadevelocidades(String fabricante, int cantMarchas, char tipoRelacion){

        this.fabricante = fabricante;
        this.cantMarchas = cantMarchas;
        this.tipoRelacion = tipoRelacion;
        marchaActual = 0;

    }



    public String getFabricante(){
        return fabricante;
    }

    public int getCantMarchas(){
        return cantMarchas;
    }
    public char getTipoRelacion(){
        return tipoRelacion;
    }

    public int getMarchaActual(){
        return marchaActual;
    }

}
