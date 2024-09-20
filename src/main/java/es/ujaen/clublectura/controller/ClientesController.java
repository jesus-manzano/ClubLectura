package es.ujaen.clublectura.controller;

import es.ujaen.clublectura.model.Cliente;
import es.ujaen.clublectura.model.dao.ClientesDAOJPA;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("ctrlClientes")
@RequestScoped
public class ClientesController implements Serializable {

    @Inject
    private ClientesDAOJPA clientes;

    private Cliente cliente;

    private static final Logger logger = Logger.getLogger(ClientesController.class.getName());

    public ClientesController() {
        cliente = new Cliente();
    }

    public String enviar() {
        cliente.setRol("USUARIOS"); // Por defecto solo serán usuarios
        if(clientes.inserta(cliente))
            return "/index?faces-redirect=true";
        return null;
    }

    public void elimina(String user) {
        clientes.elimina(user);
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes.obtenerTodos();
    }
    public void setClientes(ClientesDAOJPA clientes) {
        this.clientes = clientes;
    }
}
