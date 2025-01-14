package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.IMateriaDAO;
import com.company.dao.MateriaDAOH2;
import com.company.entidades.Materia;

import java.util.ArrayList;

public class MateriaService {


    private IMateriaDAO materiaDAO;

    public MateriaService(){

        materiaDAO = new MateriaDAOH2();
    }


    public void guardarMateria(Materia materia) throws ServiceException {
        try {
            materiaDAO.guardarMateria(materia);
        }
        catch (DAOException e){

            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarMateria(Materia materia) throws ServiceException {
        try {
            materiaDAO.modificarMateria(materia);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


    public void eliminarMateria(int codMateria) throws ServiceException {
        try {
            materiaDAO.eliminarMateria(codMateria);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


    public Materia buscarMateria(int codMateria) throws ServiceException {

        try {
            return materiaDAO.buscarMateria(codMateria);
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


    public ArrayList buscarTodas() throws ServiceException {
        try {
            return materiaDAO.buscarTodas();
        }
        catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }



}
