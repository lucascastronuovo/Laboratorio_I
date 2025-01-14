package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.EstudianteDAOH2;
import com.company.dao.IEstudianteDAO;
import com.company.entidades.Estudiante;

import java.util.ArrayList;

public class EstudianteService {

    private IEstudianteDAO estudianteDAO;

    public EstudianteService(){
        estudianteDAO = new EstudianteDAOH2();
    }


    public void guardarEstudiante(Estudiante estudiante) throws ServiceException{

        try {
            estudianteDAO.guardarEstudiante(estudiante);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }


    public void modificarEstudiante(Estudiante estudiante) throws ServiceException{

        try {
            estudianteDAO.modificarEstudiante(estudiante);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }



    public void eliminarEstudiante(int legajo) throws ServiceException{

        try {
            estudianteDAO.eliminarEstudiante(legajo);

        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Estudiante buscarEstudiante(int legajo) throws ServiceException{

        try {
            return estudianteDAO.buscarEstudiante(legajo);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


    public ArrayList buscarTodos() throws ServiceException{

        try {
            return estudianteDAO.buscarTodos();
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

}
