package Clases;

public class ZonaEvento{
    private String nombreZona;
    private int precioZona;
    private int cantidadEntradasZona;

    private  int entradasDisponibles;
    private Evento evento;

    public ZonaEvento() {
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String name) {
        this.nombreZona = name;
    }

    public int getPrecioZona() {
        return precioZona;
    }

    public void setPrecioZona(int precioZona) {
        this.precioZona = precioZona;
    }

    public int getCantidadEntradasZona() {
        return cantidadEntradasZona;
    }

    public void setCantidadEntradasZona(int cantidadEntradasZona) {
        this.cantidadEntradasZona = cantidadEntradasZona;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public int getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(int entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }
}
