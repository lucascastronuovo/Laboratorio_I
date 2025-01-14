package com.company;

import java.util.ArrayList;

public interface IEstudianteDAO {
    public void guardar(Estudiante estudiante);
    public void modificar(Estudiante estudiante);
    public void eliminar(int id);
    public Estudiante buscar(int id);
    public ArrayList buscarTodos();
}
