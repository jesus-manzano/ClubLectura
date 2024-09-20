package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.logging.*;

@Transactional
public class ClientesDAOJPA implements Serializable {
    @PersistenceContext(unitName = "notesPU")
    private EntityManager em;

    final Logger logger = Logger.getLogger(ClientesDAOJPA.class.getName());

    public boolean inserta(Cliente c) {
        boolean creado = false;
        try {
            em.persist(c);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    public boolean guarda(Cliente c) {
        boolean guardado = false;
        try {
            c = em.merge( c );
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    public boolean elimina(String user) {
        boolean borrado = false;
        try {
            Cliente c = null;
            c = em.find(Cliente.class, user);
            if(c.estaEnRol("USUARIOS")) { //Eliminamos solo si no es administrador
                em.remove(c);
                borrado = true;
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    public String getRol(String user) {
        Cliente c = null;
        try {
            c = em.find(Cliente.class, user);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        if(c != null) return c.getRol();
        return null;
    }

    public String getPwd(String user) {
        Cliente c = null;
        try {
            c = em.find(Cliente.class, user);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        if(c != null) return c.getPwd();
        return null;
    }

    public List<Cliente> obtenerTodos() {
        List<Cliente> lc = null;
        try {
            Query q = em.createQuery("Select c from Cliente c"
                    , Cliente.class);
            lc = q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lc;
    }
}
