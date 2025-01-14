package com.company.dao;

import com.company.entidades.Estudiante;

import java.util.ArrayList;

public interface IEstudianteDAO {

    public void guardarEstudiante(Estudiante estudiante) throws DAOException;;

    public void modificarEstudiante(Estudiante estudiante) throws DAOException;;

    public void eliminarEstudiante(int legajo) throws DAOException;;

    public Estudiante buscarEstudiante(int legajo) throws  DAOException;

    public ArrayList buscarTodos() throws DAOException;;
}
