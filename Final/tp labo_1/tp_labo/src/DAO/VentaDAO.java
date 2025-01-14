package DAO;

import Clases.Evento;
import Clases.Venta;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import Services.EventoService;

import java.sql.*;
import java.util.ArrayList;

public class VentaDAO implements IVentaDAO{
    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/dbcastronuovo";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";

    @Override
    public void registrarVenta(Venta venta) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO ventas(ID, EVENTO,DNI_CLIENTE,NOMBRE_CLIENTE, APELLIDO_CLIENTE, FECHA, TOTAL_PAGADO ,ZONA, VENDEDOR, IDEVENTO, CANTIDAD_ENTRADAS) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,venta.getIdCompra());
            preparedStatement.setString(2, venta.getEvento().getNombre());
            preparedStatement.setLong(3, venta.getDniCliente());
            preparedStatement.setString(4, venta.getNombreCliente());
            preparedStatement.setString(5,venta.getApellidoCliente());
            preparedStatement.setDate(6, (java.sql.Date) venta.getFecha());
            preparedStatement.setInt(7, venta.getTotalPagado());
            preparedStatement.setString(8, venta.getZona());
            preparedStatement.setString(9,venta.getVendedor().getUsuario());
            preparedStatement.setInt(10,venta.getEvento().getIdEvento());
            preparedStatement.setInt(11,venta.getCantidadEntradas());

            int i = preparedStatement.executeUpdate();
            System.out.println("Registros afectados "+i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }


    }

    @Override
    public ArrayList traerVentas(int idEvento) throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Venta venta = null;

        ArrayList ventas = new ArrayList();

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM VENTAS WHERE IDEVENTO = ?");
            preparedStatement.setInt(1,idEvento);


            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();
            EventoService eventoService = new EventoService();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                venta = new Venta();
                venta.setIdCompra(rs.getInt("ID"));
                venta.setEvento(eventoService.buscarEvento(rs.getInt("IDEVENTO")));
                venta.setDniCliente(rs.getInt("DNI_CLIENTE"));
                venta.setFecha(rs.getDate("FECHA"));
                venta.setTotalPagado(rs.getInt("TOTAL_PAGADO"));

                ventas.add(venta);

            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }
        return ventas;

    }




    @Override
    public int calcularTotalVendido(int idEvento, String fechaDesde, String fechaHasta) throws DAOException, ServiceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int total;


        ArrayList ventas = new ArrayList();

        try {
            total = 0;

            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT SUM(TOTAL_PAGADO) FROM VENTAS WHERE IDEVENTO = ? AND FECHA BETWEEN ? AND ?");
            preparedStatement.setInt(1,idEvento);
            preparedStatement.setDate(2, Date.valueOf(fechaDesde));
            preparedStatement.setDate(3, Date.valueOf(fechaHasta));


            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();
            EventoService eventoService = new EventoService();

            //4 Evaluamos resultados del result set

            while(rs.next()) {
                total = rs.getInt(1);
            }





        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }
        return total;

    }

    @Override
    public ArrayList traerTodo() throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Venta venta = null;
        ArrayList ventas = new ArrayList();

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM VENTAS");


            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();
            EventoService eventoService = new EventoService();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                venta = new Venta();
                venta.setIdCompra(rs.getInt("ID"));
                venta.setEvento( eventoService.buscarEvento(rs.getInt("IDEVENTO")));
                venta.setDniCliente(rs.getInt("DNI_CLIENTE"));
                venta.setNombreCliente(rs.getString("NOMBRE_CLIENTE"));
                venta.setApellidoCliente(rs.getString("APELLIDO_CLIENTE"));
                venta.setFecha(rs.getDate("FECHA"));
                venta.setZona(rs.getString("ZONA"));
                venta.setCantidadEntradas(rs.getInt("CANTIDAD_ENTRADAS"));
                venta.setTotalPagado(rs.getInt("TOTAL_PAGADO"));

                ventas.add(venta);

            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }
        return ventas;
    }


}
