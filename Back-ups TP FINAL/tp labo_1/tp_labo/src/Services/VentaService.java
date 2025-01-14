package Services;

import Clases.Venta;
import DAO.IVentaDAO;
import DAO.VentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;

import java.util.ArrayList;

public class VentaService {
    private IVentaDAO ventaDAO;

    public VentaService(){
        ventaDAO = new VentaDAO();
    }

    public void registrarVenta(Venta venta) throws ServiceException {
        try {
            ventaDAO.registrarVenta(venta);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList traerVentas(int idEvento) throws ServiceException, DAOException {
        return  ventaDAO.traerVentas(idEvento);
    }
    public int calcularTotalVendido(int idEvento, String fechaDesde, String fechaHasta){
        try {
            return ventaDAO.calcularTotalVendido(idEvento,fechaDesde,fechaHasta);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList traerTodo() throws DAOException, ServiceException {
        return ventaDAO.traerTodo();
    }


}
