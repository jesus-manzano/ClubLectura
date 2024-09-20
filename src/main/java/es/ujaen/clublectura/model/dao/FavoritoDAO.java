package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Favorito;
import es.ujaen.clublectura.model.Libro;
import java.util.List;

public interface FavoritoDAO extends GenericDAO<Favorito, Integer> {
    List<Libro> buscaPorUser(String user);
    public void borra(String user, String isbn);
    int numFavoritos();
}
