package service;

import dao.DAOException;
import dao.EstudianteDAOH2;
import dao.IEstudianteDAO;
import entidades.Estudiante;

import java.util.ArrayList;

public class EstudianteService {
    private IEstudianteDAO estudianteDAO;

    public EstudianteService()
    {
        estudianteDAO = new EstudianteDAOH2();

    }

    public void guardarEstudiante(Estudiante estudiante) throws ServiceException {

        try {
            estudianteDAO.guardar(estudiante);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    public void eliminarEstudiante(int id) throws ServiceException {
        try {
            estudianteDAO.eliminar(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Estudiante buscarEstudiante(int id) throws ServiceException {
        try {
            return estudianteDAO.buscar(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList buscarTodos() throws ServiceException {
        try {
            return estudianteDAO.buscarTodos();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarEstudiante(Estudiante estudiante) throws ServiceException {
        try {
            estudianteDAO.modificar(estudiante);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


}
