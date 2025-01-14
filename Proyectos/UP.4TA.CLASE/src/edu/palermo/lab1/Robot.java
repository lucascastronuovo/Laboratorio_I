package edu.palermo.lab1;

public class Robot {
    private String color;
    private double peso;
    private int porcentajeBateria;
    private int cantLibertades;
    private String estado;
    //robot tiene una computadora
    private Computadora computadora;


    public Robot(String color, double peso, int cantLibertades, Computadora computadora){
        this.color = color;
        this.peso = peso;
        this.cantLibertades = cantLibertades;
        porcentajeBateria = 100;
        estado = "APAGADO";
        this.computadora = computadora;
    }

    public Computadora getComputadora(){
        return computadora;
    }

    public void cambiarEstado(String estado)
    {
        this.estado = estado;
    }


    public void encender()
    {
        estado = "ENCENDIDO";
    }

    public boolean estaEncendido()
    {
        if(estado == "ENCENDIDO")
            return true;
        else
            return false;
    }

    public int obtenerPorcentajeBateria()
    {
        return porcentajeBateria;
    }

    public String obtenerColor()
    {
        return color;
    }

    public double obtenerPeso(){
        return peso;
    }

    public int obtenerCantidadLibertades()
    {
        return cantLibertades;
    }

    public void avanzar()
    {
        if(estado == "ENCENDIDO" && porcentajeBateria >= 10) {
            porcentajeBateria = porcentajeBateria - 10;
        }
    }




}
