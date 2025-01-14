package com.company.dao;

import com.company.entidades.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class EstudianteDAOH2 implements IEstudianteDAO{

    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/dbcastronuovo";
    String DB_USER = "sa";
    String DB_PASSWORD = "";


    @Override
    public void guardarEstudiante(Estudiante estudiante) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO estudiantes VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1,estudiante.getLegajo());
            preparedStatement.setString(2,estudiante.getNombre());
            preparedStatement.setString(3,estudiante.getApellido());
            preparedStatement.setInt(4,estudiante.getCantMaxCursos());
            preparedStatement.setFloat(5, estudiante.getDinero());

            int i = preparedStatement.executeUpdate();

            System.out.println("Registros insertados: " + i);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        catch (SQLException e1){
            e1.printStackTrace();
            throw new DAOException(e1.getMessage());
        }
        finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void modificarEstudiante(Estudiante estudiante) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE estudiantes SET NOMBRE=?,APELLIDO=?,CANTIDAD_MAXIMA_DE_CURSOS=?,DINERO=? WHERE LEGAJO=?");
            preparedStatement.setInt(5,estudiante.getLegajo());
            preparedStatement.setString(1,estudiante.getNombre());
            preparedStatement.setString(2,estudiante.getApellido());
            preparedStatement.setInt(3,estudiante.getCantMaxCursos());
            preparedStatement.setFloat(4, estudiante.getDinero());


            int i =preparedStatement.executeUpdate();

            System.out.println("Registros modificados: " + i);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }
        }

    }

    @Override
    public void eliminarEstudiante(int legajo) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM estudiantes WHERE LEGAJO=?");
            preparedStatement.setInt(1, legajo);


            int i =preparedStatement.executeUpdate();
            System.out.println("Registros eliminados: " + i);








        } catch (ClassNotFoundException e)  {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public Estudiante buscarEstudiante(int legajo) throws  DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Estudiante estudiante = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes WHERE LEGAJO=?");
            preparedStatement.setInt(1, legajo);


            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                estudiante = new Estudiante();
                estudiante.setLegajo(rs.getInt("LEGAJO"));
                estudiante.setNombre(rs.getString("NOMBRE"));
                estudiante.setApellido(rs.getString("APELLIDO"));
                estudiante.setCantMaxCursos(rs.getInt("CANTIDAD_MAXIMA_DE_CURSOS"));
                estudiante.setDinero(rs.getFloat("DINERO"));

                System.out.println("LEGAJO: " + estudiante.getLegajo() + " NOMBRE: " + estudiante.getNombre() + " APELLIDO: " + estudiante.getApellido() + " CANTIDAD MÁXIMA DE CURSOS: " + estudiante.getCantMaxCursos() + " DINERO: " + estudiante.getDinero());

            }







        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }
        }

        return estudiante;
    }

    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Estudiante estudiante = null;
        ArrayList estudiantes = new ArrayList();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes");



            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                estudiante = new Estudiante();
                estudiante.setLegajo(rs.getInt("LEGAJO"));
                estudiante.setNombre(rs.getString("NOMBRE"));
                estudiante.setApellido(rs.getString("APELLIDO"));
                estudiante.setCantMaxCursos(rs.getInt("CANTIDAD_MAXIMA_DE_CURSOS"));
                estudiante.setDinero(rs.getFloat("DINERO"));

                System.out.println("LEGAJO: " + estudiante.getLegajo() + " NOMBRE: " + estudiante.getNombre() + " APELLIDO: " + estudiante.getApellido() + " CANTIDAD MÁXIMA DE CURSOS: " + estudiante.getCantMaxCursos() + " DINERO: " + estudiante.getDinero());

                estudiantes.add(estudiante);
            }







        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }
        }

        return estudiantes;
    }
}
