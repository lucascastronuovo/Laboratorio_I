package DAO;

import Clases.Evento;
import Clases.ZonaEvento;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import Services.ZonaEventoService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EventoDAOdb implements IEventoDAO{
    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/dbtp";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";

    @Override
    public void guardar(Evento evento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO eventos(ID, NOMBRE, FECHA, UBICACION, CREADOR,CANTIDAD_ENTRADAS) VALUES(?,?,?,?,?,?)");
            preparedStatement.setInt(1,evento.getIdEvento());
            preparedStatement.setString(2, evento.getNombre());
            preparedStatement.setDate(3, evento.getFecha());
            preparedStatement.setString(4,evento.getDireccion());
            preparedStatement.setString(5, evento.getCreador());
            preparedStatement.setInt(6, evento.getCapacidad());

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
    public void modificar(Evento evento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("UPDATE eventos SET NOMBRE=?, FECHA=?, DIRECCION=? WHERE ID=?");
            preparedStatement.setString(1, evento.getNombre());
            preparedStatement.setDate(2,evento.getFecha());
            preparedStatement.setString(3,evento.getDireccion());

            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();

            //4 Evaluar resultados
            System.out.println("Registros modificados: " + i);


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

    @Override
    public void eliminar(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;




        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("DELETE FROM EVENTOS WHERE ID=?");
            preparedStatement.setInt(1,id);

            ZonaEventoService zonaEventoService = new ZonaEventoService();
            zonaEventoService.eliminarEventos(id);




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

    public Evento buscarEvento(int id) throws DAOException, ServiceException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Evento evento = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM EVENTOS WHERE id=?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                evento = new Evento();
                ZonaEventoService zonaEventoService = new ZonaEventoService();
                evento.setIdEvento(rs.getInt("ID"));
                evento.setNombre( rs.getString("NOMBRE") );
                evento.setFecha(Date.valueOf(rs.getString("FECHA")));
                evento.setDireccion(rs.getString("UBICACION"));

                evento.setCapacidad(rs.getInt("CANTIDAD_ENTRADAS"));
                evento.setCreador(rs.getString("CREADOR"));
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

        return evento;
    }

    public Evento buscarEvento(String nombreEvento) throws DAOException, ServiceException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Evento evento = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM EVENTOS WHERE nombre=?");
            preparedStatement.setString(1,nombreEvento);

            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                evento = new Evento();
                ZonaEventoService zonaEventoService = new ZonaEventoService();
                evento.setIdEvento(rs.getInt("ID"));
                evento.setNombre( rs.getString("NOMBRE") );
                evento.setFecha(Date.valueOf(rs.getString("FECHA")));
                evento.setDireccion(rs.getString("UBICACION"));

                evento.setCapacidad(rs.getInt("CANTIDAD_ENTRADAS"));
                evento.setCreador(rs.getString("CREADOR"));
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

        return evento;
    }

    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Evento evento = null;
        ArrayList eventos = new ArrayList();

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM EVENTOS");


            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                evento = new Evento();
                evento.setIdEvento(rs.getInt("ID"));
                evento.setNombre( rs.getString("NOMBRE") );
                evento.setFecha(Date.valueOf(rs.getString("FECHA")));
                evento.setDireccion(rs.getString("UBICACION"));
                evento.setCreador(rs.getString("CREADOR"));
                evento.setCapacidad(rs.getInt("CANTIDAD_ENTRADAS"));
                eventos.add(evento);

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
        return eventos;

    }
}
