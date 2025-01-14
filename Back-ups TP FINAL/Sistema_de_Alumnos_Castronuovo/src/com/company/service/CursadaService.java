package com.company.service;

import com.company.dao.CursadaDAOH2;
import com.company.dao.DAOException;
import com.company.dao.ICursadaDAO;
import com.company.entidades.Cursada;

import java.util.ArrayList;

public class CursadaService {


    private ICursadaDAO cursadaDAO;

    public CursadaService(){cursadaDAO = new CursadaDAOH2(); }


    public void guardarCursada(Cursada cursada) throws ServiceException{

        try {
            cursadaDAO.guardarCursada(cursada);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }


    public void modificarCursada(Cursada cursada) throws ServiceException{

        try {
            cursadaDAO.modificarCursada(cursada);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }



    public void eliminarCursada(int codCursada) throws ServiceException{

        try {
            cursadaDAO.eliminarCursada(codCursada);

        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Cursada buscarCursada(int codCursada) throws ServiceException{

        try {
            return cursadaDAO.buscarCursada(codCursada);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


    public ArrayList buscarTodas() throws ServiceException{

        try {
            return cursadaDAO.buscarTodas();
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }
}
