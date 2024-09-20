package es.ujaen.clublectura.Favorito;


import es.ujaen.clublectura.clientes.ClientesDAOJPA;
import es.ujaen.clublectura.libros.Libro;
import es.ujaen.clublectura.libros.LibrosDAOJpa;
import es.ujaen.clublectura.qualifiers.DAOMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("ctrlFavoritos")
@ViewScoped
public class FavoritosController implements Serializable {
    @Inject
    private ClientesDAOJPA clientes;

    @Inject
    private LibrosDAOJpa libros;

    @Inject
    private FavoritoDAOJPA favoritos;

    private Favorito favorito;

    public FavoritosController(){

    }

    private final Logger logger = Logger.getLogger(FavoritosController.class.getName());

    @PostConstruct
    public void init(){
        favorito = new Favorito();
    }






    public List<Favorito> getFavoritos() { return favoritoDAO.buscaTodos(); }

    public Favorito getFavorito() { return favorito; }

    public void recupera(){
        favorito = favoritoDAO.buscaID(favorito.getIdFavorito());
        if (favorito == null){
            fc.addMessage(null, new FacesMessage("El libro indicado no existe"));
        }
    }

    public String crea(){
        favorito.setIdFavorito(0);
        favoritoDAO.crea(favorito);
        return "visualiza?faces-redirect=true&id=" + favorito.getIdFavorito();
    }

    public String guarda(){
        favoritoDAO.guarda(favorito);
        return "visualiza?faces-redirect=true&id=" + favorito.getIdFavorito();
    }

    public String borra(){
        favoritoDAO.borra(favorito.getIdFavorito());
        return "listado";
    }

    public String borra(Favorito fav){
        favoritoDAO.borra(fav.getIdFavorito());
        return "listado";
    }

    public void editRow(Favorito fav){
        this.favorito=fav;
    }

    public void cancelEditRow(){ this.favorito = new Favorito(); }

    public void actualizaFavorito(){
        favoritoDAO.guarda(favorito);
        cancelEditRow();
    }
}