package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa //Qualifier para esta implementación
@Transactional
public class LibrosDAOJpa implements LibrosDAO, Serializable {

    @PersistenceContext (unitName = "notesPU")
    private EntityManager em;

    private final Logger logger = Logger.getLogger(LibrosDAOJpa.class.getName());

    public LibrosDAOJpa() {}

    @Override
    public Libro buscaIsbn(String isbn){
        Libro l=null;
        try {
            l=em.find(Libro.class, isbn);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return l;
    }

    @Override
    public Libro buscaId(String id) {
        return null;
    }

    @Override
    public List<Libro> buscaTodos() {
        List<Libro> l;
        try {
            l=em.createQuery("select l from Libro l", Libro.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"No se pueden recuperar los libros",e);
            l=new ArrayList<>();
        }
        return l;
    }

    @Override
    public int numLibros() {
        return 0;
    }

    @Override
    public boolean crea(Libro l) {
        boolean creado = false;
        try {
            em.persist(l);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Libro l) {
        boolean guardado = false;
        try {
            l = em.merge(l);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean elimina(String isbn) {
        boolean borrado = false;
        try {
            Libro l = null;
            l = em.find(Libro.class, isbn);
            em.remove(l);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

}