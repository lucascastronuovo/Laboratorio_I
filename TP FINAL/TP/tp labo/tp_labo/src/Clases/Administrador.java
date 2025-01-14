package Clases;


import java.util.ArrayList;

public class Administrador extends Usuario{
    private ArrayList<Evento> eventosCreados;


    public Administrador(String usuario, String password) {
        super(usuario, password);
        this.eventosCreados = new ArrayList<Evento>();
    }



    public void crearEvento(Evento evento){
        eventosCreados.add(evento);
    }

    public ArrayList<Evento> getEventosCreados() {
        return eventosCreados;
    }

    @Override
    public String getUsuario() {
        return super.getUsuario();
    }


    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
