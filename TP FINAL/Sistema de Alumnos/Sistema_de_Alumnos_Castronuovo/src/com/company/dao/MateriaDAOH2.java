package com.company.dao;

import com.company.entidades.Materia;

import java.sql.*;
import java.util.ArrayList;

public class MateriaDAOH2 implements IMateriaDAO {

    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/dbcastronuovo";
    String DB_USER = "sa";
    String DB_PASSWORD = "";


    @Override
    public void guardarMateria(Materia materia) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO materias VALUES(?,?)");
            preparedStatement.setInt(1, materia.getCodMateria());
            preparedStatement.setString(2, materia.getNombreMateria());


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
    public void modificarMateria(Materia materia) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE materias SET NOMBRE=? WHERE COD_MATERIA=?");
            preparedStatement.setString(1, materia.getNombreMateria());
            preparedStatement.setInt(2, materia.getCodMateria());

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
    public void eliminarMateria(int codMateria) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM materias WHERE COD_MATERIA=?");
            preparedStatement.setInt(1, codMateria);


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
    public Materia buscarMateria(int codMateria) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Materia materia = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM MATERIAS WHERE COD_MATERIA=?");
            preparedStatement.setInt(1, codMateria);


            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                materia = new Materia();
                materia.setCodMateria(rs.getInt("COD_MATERIA"));
                materia.setNombreMateria(rs.getString("NOMBRE"));


                System.out.println("COD_MATERIA: " + materia.getCodMateria() + " NOMBRE: " + materia.getNombreMateria());
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

        return materia;
    }

    @Override
    public ArrayList buscarTodas() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Materia materia = null;
        ArrayList materias = new ArrayList();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM MATERIAS");



            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                materia = new Materia();
                materia.setCodMateria(rs.getInt("COD_MATERIA"));
                materia.setNombreMateria(rs.getString("NOMBRE"));


                System.out.println("COD_MATERIA: " + materia.getCodMateria() + " NOMBRE: " + materia.getNombreMateria());

                materias.add(materia);
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

        return materias;
    }
}
