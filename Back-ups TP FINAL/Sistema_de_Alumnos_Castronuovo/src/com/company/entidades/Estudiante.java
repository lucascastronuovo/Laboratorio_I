package com.company.entidades;

public class Estudiante extends Usuario{

    private int legajo;
    private String nombre;
    private String apellido;
    private int cantMaxCursos;
    private Cursada cursadas[];
    private float dinero;




    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCantMaxCursos() {
        return cantMaxCursos;
    }

    public void setCantMaxCursos(int cantMaxCursos) {
        this.cantMaxCursos = cantMaxCursos;
    }

    public Cursada[] getCursadas() {
        return cursadas;
    }

    public void setCursadas(Cursada[] cursadas) {
        this.cursadas = cursadas;
    }

    public float getDinero() {return dinero;}

    public void setDinero(float dinero) {this.dinero = dinero;}
}
