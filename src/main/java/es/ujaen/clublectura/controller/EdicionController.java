//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package es.ujaen.clublectura.controller;

import es.ujaen.clublectura.model.Edicion;
import es.ujaen.clublectura.model.dao.EdicionDAO;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("ctrlEdiciones")
@ViewScoped
public class EdicionController implements Serializable {
    private final Logger logger = Logger.getLogger(EdicionController.class.getName());

    @Inject @DAOJpa
    private EdicionDAO edicionesDAO;

    private Edicion edicion = new Edicion();

    /**
     * Constructor por defecto de la clase EdicionController
     */
    public EdicionController() {}

    /**
     * Método get para devolver la edición
     * @return La edición
     */
    public Edicion getEdicion() {return this.edicion;}

    /**
     * Método set para modificar la edición
     * @param e La nueva edición
     */
    public void setEdicion(Edicion e) {this.edicion = e;}

    /**
     * Método get para devolver todas las ediciones de todos los libros
     * @return Lista de todas las ediciones de todos los libros
     */
    public List<Edicion> getEdiciones() {return this.edicionesDAO.buscaTodos();}

    /**
     * Método set para modificar todas las ediciones
     * @param le Las nuevas ediciones
     */
    public void setEdicionesDAO(EdicionDAO le) {this.edicionesDAO = le;}

    /**
     * Método para crear una nueva edición
     * @return Una ruta a la vista de los detalles de las ediciones si se ha creado correctamente
     */
    public String crea() {
        if (this.edicionesDAO.crea(edicion)){
            return "/edicion/detalleEdicion?faces-redirect=true&id=" + this.edicion.getId();
        }
        return null;
    }

    /**
     * Método para buscar todas ediciones de un libro
     * Se busca el libro por su ISBN
     * @param isbn El ISBN del libro
     * @return Una lista de todas las ediciones
     */
    public List<Edicion> buscaPorISBN(String isbn) {return this.edicionesDAO.buscaPorISBN(isbn);}

    /**
     * Método para obtener los datos de una edición por su ID
     */
    public void recupera() {this.edicion = this.edicionesDAO.buscaId(this.edicion.getId());}

    /**
     * Método para borrar una edición
     * @return Una ruta si se ha borrado correctamente
     */
    public String borra() {
        if (this.edicionesDAO.elimina(edicion.getId())){
            return "/index?faces-redirect=true";
        }
        return "";
    }

    /**
     * Método para devolver el número total de todas las ediciones de todos los libros
     * @return El número total de todas las ediciones de todos los libros
     */
    public int numEdiciones() {return this.edicionesDAO.numEdiciones()-1;}

    /**
     * Método para devolver el número de ediciones de un libro espcifico
     * @return El numero de ediciones de un libro
     */
    public int numEdicionesLibro(String ISBN){return this.edicionesDAO.numEdicionesLibro(ISBN);}

    /**
     * Método para validar que existe el ISBN
     * @return Un lógico que indica que existe o no el ISBN
     */
    public boolean validarISBN(){return this.edicionesDAO.validarISBN(this.edicion.getIsbn());}

    /**
     * Método para validar los datos de una edición
     * @return La vista a donde se debe ir si los datos son validos o nada en otro caso
     */
    public String validar(){
        String view="";
        try {
            if (edicionesDAO.validarISBN(edicion.getIsbn()) &&
                    edicionesDAO.numEdicionesLibro(edicion.getIsbn()) <= edicion.getNumero()){
                if (this.edicionesDAO.crea(edicion)){
                    view = "/edicion/detalleEdicion?faces-redirect=true&id=" + this.edicion.getId();
                }
            }
        } catch (Exception ex) {
            this.logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return view;
    }

    /**
     * Método para devolver el titulo de un libro
     * @return El titulo de un libro
     */
    public String tituloEdicion() {return this.edicionesDAO.tituloEdicion(edicion.getIsbn());}

    /**
     * Método para devolver la URL de un libro
     * @return La URL de un libro
     */
    public String urlEdicion() {return this.edicionesDAO.urlEdicion(edicion.getIsbn());}

    /**
     * Método para devolver la descripción de un libro
     * @return La descripción de un libro
     */
    public String descripcionEdicion() {return this.edicionesDAO.descripcionEdicion(edicion.getIsbn());}
}
