package DAO;

import Clases.Evento;
import Clases.ZonaEvento;
import Exceptions.DAOException;

import java.util.ArrayList;

public interface IZonaEventoDAO {
    public void guardarInfoZona(ZonaEvento zonaEvento) throws DAOException;

    public ArrayList buscarZonasEvento(int idEvento) throws DAOException;

    public void eliminarEventos(int idEvento) throws DAOException;

}
