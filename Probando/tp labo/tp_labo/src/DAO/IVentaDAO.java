package DAO;

import Clases.Venta;
import Exceptions.DAOException;
import Exceptions.ServiceException;

import java.util.ArrayList;

public interface IVentaDAO {
    public void registrarVenta(Venta venta) throws DAOException;

    public ArrayList traerVentas(int idEvento) throws ServiceException, DAOException;
    public int calcularTotalVendido(int idEvento, String fechaDesde, String fechaHasta) throws DAOException, ServiceException;
    public ArrayList traerTodo() throws ServiceException, DAOException;
}