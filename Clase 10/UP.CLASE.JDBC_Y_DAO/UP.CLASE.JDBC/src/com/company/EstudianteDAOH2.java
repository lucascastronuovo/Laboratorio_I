package com.company;

import java.sql.*;
import java.util.ArrayList;

public class EstudianteDAOH2 implements IEstudianteDAO{

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/dbpalermo";
    private String DB_USER ="sa";
    private String DB_PASSWORD = "";

    @Override
    public void guardar(Estudiante estudiante) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("INSERT INTO estudiantes VALUES(?,?,?)");
            preparedStatement.setInt(1,estudiante.getId());
            preparedStatement.setString(2, estudiante.getNombre());
            preparedStatement.setString(3,estudiante.getApellido());

            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();

            //4 Evaluar resultados
            System.out.println("Registros insertados: " + i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void modificar(Estudiante estudiante) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("UPDATE estudiantes SET NOMBRE=?, APELLIDO=? WHERE ID=?");
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2,estudiante.getApellido());
            preparedStatement.setInt(3,estudiante.getId());

            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();

            //4 Evaluar resultados
            System.out.println("Registros modificados: " + i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("DELETE FROM Estudiantes WHERE ID=?");
            preparedStatement.setInt(1,id);


            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();

            //4 Evaluar resultados
            System.out.println("Registros eliminados: " + i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

    }

    @Override
    public Estudiante buscar(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Estudiante estudiante = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM Estudiantes WHERE id=?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                estudiante = new Estudiante();
                estudiante.setApellido( rs.getString("APELLIDO") );
                estudiante.setId( rs.getInt("ID") );
                estudiante.setNombre(rs.getString("NOMBRE"));
            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return estudiante;
    }

    @Override
    public ArrayList buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Estudiante estudiante = null;
        ArrayList estudiantes = new ArrayList();

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM Estudiantes");


            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while(rs.next())
            {
                estudiante = new Estudiante();
                estudiante.setApellido( rs.getString("APELLIDO") );
                estudiante.setId( rs.getInt("ID") );
                estudiante.setNombre(rs.getString("NOMBRE"));
                estudiantes.add(estudiante);

            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return estudiantes;

    }
}
