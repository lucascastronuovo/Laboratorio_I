package com.company;

import entidades.Estudiante;
import service.EstudianteService;
import service.ServiceException;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //guardar un estudiante
        EstudianteService estudianteService = new EstudianteService();

        Estudiante estudiante = new Estudiante();
        estudiante.setId(5);
        estudiante.setNombre("Daira");
        estudiante.setApellido("Orlandini");

        try {
            estudianteService.guardarEstudiante(estudiante);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        //buscando un estudiante
        Estudiante estudiante2 = null;
        try {
            estudiante2 = estudianteService.buscarEstudiante(5);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("ID:" + estudiante2.getId() + " Nombre:" + estudiante2.getNombre() + " Apellido: " + estudiante2.getApellido());

        //eliminar un estudiante
        try {
            estudianteService.eliminarEstudiante(5);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        //modificar id=1
        //1ero recupero un objeto
        Estudiante estudiante4 = null;
        try {
            estudiante4 = estudianteService.buscarEstudiante(2);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        estudiante4.setNombre("Javier Damian");
        //2do modifico
        try {
            estudianteService.modificarEstudiante(estudiante4);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        //traer todos
        ArrayList estudiantes = null;
        try {
            estudiantes = estudianteService.buscarTodos();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        for(Object objeto : estudiantes){
            Estudiante estudiante3 = (Estudiante) objeto;
            System.out.println("ID:" + estudiante3.getId() + " Nombre:" + estudiante3.getNombre() + " Apellido: " + estudiante3.getApellido());

        }

    }
}
