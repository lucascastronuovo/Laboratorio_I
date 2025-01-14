package com.company;

import java.util.ArrayList;

public class EstudianteService {
    private IEstudianteDAO estudianteDAO;

    public EstudianteService()
    {
        estudianteDAO = new EstudianteDAOH2();

    }

    public void guardarEstudiante(Estudiante estudiante){
        estudianteDAO.guardar(estudiante);
    }

    public void eliminarEstudiante(int id){
        estudianteDAO.eliminar(id);
    }

    public Estudiante buscarEstudiante(int id){
        return estudianteDAO.buscar(id);
    }

    public ArrayList buscarTodos()
    {
        return estudianteDAO.buscarTodos();
    }

    public void modificarEstudiante(Estudiante estudiante){
        estudianteDAO.modificar(estudiante);
    }


}
