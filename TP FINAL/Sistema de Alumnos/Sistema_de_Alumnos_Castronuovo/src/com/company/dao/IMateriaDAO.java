package com.company.dao;

import com.company.entidades.Materia;

import java.util.ArrayList;

public interface IMateriaDAO {


    public void guardarMateria(Materia materia) throws DAOException;

    public void modificarMateria(Materia materia) throws DAOException;

    public void eliminarMateria(int codMateria) throws DAOException;

    public  Materia buscarMateria(int codMateria) throws DAOException;

    public ArrayList buscarTodas() throws DAOException;


}
