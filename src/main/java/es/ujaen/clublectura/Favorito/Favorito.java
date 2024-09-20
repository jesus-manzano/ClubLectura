package es.ujaen.clublectura.Favorito;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Favorito {

    @Id         //JPA annotations
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-incremental
    private Integer idFavorito;
    private String isbnLibro;
    private String user;

    public Favorito(String isbn, String user) {
        this.idFavorito = this.idFavorito++;
        this.isbnLibro = isbn;
        this.user = user;
    }

    public Favorito(Favorito _favorito) {
        idFavorito = _favorito.getIdFavorito();
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}