package com.company.dao;

import com.company.entidades.Profesor;

import java.util.ArrayList;

public interface IProfesorDAO {

    public void guardarProfesor(Profesor profesor) throws DAOException;

    public void modificarProfesor(Profesor profesor) throws  DAOException;

    public void eliminarProfesor(int id) throws  DAOException;

    public Profesor buscarProfesor(int id) throws  DAOException;

    public ArrayList buscarTodos() throws  DAOException;

}
