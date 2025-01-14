package DAO;

import Clases.Evento;
import Exceptions.DAOException;
import Exceptions.ServiceException;

import java.util.ArrayList;

public interface IEventoDAO {
    public void guardar(Evento evento) throws DAOException;
    public void modificar(Evento evento) throws DAOException;
    public void eliminar(int ID) throws DAOException;
    public Evento buscarEvento(int id) throws DAOException, ServiceException;
    public Evento buscarEvento(String nombreEvento) throws DAOException, ServiceException;
    public ArrayList buscarTodos() throws DAOException;

}
