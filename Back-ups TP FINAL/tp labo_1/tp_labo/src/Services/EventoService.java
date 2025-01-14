package Services;

import Clases.Evento;
import DAO.EventoDAOdb;
import DAO.IEventoDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;

import java.util.ArrayList;

public class EventoService {
    private IEventoDAO eventoDAO;


    public EventoService() {
        eventoDAO = new EventoDAOdb();
    }

    public void guardarEvento(Evento evento) throws ServiceException {
        try {
            eventoDAO.guardar(evento);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    public void modificarEvento(Evento evento) throws ServiceException {
        try {
            eventoDAO.modificar(evento);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminarEvento(int id) throws ServiceException {
        try {
            eventoDAO.eliminar(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Evento buscarEvento(int id) throws ServiceException {
        try {
            return eventoDAO.buscarEvento(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
    public Evento buscarEvento(String nombreEvento) throws ServiceException {
        try {
            return eventoDAO.buscarEvento(nombreEvento);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList buscarTodos() throws ServiceException {
        try {
            return eventoDAO.buscarTodos();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }


}
