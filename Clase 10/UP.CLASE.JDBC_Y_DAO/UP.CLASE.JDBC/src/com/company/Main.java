package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

       //guardar un estudiante
       EstudianteService estudianteService = new EstudianteService();

       Estudiante estudiante = new Estudiante();
       estudiante.setId(5);
       estudiante.setNombre("Daira");
       estudiante.setApellido("Orlandini");

       estudianteService.guardarEstudiante(estudiante);

       //buscando un estudiante
       Estudiante estudiante2 = estudianteService.buscarEstudiante(5);
       System.out.println("ID:" + estudiante2.getId() + " Nombre:" + estudiante2.getNombre() + " Apellido: " + estudiante2.getApellido());

       //eliminar un estudiante
       estudianteService.eliminarEstudiante(5);

       //modificar id=1
        //1ero recupero un objeto
        Estudiante estudiante4 = estudianteService.buscarEstudiante(2);
        estudiante4.setNombre("Javier Damian");
        //2do modifico
        estudianteService.modificarEstudiante(estudiante4);


       //traer todos
       ArrayList estudiantes = estudianteService.buscarTodos();
       for(Object objeto : estudiantes){
           Estudiante estudiante3 = (Estudiante) objeto;
           System.out.println("ID:" + estudiante3.getId() + " Nombre:" + estudiante3.getNombre() + " Apellido: " + estudiante3.getApellido());

       }




    }

}
