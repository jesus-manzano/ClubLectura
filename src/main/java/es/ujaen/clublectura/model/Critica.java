package es.ujaen.clublectura.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Critica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-incremental
    private Integer id;

    private String isbnCritica;

    private String userCritica;

    @Size(min=2, max=300, message="Longitud del nombre entre {min} y {max} caracteres")
    private String descrip;

    public Critica() {
    }

    public Critica(Integer id, String isbnCritica, String userCritica, String descrip) {
        this.id = id;
        this.isbnCritica = isbnCritica;
        this.userCritica = userCritica;
        this.descrip = descrip;
    }

    public Critica(Critica otra){
        id = otra.id;
        isbnCritica = otra.isbnCritica;
        userCritica = otra.userCritica;
        descrip = otra.descrip;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbnCritica() {
        return isbnCritica;
    }
    public void setIsbnCritica(String isbnCritica) {
        this.isbnCritica = isbnCritica;
    }

    public String getUserCritica() {
        return userCritica;
    }
    public void setUserCritica(String userCritica) {
        this.userCritica = userCritica;
    }

    public String getDescrip() {
        return descrip;
    }
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
