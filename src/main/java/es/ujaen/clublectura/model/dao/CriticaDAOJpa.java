package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Critica;
import es.ujaen.clublectura.model.Favorito;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa //Qualifier para esta implementación
@Transactional
public class CriticaDAOJpa implements CriticaDAO, Serializable {

    @PersistenceContext(unitName = "notesPU")
    private EntityManager em;

    private final Logger logger = Logger.getLogger(LibrosDAOJpa.class.getName());

    public CriticaDAOJpa() {}

    @Override
    public Critica buscaId(Integer id) {
        return null;
    }

    @Override
    public List<Critica> buscaTodos() {
        List<Critica> c;
        try {
            c=em.createQuery("select c from Critica c", Critica.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"No se pueden recuperar las criticas",e);
            c=new ArrayList<>();
        }
        return c;
    }

    public List<Critica> buscaPorIsbn(String isbn){
        List<Critica> l = null;
        try {
            Query q = em.createQuery("Select c from Critica c where c.isbnCritica = ?1"
                    , Favorito.class).setParameter(1, isbn);
            l = q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return l;
    }

    @Override
    public boolean crea(Critica c) {
        boolean creado = false;
        try {
            em.persist(c);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Critica c) {
        boolean guardado = false;
        try {
            c = em.merge(c);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean elimina(Integer id) {
        boolean borrado = false;
        try {
            Critica c = null;
            c = em.find(Critica.class, id);
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public int numCritica() {
        return 0;
    }
}
