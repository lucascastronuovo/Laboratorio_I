package DAO;

import Clases.Evento;
import Clases.ZonaEvento;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import Services.EventoService;
import Services.ZonaEventoService;

import java.sql.*;
import java.util.ArrayList;

public class ZonaEventoDAO implements IZonaEventoDAO {
    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/dbcastronuovo";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";

    public void guardarInfoZona(ZonaEvento zonaEvento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO zonas_eventos(NOMBRE_ZONA,PRECIO_POR_ZONA ,ENTRADAS_POR_ZONA, ENTRADAS_DISPONIBLES ,IDEVENTO ) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(zonaEvento.getNombreZona()));
            preparedStatement.setInt(2, zonaEvento.getPrecioZona());
            preparedStatement.setInt(3, zonaEvento.getCantidadEntradasZona());
            preparedStatement.setInt(4, zonaEvento.getEvento().getCapacidad());
            preparedStatement.setInt(5, zonaEvento.getEvento().getIdEvento());

            int a = preparedStatement.executeUpdate();
            System.out.println("Registros afectados " + a);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

         finally {
            try {
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }


    }

    @Override
    public ArrayList buscarZonasEvento(int idEvento) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<ZonaEvento> zonasEventos = new ArrayList<ZonaEvento>();

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM ZONAS_EVENTOS WHERE idEvento = ?");
            preparedStatement.setInt(1,idEvento);


            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                ZonaEvento zonaEvento = new ZonaEvento();
                zonaEvento.setNombreZona(rs.getString("NOMBRE_ZONA"));
                zonaEvento.setPrecioZona(rs.getInt("PRECIO_POR_ZONA"));
                zonaEvento.setCantidadEntradasZona(rs.getInt("ENTRADAS_POR_ZONA"));
                zonaEvento.setEntradasDisponibles(rs.getInt("ENTRADAS_DISPONIBLES"));
                try {
                    zonaEvento.setEvento(new EventoService().buscarEvento(rs.getInt("IDEVENTO")));
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }

                zonasEventos.add(zonaEvento);

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
        return zonasEventos;
    }

    public void eliminarEventos(int idEvento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;




        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("DELETE FROM ZONAS_EVENTOS WHERE IDEVENTO=?");
            preparedStatement.setInt(1,idEvento);


            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();




            //4 Evaluar resultados
            System.out.println("Registros eliminados: " + i);


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

    }


}
