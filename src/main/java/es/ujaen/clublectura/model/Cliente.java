package es.ujaen.clublectura.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Cliente {
    @Id
    private String user;
    private String pwd, nombre, apellidos, email, rol;

    private static final Logger logger = Logger.getLogger(Cliente.class.getName());

    public Cliente() {}

    public Cliente(Cliente c){
        user = c.user;
        pwd = c.pwd;
        nombre = c.nombre;
        apellidos = c.apellidos;
        email = c.email;
        rol = c.rol;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean estaEnRol(String rol){
        logger.log(Level.SEVERE,"ROL: " + rol);
        if(rol.equals(this.rol)) return true;
        return false;
    }
}
