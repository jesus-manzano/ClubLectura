package es.ujaen.clublectura.controller;

import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.model.dao.LibrosDAO;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("ctrlLibros")
@ViewScoped
public class LibrosController implements Serializable {
    @Inject @DAOJpa

    private LibrosDAO librosDAO;
    //View-Model
    private Libro libro;

    public LibrosController() {}

    @PostConstruct
    private void init() {
        libro = new Libro();
    }

    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        //método no necesario, se usarán los set de Libro desde vista
        this.libro = libro;
    }

    public List<Libro> getLibros() {
        return librosDAO.buscaTodos();
    }

    public void recupera() {
        libro = librosDAO.buscaIsbn(libro.getIsbn());
    }

    public String crea() {
        librosDAO.crea(libro);
        //Post-Redirect-Get
        return "/libro/detalle?faces-redirect=true&isbn="+libro.getIsbn();
    }

    public String borra() {
        // Delete current view-model client
        librosDAO.elimina(this.libro.getIsbn());
        return "/index?faces-redirect=true";
    }
}
