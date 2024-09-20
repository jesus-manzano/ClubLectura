package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Cliente;
import es.ujaen.clublectura.qualifiers.DAOMap;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class ClientesDAOMap implements ClientesDAO, Serializable {

    private Map<String, Cliente> clientes;

    public ClientesDAOMap() {
        clientes = new HashMap<>();
    }

    @Override
    public Cliente buscaId(String id) {
        return null;
    }

    @Override
    public List<Cliente> buscaTodos() {
        return clientes.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean crea(Cliente c) {
        clientes.put(c.getUser(), new Cliente(c));
        return true;
    }

    @Override
    public boolean guarda(Cliente c) {
        boolean result=false;
        if (clientes.containsKey(c.getUser())) {
            Cliente nc=new Cliente(c);
            clientes.replace(c.getUser(),c);
            result=true;
        }
        return result;
    }

    @Override
    public boolean elimina(String id) {
        boolean result=false;
        if (clientes.containsKey(id)) {
            clientes.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public int numClientes() {
        return clientes.size();
    }
}
