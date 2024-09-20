package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Favorito;
import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa
@Transactional
public class FavoritoDAOJPA  implements FavoritoDAO, Serializable {

    private final Logger logger = Logger.getLogger(FavoritoDAOJPA.class.getName());

    @PersistenceContext(unitName = "notesPU")
    private EntityManager em;

    @Override
    public Favorito buscaId(Integer id) {
        Favorito f = null;
        try{
            f=em.find(Favorito.class, id);
        } catch (Exception ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return f;
    }

    @Override
    public boolean crea(Favorito fav){
        boolean creado = false;
        List<Favorito> l = null;
        try {
            Query q = em.createQuery("Select f from Favorito f where f.userFavorito = ?1 and f.isbnFavorito = ?2"
                    , Favorito.class).setParameter(1, fav.getUserFavorito()).setParameter(2, fav.getIsbnFavorito());
            l = q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        if(l.size() == 0) {
            try {
                em.persist(fav);
                creado = true;
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return creado;
    }

    @Override
    public boolean guarda(Favorito f){
        boolean guardado = false;
        try {
            f = em.merge(f);
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
            Favorito f = null;
            f = em.find(Favorito.class, id);
            em.remove(f);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public void borra(String user, String isbn){
        List<Favorito> l = null;
        try {
            Query q = em.createQuery("Select f from Favorito f where f.userFavorito = ?1 and f.isbnFavorito = ?2"
                    , Favorito.class).setParameter(1, user).setParameter(2, isbn);
            l = q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        for(int i=0; i<l.size(); i++)
            elimina(l.get(i).getIdFavorito());
    }

    public List<Libro> buscaPorUser(String user){
        List<Libro> l = null;
        try {
            Query q = em.createQuery("Select l from Favorito f, Libro l where f.isbnFavorito = l.isbn and f.userFavorito = ?1"
                    , Favorito.class).setParameter(1, user);
            l = q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return l;
    }

    @Override
    public List<Favorito> buscaTodos(){
        List<Favorito> lf = null;
        try{
            Query q = em.createQuery("Select f from Favorito f", Favorito.class);
            lf = (List<Favorito>) q.getResultList();
        } catch (Exception ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lf;
    }

    @Override
    public int numFavoritos() {
        List<Favorito> lf = null;
        try{
            Query q = em.createQuery("Select f from Favorito f", Favorito.class);
            lf = (List<Favorito>) q.getResultList();
        } catch (Exception ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lf.size();
    }
}








