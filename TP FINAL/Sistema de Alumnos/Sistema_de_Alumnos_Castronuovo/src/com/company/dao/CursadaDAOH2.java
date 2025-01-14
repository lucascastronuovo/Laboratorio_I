package com.company.dao;


import com.company.entidades.Cursada;
import com.company.entidades.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class CursadaDAOH2 implements ICursadaDAO{

    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/dbcastronuovo";
    String DB_USER = "sa";
    String DB_PASSWORD = "";


    @Override
    public void guardarCursada(Cursada cursada) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO cursadas VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,cursada.getCodCursada());
            preparedStatement.setInt(2,cursada.getCupoMax());
            preparedStatement.setInt(3,0);
            preparedStatement.setString(4, "");
            preparedStatement.setString(5, "");
            preparedStatement.setFloat(6,cursada.getPrecio());
            preparedStatement.setInt(7,0);
            preparedStatement.setString(8,"");
            preparedStatement.setString(9, "");
            preparedStatement.setInt(10,0);
            preparedStatement.setString(11, "");
            preparedStatement.setFloat(12, cursada.getNotaParaAprobar());
            preparedStatement.setFloat(13,0f);


            if(cursada.getNotaParaAprobar() > 10 || cursada.getNotaParaAprobar() < 0 ){
                throw new DAOException("La nota indicada no es válida");
            }

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
        } finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void modificarCursada(Cursada cursada) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE cursadas SET CUPO_MAXIMO=?,LEGAJO_ESTUDIANTE=?, NOMBRE_ESTUDIANTE=?, APELLIDO_ESTUDIANTE=?,PRECIO=?,ID_PROFESOR=?,NOMBRE_PROFESOR=?,APELLIDO_PROFESOR=?,COD_MATERIA=?,NOMBRE_MATERIA=?,NOTA_PARA_APROBAR=?,NOTA_DEL_ESTUDIANTE=? WHERE COD_CURSADA=?");
            preparedStatement.setInt(13,cursada.getCodCursada());
            preparedStatement.setInt(1,cursada.getCupoMax());
            preparedStatement.setInt(2,0);
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, "");
            preparedStatement.setFloat(5,cursada.getPrecio());
            preparedStatement.setInt(6,0);
            preparedStatement.setString(7, "");
            preparedStatement.setString(8, "");
            preparedStatement.setInt(9,0);
            preparedStatement.setString(10, "");
            preparedStatement.setFloat(11, cursada.getNotaParaAprobar());
            preparedStatement.setFloat(12,0f);


            if(cursada.getNotaParaAprobar() > 10 || cursada.getNotaParaAprobar() < 0 ){
                throw new DAOException("La nota indicada no es válida");
            }

            int i =preparedStatement.executeUpdate();

            System.out.println("Registros modificados: " + i);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void eliminarCursada(int codCursada) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM cursadas WHERE COD_CURSADA=?");
            preparedStatement.setInt(1, codCursada);


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
    public Cursada buscarCursada(int codCursada) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cursada cursada = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM CURSADAS WHERE COD_CURSADA=?");
            preparedStatement.setInt(1, codCursada);


            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                cursada = new Cursada();
                cursada.setCodCursada(rs.getInt("COD_CURSADA"));
                cursada.setCupoMax(rs.getInt("CUPO_MAXIMO"));
                cursada.setPrecio(rs.getFloat("PRECIO"));
                cursada.setNotaParaAprobar(rs.getFloat("NOTA_PARA_APROBAR"));



                System.out.println("COD_CURSADA: " + cursada.getCodCursada() + " CUPO MÁXIMO: " + cursada.getCupoMax() + " PRECIO: " + cursada.getPrecio() + " NOTA PARA APROBAR: " + cursada.getNotaParaAprobar() );
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

        return cursada;
    }

    @Override
    public ArrayList buscarTodas() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cursada cursada= null;
        ArrayList cursadas = new ArrayList();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM cursadas");



            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){

                cursada = new Cursada();
                cursada.setCodCursada(rs.getInt("COD_CURSADA"));
                cursada.setCupoMax(rs.getInt("CUPO_MAXIMO"));
                cursada.setPrecio(rs.getFloat("PRECIO"));
                cursada.setNotaParaAprobar(rs.getFloat("NOTA_PARA_APROBAR"));


                System.out.println("COD_CURSADA: " + cursada.getCodCursada() + " CUPO MÁXIMO: " + cursada.getCupoMax() + " PRECIO: " + cursada.getPrecio() + " NOTA PARA APROBAR: " + cursada.getNotaParaAprobar() );
                cursadas.add(cursada);
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

        return cursadas;
    }

    @Override
    public void asginarAlumno(Cursada cursada) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO cursadas (LEGAJO_ESTUDIANTE,NOMBRE_ESTUDIANTE,APELLIDO_ESTUDIANTE) SELECT LEGAJO, NOMBRE, APELLIDO FROM ESTUDIANTES WHERE COD_CURSADA=?");

            preparedStatement.setInt(1,cursada.getEstudiante().getLegajo());


            int i =preparedStatement.executeUpdate();

            System.out.println("Estudiante asignado: " + i);

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            }

        }

    }


}
