package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.IProfesorDAO;
import com.company.dao.ProfesorDAOH2;
import com.company.entidades.Profesor;

import java.util.ArrayList;

public class ProfesorService {

    private IProfesorDAO profesorDAO;

    public ProfesorService(){

        profesorDAO = new ProfesorDAOH2();
    }



    public void guardarProfesor(Profesor profesor) throws ServiceException {


        try {
            profesorDAO.guardarProfesor(profesor);
        }
        catch (DAOException e){

            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }


    public void eliminarProfesor(int id) throws  ServiceException{



        try {
            profesorDAO.eliminarProfesor(id);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    public Profesor buscarProfesor(int id) throws  ServiceException{



        try {
            return profesorDAO.buscarProfesor(id);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }



    }


    public ArrayList buscarTodos() throws  ServiceException{



        try {
            return profesorDAO.buscarTodos();
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    public  void modificarProfesor(Profesor profesor) throws  ServiceException{


        try {
            profesorDAO.modificarProfesor(profesor);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }


}
