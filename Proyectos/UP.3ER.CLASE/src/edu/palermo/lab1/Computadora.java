package edu.palermo.lab1;

import java.awt.*;

public class Computadora {
    private int procesador;
    private int capacidadDisco;
    private int memoriaRam;
    private String sistemaOperativo;


    public Computadora(int procesador, int capacidadDisco, int memoriaRam, String sistemaOperativo){
        this.procesador = procesador;
        this.capacidadDisco = capacidadDisco;
        this.memoriaRam = memoriaRam;
        this.sistemaOperativo = sistemaOperativo;
    }

    public void setMemoriaRam(int memoriaRam){
        this.memoriaRam = memoriaRam;
    }

    public int getProcesador(){
        return procesador;
    }

    public int getCapacidadDisco(){
        return capacidadDisco;
    }

    public int getMemoriaRam(){
        return memoriaRam;
    }

    public String getSistemaOperativo(){
        return sistemaOperativo;
    }


}
