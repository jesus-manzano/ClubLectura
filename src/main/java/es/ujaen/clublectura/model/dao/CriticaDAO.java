package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Critica;
import java.util.List;

public interface CriticaDAO extends GenericDAO<Critica,Integer> {
    public List<Critica> buscaPorIsbn(String isbn);
    public int numCritica();
}

