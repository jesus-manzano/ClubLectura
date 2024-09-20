//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package es.ujaen.clublectura.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Edicion {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) //Auto-incremental
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-incremental
    private Integer idEdicion;

    private Integer numero;

    @Pattern(regexp = "\\d{10}(\\d{3})?",message = "El ISBN debe tener 10 o 13 dígitos")
    private String isbn;

    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}",message = "La fecha debe tener este formato: dd/mm/aaaa")
    private String fecha;

    @Pattern(regexp = "\\w{9,}",message = "El editorial debe tener como minimo 9 letras")
    private String editorial;

    private Integer paginas;

    @Pattern(regexp = "\\w{4,}",message = "El tipo debe tener 4 letras o mas")
    private String tipo;

    /**
     * Constructor parametrizado de la clase Edicion
     * @param _id El id de la edición
     * @param _isbn El ISBN de la edición
     * @param _numero El numero de la edición
     * @param _fecha La fecha de la edición
     * @param _editorial El editorial de la edición
     * @param _npaginas El número de paginas de la edición
     * @param _tipo El tipo de edición
     */
    public Edicion(Integer _id, String _isbn, Integer _numero, String _fecha,
                   String _editorial, Integer _npaginas, String _tipo) {
        this.idEdicion = _id;
        this.numero = _numero;
        this.isbn = _isbn;
        this.fecha = _fecha;
        this.editorial = _editorial;
        this.paginas = _npaginas;
        this.tipo = _tipo;
    }

    /**
     * Constructor de copia de la clase Edicion
     * @param e la nueva edición
     */
    public Edicion(Edicion e) {
        this.idEdicion = e.getId();
        this.numero = e.getNumero();
        this.isbn = e.getIsbn();
        this.fecha = e.getFecha();
        this.editorial = e.getEditorial();
        this.paginas = e.getPaginas();
        this.tipo = e.getTipo();
    }

    /**
     * Constructor por defecto de la clase Edicion
     */
    public Edicion() {
    }

    /**
     * Método get para devolver el Id de la edicion
     * @return IdEdicion
     */
    public Integer getId() {return this.idEdicion;}

    /**
     * Método set para modificar el id de la edición
     * @param id el nuevo idEdicion
     */
    public void setId(Integer id) {this.idEdicion = id;}

    /**
     * Método get para devolver el número de edición
     * @return numero de edición
     */
    public Integer getNumero() {
        return this.numero;
    }

    /**
     * Método set para modificar el número de la edición
     * @param n el nuevo número de edición
     */
    public void setNumero(Integer n) {
        this.numero = n;
    }

    /**
     * Método get para devolver el ISBN de una edición
     * @return ISBN de la edición
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Método set para modificar el ISBN de una edición
     * @param _isbn el nuevo ISBN
     */
    public void setIsbn(String _isbn) {
        this.isbn = _isbn;
    }

    /**
     * Método get para devolver la fecha de la edición
     * @return fecha de la edición
     */
    public String getFecha() {
        return this.fecha;
    }

    /**
     * Método set para modificar la fecha de la edición
     * @param date la nueva fecha
     */
    public void setFecha(String date) {
        this.fecha = date;
    }

    /**
     * Método get para devolver el editorial de la edición
     * @return editorial de la edición
     */
    public String getEditorial() {
        return this.editorial;
    }

    /**
     * Método set para modificar el editorial de la edición
     * @param e el nuevo editorial
     */
    public void setEditorial(String e) {
        this.editorial = e;
    }

    /**
     * Método get para devolver el número de paginas de la edición
     * @return el número de paginas
     */
    public Integer getPaginas() {
        return this.paginas;
    }

    /**
     * Método set para modificar el número de paginas de la edición
     * @param n el nuevo número de paginas
     */
    public void setPaginas(Integer n) {
        this.paginas = n;
    }

    /**
     * Método get para devolver el tipo de edición
     * @return el tipo de la edición
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Método set para modificar el tipo de la edición
     * @param t el nuevo tipo
     */
    public void setTipo(String t) {
        this.tipo = t;
    }
}
