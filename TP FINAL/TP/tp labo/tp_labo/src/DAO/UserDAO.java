package DAO;

import Clases.Administrador;
import Clases.Usuario;
import Clases.Vendedor;
import Exceptions.DAOException;

import java.sql.*;

public class UserDAO implements  IUserDAO {
    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/dbtp";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";


    @Override
    public void guardarUsuario(Usuario usuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //2
            preparedStatement = connection.prepareStatement("INSERT INTO USUARIOS VALUES(?,?,?)");
            preparedStatement.setString(1,usuario.getUsuario());
            preparedStatement.setString(2, String.valueOf(usuario.getPassword()));
            if (usuario.getTipo() == "administrador"){
                preparedStatement.setString(3,"administrador");
                Administrador administrador = new Administrador(usuario.getUsuario(),usuario.getPassword());
            } else{
                preparedStatement.setString(3,"vendedor");
                Vendedor vendedor = new Vendedor(usuario.getUsuario(),usuario.getPassword());
            }


            //3
            int i = preparedStatement.executeUpdate();


            System.out.println("registros insertados "+i);

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
    public void eliminarUsuario(String username) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //2
            preparedStatement = connection.prepareStatement("DELETE FROM USUARIOS where usuario = ?");
            preparedStatement.setString(1,username);


            //3
            int i = preparedStatement.executeUpdate();


            System.out.println("registros afectados "+i);

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

    public Usuario buscarUsuario(String username, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Usuario usuario = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM USUARIOS WHERE usuario=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);


            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                usuario = new Usuario((rs.getString("usuario")), (rs.getString(2)));
                usuario.setTipo(rs.getString(3));

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {

            try {
                preparedStatement.close();
            } catch (SQLException e2) {
                throw new DAOException(e2.getMessage());
            }
        }
        return usuario;
    }
}
