package Services;

import Clases.Usuario;
import DAO.IUserDAO;
import DAO.UserDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;

public class UsuarioService {
    private IUserDAO userDAO;

    public UsuarioService(){
        userDAO = new UserDAO();
    }

    public void guardarAUsuario(Usuario usuario) throws ServiceException {
        try {
            userDAO.guardarUsuario(usuario);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminarUsuario(String usuario) throws ServiceException {
        try {
            userDAO.eliminarUsuario(usuario);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Usuario buscarUsuario(String username, String password){
        try {
            return userDAO.buscarUsuario(username,password);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }

}
