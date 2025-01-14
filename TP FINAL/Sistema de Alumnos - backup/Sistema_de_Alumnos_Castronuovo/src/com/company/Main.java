package com.company;

import com.company.entidades.Profesor;
import com.company.service.ProfesorService;

public class Main {

    public static void main(String[] args) {


        ProfesorService profesorService = new ProfesorService();

        Profesor profesor = new Profesor();

        profesor.setId(6);
        profesor.setNombre("Pablo");
        profesor.setApellido("Perez");


        //profesorService.guardarProfesor(profesor);

    }
}
