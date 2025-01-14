package com.company.dao;


import com.company.entidades.Cursada;


import java.util.ArrayList;

public interface ICursadaDAO {

    public void guardarCursada(Cursada cursada) throws DAOException;

    public void modificarCursada(Cursada cursada) throws DAOException;

    public  void eliminarCursada(int codCursada) throws DAOException;

    public Cursada buscarCursada(int codCursada) throws DAOException;

    public ArrayList buscarTodas() throws DAOException;

    public void asginarAlumno(Cursada cursada) throws DAOException;


}
