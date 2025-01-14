package DAO;

import Clases.Usuario;
import Exceptions.DAOException;

public interface IUserDAO {
    public void guardarUsuario(Usuario usuario) throws DAOException;
    public void eliminarUsuario(String username) throws DAOException;
    public Usuario buscarUsuario(String username, String password) throws DAOException;
}
