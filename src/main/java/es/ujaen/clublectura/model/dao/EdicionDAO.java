//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Edicion;
import java.util.List;

public interface EdicionDAO extends GenericDAO<Edicion, Integer> {
    /**
     * Método para devolver todas las ediciones de un libro
     * Las ediciones se buscan por el ISBN
     * @param ISBN El ISBN del libro para buscar sus ediciones
     * @return una lista de todas las ediciones del libro
     */
    List<Edicion> buscaPorISBN(String ISBN);

    /**
     * Método para devolver el numero total de todas las ediciones de todos los libros
     * @return el numero total de ediciones de todos los libros
     */
    int numEdiciones();

    /**
     * Método para devolver el número de todas las ediciones de un libro
     * @param ISBN El ISBN del libro
     * @return El número de todas las ediciones de un libro
     */
    int numEdicionesLibro(String ISBN);

    /**
     * Método para validar un ISBN
     * @param ISBN El isbn que se debe comprobar que existe
     * @return Un lógico que indica si el ISBN existe o no
     */
    boolean validarISBN(String ISBN);

    /**
     * Método para devolver el titulo de un libro
     * Se busca el libro por su ISBN
     * @param ISBN el ISBN del libro
     * @return el nombre del libro
     */
    String tituloEdicion(String ISBN);

    /**
     * Método para devolver la URL de un libro
     * Se busca el libro por su ISBN
     * @param ISBN el ISBN del libro
     * @return la URL del libro
     */
    String urlEdicion(String ISBN);

    /**
     * Método para devolver la descripcion de un libro
     * El libro se busca por su ISBN
     * @param ISBN El ISBN del libro
     * @return la descripcion del libro
     */
    String descripcionEdicion(String ISBN);
}
