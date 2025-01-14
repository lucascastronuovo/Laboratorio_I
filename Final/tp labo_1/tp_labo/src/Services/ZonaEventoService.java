package Services;

import Clases.Evento;
import Clases.ZonaEvento;
import DAO.IZonaEventoDAO;
import DAO.ZonaEventoDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;

import java.util.ArrayList;

public class ZonaEventoService {
    private IZonaEventoDAO zonaEventoDAO;

    public ZonaEventoService(){
        zonaEventoDAO = new ZonaEventoDAO();
    }


    public void guardarInfoZona(ZonaEvento zonaEvento) throws ServiceException {
        try {
            zonaEventoDAO.guardarInfoZona(zonaEvento);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    public ArrayList buscarZonasEvento(int idEvento) throws ServiceException {
        try {
            return zonaEventoDAO.buscarZonasEvento(idEvento);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public void eliminarEventos(int idEvento) throws DAOException {
        zonaEventoDAO.eliminarEventos(idEvento);
    }

}
