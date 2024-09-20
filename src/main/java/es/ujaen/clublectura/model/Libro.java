package es.ujaen.clublectura.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Libro {
    @Id
    @Pattern(regexp = "\\d{10}(\\d{3})?",message = "El ISBN debe tener 10 o 13 dígitos")
    private String isbn;

    @Size(min=2, max=50, message = "Longitud del título entre {min} y {max} caracteres")
    private String titulo;

    private String descripcion;

    private String url;

    public Libro() {
        setIsbn("");
        setTitulo("");
        setDescripcion("");
        setUrl("");
    }

    public Libro(String _isbn, String _titulo, String _descripcionn,String _url) {
        this.setIsbn(_isbn);
        this.setTitulo(_titulo);
        this.setDescripcion(_descripcionn);
        this.setUrl(_url);
    }

    public Libro(Libro l) {
        this.isbn = l.isbn;
        this.titulo = l.titulo;
        this.descripcion = l.descripcion;
        this.url=l.url;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}


