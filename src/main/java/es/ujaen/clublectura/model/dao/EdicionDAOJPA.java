//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Edicion;
import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
@DAOJpa
@Transactional
public class EdicionDAOJPA implements EdicionDAO, Serializable {
    private final Logger logger = Logger.getLogger(EdicionDAOJPA.class.getName());

    @PersistenceContext(unitName = "notesPU")
    private EntityManager em;

    /**
     * Constructor por defecto
     */
    public EdicionDAOJPA() {}

    /**
     * Método para buscar una edicion por su ID
     * Método heredado de la clase GeneicDAO.java
     * @param id El id de la edición buscada
     * @return una edición
     */
    public Edicion buscaId(Integer id) {
        Edicion edi=null;
        try {
            edi = em.find(Edicion.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return edi;
    }

    /**
     * Método para devolver todas las ediciones de todos los libros
     * Método heredado de la clase GenericDAO.java
     * @return Una lista de todas las edicciones de todos los libros
     */
    public List<Edicion> buscaTodos() {
        List<Edicion> le = null;
        try {
            le = this.em.createQuery("select e from Edicion e", Edicion.class).getResultList();
        } catch (Exception var3) {
            this.logger.log(Level.SEVERE, "No se puede recuperar las Ediciones", var3);
        }
        return le;
    }

    /**
     * Método para añadir una nueva edición
     * Método heredado de la clase GenericDAO.java
     * @param e La nueva edición
     * @return Un lógico que indica si se ha añadido la edición con existo o no
     */
    public boolean crea(Edicion e) {
        boolean creado = false;
        try {
            em.persist(e);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    /**
     * Método para modificar una edición existente
     * Método heredado de la clase GenericDAO.java
     * @param e La nueva edición
     * @return Un lógico que indica si se ha guardado la nueva edición o no
     */
    public boolean guarda(Edicion e) {
        boolean guardado = false;
        try {
            em.merge(e);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    /**
     * Método para eliminar una edición existente
     * Método heredado de la clase GenericDAO.java
     * @param id La ID de la edición eliminada
     * @return Un lógico que indica si se ha eliminado la edición con existo o no
     */
    @Override
    public boolean elimina(Integer id) {
        boolean borrado = false;
        try {
            Edicion e = em.find(Edicion.class, id);
            em.remove(e);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    /**
     * Método para devolver una lista de todas las ediciones de un libro
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El ISBN del libro
     * @return Una lista de todas las ediciones de un libro
     */
    public List<Edicion> buscaPorISBN(String isbn) {
        List<Edicion> le = null;
        try {
            String plantillaSQL = "select e from Edicion e where e.isbn=:ISBNE";
            TypedQuery<Edicion> q = this.em.createQuery(plantillaSQL, Edicion.class);
            q.setParameter("ISBNE", isbn);
            le = q.getResultList();
        } catch (Exception var5) {
            this.logger.log(Level.SEVERE, var5.getMessage(), var5);
        }
        return le;
    }

    /**
     * Método para devolver el número de ediciones totales de todos los libros
     * Método heredado de la clase EdicionDAO.java
     * @return El número de ediciones totales de todos los libros
     */
    public int numEdiciones() {
        int numEdi=0;
        try {
            numEdi = this.buscaTodos().size();
        } catch (Exception var4) {
            this.logger.log(Level.SEVERE, var4.getMessage(), var4);
        }
        return numEdi;
    }

    /**
     * Método para devolver el número de todas las ediciones de un libro
     * Método heredado de la clase EdicionDAO.java
     * @param ISBN El ISBN del libro
     * @return El número de todas las ediciones
     */
    public int numEdicionesLibro(String ISBN){
        int numero=0;
        try {
            numero = buscaPorISBN(ISBN).size();
        } catch (Exception var4) {
            this.logger.log(Level.SEVERE, var4.getMessage(), var4);
        }
        return numero;
    }

    /**
     * Método para validar un ISBN
     * Método heredado de la clase EdicionDAO.java
     * @param ISBN El isbn que se debe comprobar que existe
     * @return Un lógico que indica si el ISBN existe o no
     */
    public boolean validarISBN(String ISBN){
        boolean existe = false;
        try {
            Libro l = em.find(Libro.class, ISBN);
            if (l.getIsbn().equals(ISBN)){
                existe=true;
            }
        } catch (Exception var4) {
            this.logger.log(Level.SEVERE, var4.getMessage(), var4);
        }
        return existe;
    }

    /**
     * Metodo para devolver el titulo de un libro
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El ISBN del libro
     * @return El titulo del libro
     */
    public String tituloEdicion(String isbn) {
        String titulo="";
        try {
            titulo = this.em.find(Libro.class, isbn).getTitulo();
        } catch (Exception var5) {
            this.logger.log(Level.SEVERE, var5.getMessage(), var5);
        }
        return titulo;
    }

    /**
     * Método para devolver la URL de un libro
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El ISBN del libro
     * @return La URL del libro
     */
    public String urlEdicion(String isbn) {
        String URL="";
        try {
            URL = this.em.find(Libro.class, isbn).getUrl();
        } catch (Exception var5) {
            this.logger.log(Level.SEVERE, var5.getMessage(), var5);
        }
        return URL;
    }

    /**
     * Método para devolver la descripción de un libro
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El ISBN del libro
     * @return la descripción del libro
     */
    public String descripcionEdicion(String isbn) {
        String descripcion = "";
        try {
            descripcion = this.em.find(Libro.class, isbn).getDescripcion();
        } catch (Exception var5) {
            this.logger.log(Level.SEVERE, var5.getMessage(), var5);
        }
        return descripcion;
    }
}
