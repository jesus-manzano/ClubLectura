package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Libro;

public interface LibrosDAO extends GenericDAO<Libro,String> {
    public Libro buscaIsbn(String isbn);
    public int numLibros();
}


