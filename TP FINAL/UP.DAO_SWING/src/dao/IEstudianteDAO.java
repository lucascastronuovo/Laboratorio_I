package dao;

import entidades.Estudiante;

import java.util.ArrayList;

public interface IEstudianteDAO {
    public void guardar(Estudiante estudiante) throws DAOException;
    public void modificar(Estudiante estudiante) throws DAOException;
    public void eliminar(int id) throws DAOException;
    public Estudiante buscar(int id) throws DAOException;
    public ArrayList buscarTodos() throws DAOException;
}
