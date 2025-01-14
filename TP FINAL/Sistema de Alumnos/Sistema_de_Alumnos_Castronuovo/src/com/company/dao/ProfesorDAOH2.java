package com.company.dao;

import com.company.entidades.Profesor;

import java.sql.*;
import java.util.ArrayList;

public class ProfesorDAOH2 implements IProfesorDAO {

    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/dbcastronuovo";
    String DB_USER = "sa";
    String DB_PASSWORD = "";


    @Override
    public void guardarProfesor(Profesor profesor) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO profesores VALUES(?,?,?)");
            preparedStatement.setInt(1, profesor.getId());
            preparedStatement.setString(2, profesor.getNombre());
            preparedStatement.setString(3, profesor.getApellido());

            int i =preparedStatement.executeUpdate();
            System.out.println("Registros insertados: " + i);








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
    }

    @Override
    public void modificarProfesor(Profesor profesor) throws  DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE profesores SET NOMBRE=?, APELLIDO=? WHERE ID=?");
            preparedStatement.setString(1, profesor.getNombre());
            preparedStatement.setString(2, profesor.getApellido());
            preparedStatement.setInt(3, profesor.getId());

            int i =preparedStatement.executeUpdate();
            System.out.println("Registros modificados: " + i);








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
    }

    @Override
    public void eliminarProfesor(int id) throws  DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM PROFESORES WHERE ID=?");
            preparedStatement.setInt(1, id);


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
    public Profesor buscarProfesor(int id) throws  DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Profesor profesor = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM PROFESORES WHERE ID=?");
            preparedStatement.setInt(1, id);


            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                profesor = new Profesor();
                profesor.setId(rs.getInt("ID"));
                profesor.setNombre(rs.getString("NOMBRE"));
                profesor.setApellido(rs.getString("APELLIDO"));

                System.out.println("ID: " + profesor.getId() + " NOMBRE: " + profesor.getNombre() + " APELLIDO: " + profesor.getApellido());
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

        return profesor;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Profesor profesor = null;
        ArrayList profesores = new ArrayList();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM PROFESORES");



            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                profesor = new Profesor();
                profesor.setId(rs.getInt("ID"));
                profesor.setNombre(rs.getString("NOMBRE"));
                profesor.setApellido(rs.getString("APELLIDO"));

                System.out.println("ID: " + profesor.getId() + " NOMBRE: " + profesor.getNombre() + " APELLIDO: " + profesor.getApellido());

                profesores.add(profesor);
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

        return profesores;
    }
}
