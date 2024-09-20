package es.ujaen.clublectura.controller;

import es.ujaen.clublectura.model.Favorito;
import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.model.dao.FavoritoDAO;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("ctrlFavoritos")
@ViewScoped
public class FavoritosController implements Serializable {
    private final Logger logger = Logger.getLogger(FavoritosController.class.getName());

    @Inject @DAOJpa
    private FavoritoDAO favoritos;

    private Favorito favorito;

    public FavoritosController(){
        favorito = new Favorito();
    }

    public String enviar(String user, String isbn) {
        favorito.setUserFavorito(user);
        favorito.setIsbnFavorito(isbn);
        if(favoritos.crea(favorito))
            return "/identificados/favoritos?faces-redirect=true";
        return null;
    }

    public void elimina(String user, String isbn){
        favoritos.borra(user, isbn);
    }

    public List<Libro> buscaPorUser(String user){
        return favoritos.buscaPorUser(user);
    }

    public List<Favorito> getFavoritos() {
        return favoritos.buscaTodos();
    }

    public void setFavoritos(FavoritoDAO favoritos) {
        this.favoritos = favoritos;
    }

    public Favorito getFavorito() {
        return favorito;
    }

    public void setFavorito(Favorito favorito) {
        this.favorito = favorito;
    }
}