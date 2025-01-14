package Clases;

public class Usuario {
    private String usuario;
    private  String password;
    private String tipo;


    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }


    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
