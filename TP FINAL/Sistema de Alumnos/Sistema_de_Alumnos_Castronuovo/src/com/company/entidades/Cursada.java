package com.company.entidades;

public class Cursada {

    private int codCursada;
    private int cupoMax;
    private Estudiante estudiante;
    private float precio;
    private  Profesor profesorAsignado;
    private Materia materiaAsignada;
    private float notaParaAprobar;
    private float notaEstudiante;


    public int getCodCursada() {
        return codCursada;
    }

    public void setCodCursada(int codCursada) {
        this.codCursada = codCursada;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Profesor getProfesorAsignado() {
        return profesorAsignado;
    }

    public void setProfesorAsignado(Profesor profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }

    public Materia getMateriaAsignada() {
        return materiaAsignada;
    }

    public void setMateriaAsignada(Materia materiaAsignada) {
        this.materiaAsignada = materiaAsignada;
    }

    public float getNotaParaAprobar() {
        return notaParaAprobar;
    }

    public void setNotaParaAprobar(float notaParaAprobar) {
        this.notaParaAprobar = notaParaAprobar;
    }

    public float getNotaEstudiante() {
        return notaEstudiante;
    }

    public void setNotaEstudiante(float notaEstudiante) {
        this.notaEstudiante = notaEstudiante;
    }
}
