package es.ujaen.clublectura.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Favorito {

    @Id         //JPA annotations
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-incremental
    private Integer idFavorito;
    private String userFavorito;
    private String isbnFavorito;

    public Favorito(){}

    public Favorito(Favorito otro){
        idFavorito = otro.idFavorito;
        userFavorito = otro.userFavorito;;
        isbnFavorito = otro.isbnFavorito;
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }
    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    public String getUserFavorito() {
        return userFavorito;
    }
    public void setUserFavorito(String userFavorito) {
        this.userFavorito = userFavorito;
    }

    public String getIsbnFavorito() {
        return isbnFavorito;
    }
    public void setIsbnFavorito(String isbnFavorito) {
        this.isbnFavorito = isbnFavorito;
    }
}
