//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Edicion;
import es.ujaen.clublectura.qualifiers.DAOMap;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class EdicionDAOMap implements EdicionDAO, Serializable {
    private HashMap<Integer, Edicion> ediciones = null;
    private Integer idEdicion = 1;

    /**
     * Constructor por defecto de la clase EdicionDAOMap
     */
    public EdicionDAOMap() {
        if (this.ediciones.isEmpty()) {
            this.ediciones = new HashMap<>();
            this.ediciones.put(idEdicion, new Edicion(idEdicion++, "2234567890",
                                            1, "10/5/2022",
                                            "Editorial del libro 1", 5,
                                            "Tipo"));
        }
    }

    /**
     * Metodo para devolver todas las ediciones de un libro
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El ISBN del libro
     * @return una lista con todas las ediciones de un libro
     */
    public List<Edicion> buscaPorISBN(String isbn) {
        List<Edicion> le=null;
        for (Edicion e : ediciones.values()) {
            if (e.getIsbn().equals(isbn)) {
                le.add(e);
            }
        }
        return le;
    }

    /**
     * Método para buscar una edición por su ID
     * Método heredado de la clase GenericDAO.java
     * @param id El ID de la edición
     * @return La edición buscada
     */
    public Edicion buscaId(Integer id) {
        if (ediciones.containsKey(id)){
            return this.ediciones.get(id);
        }
        return null;
    }

    /**
     * Método para devolver todas las ediciones de todos los libros
     * Método heredado de la clase GenericDAO.java
     * @return Una lista de todas las ediciones
     */
    public List<Edicion> buscaTodos() {return this.ediciones.values().stream().collect(Collectors.toList());}

    /**
     * Método para devolver el número total de ediciones de todos los libros
     * Método heredado de la clase EdicionDAO.java
     * @return El número total de ediciones
     */
    public int numEdiciones() {return this.ediciones.size()-1;}

    /**
     * Método para devolver el número de todas las ediciones de un libro
     * Método heredado de la clase EdicionDAO.java
     * @param ISBN El ISBN del libro
     * @return El número de ediciones
     */
    @Override
    public int numEdicionesLibro(String ISBN) {return 0;}

    /**
     * Método para validar un ISBN
     * Método heredado de la clase EdicionDAO.java
     * @param ISBN El isbn que se debe comprobar que existe
     * @return Un lógico que indica si el ISBN existe o no
     */
    @Override
    public boolean validarISBN(String ISBN){return false;}

    /**
     * Método para devolver el titulo de un libro
     * Se busca el libro por su ISBN
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El ISBN del libro
     * @return El titulo del libro
     */
    public String tituloEdicion(String isbn) {return null;}

    /**
     * Método para devolver la URL de un libro
     * Se busca el libro por su ISBN
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El ISBN del libro
     * @return La URL del libro
     */
    public String urlEdicion(String isbn) {return null;}

    /**
     * Método para devolver la descripcion de un libro
     * Se busca el libro por su ISBN
     * Método heredado de la clase EdicionDAO.java
     * @param isbn El isbn del libro
     * @return La descripción del libro
     */
    public String descripcionEdicion(String isbn) {return null;}

    /**
     * Método para crear una nueva edición
     * Método heredado de la clase GenericDAO.java
     * @param e La nueva edición
     * @return Un lógico que indica si se ha creado la edicion con exito o no
     */
    public boolean crea(Edicion e) {
        boolean creado = false;
        if (!ediciones.containsKey(e.getId())){
            this.ediciones.put(e.getNumero(), new Edicion(e));
            creado = true;
        }
        return creado;
    }

    /**
     * Método para guardar los datos de una edición existente
     * Método heredado de la clase EdicionDAO.java
     * @param e La nueva edición
     * @return Un lógico que indica si se ha guardado la edicion con exito o no
     */
    public boolean guarda(Edicion e) {
        boolean guardado = false;
        if (this.ediciones.containsKey(e.getId())) {
            Edicion ne = new Edicion(e);
            this.ediciones.replace(e.getNumero(), ne);
            guardado = true;
        }
        return guardado;
    }

    /**
     * Método para eliminar una edición existente
     * Método heredado de la clase EdicionDAO.java
     * @param id El ID de la edición para eliminar
     * @return Un lógico que indica si se ha eliminado la edición con exito o no
     */
    public boolean elimina(Integer id) {
        boolean eliminado = false;
        if (ediciones.containsKey(id)){
            ediciones.remove(id);
            eliminado=true;
        }
        return eliminado;
    }
}
