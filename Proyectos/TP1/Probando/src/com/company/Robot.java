package com.company;

public class Robot {

    private String color;
    private int memoriaRam;
    private float peso;
    private float altura;
    private Computadora computadora;

    public Robot(String color, int memoriaRam, float peso, float altura, Computadora computadora){
        this.color = color;
        this.memoriaRam = memoriaRam;
        this.peso = peso;
        this.altura = altura;
        this.computadora = computadora;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }


    public Computadora getComputadora() {
        return computadora;
    }





}
