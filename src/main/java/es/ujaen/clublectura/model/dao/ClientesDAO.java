package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Cliente;

public interface ClientesDAO extends GenericDAO<Cliente,String> {
    public int numClientes();
}
