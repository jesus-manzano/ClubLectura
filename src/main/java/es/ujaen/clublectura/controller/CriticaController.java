package es.ujaen.clublectura.controller;

import es.ujaen.clublectura.model.Critica;
import es.ujaen.clublectura.model.dao.CriticaDAO;
import es.ujaen.clublectura.qualifiers.DAOJpa;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("ctrCritica")
@ViewScoped
public class CriticaController implements Serializable {

    @Inject @DAOJpa
    private CriticaDAO criticas;

    private Critica critica;

    public CriticaController() {
        critica= new Critica();
    }

    public void enviar (String isbn, String user){
        critica.setIsbnCritica(isbn);
        critica.setUserCritica(user);
        criticas.crea(critica);
    }

    public String elimina (Integer id){
        criticas.elimina(id);
        return "borrado";
    }

    public List<Critica> buscaPorIsbn(String isbn){
        return criticas.buscaPorIsbn(isbn);
    }

    public List<Critica> getCriticas() {
        return criticas.buscaTodos();
    }
    public void setCriticas(CriticaDAO criticas) {
        this.criticas = criticas;
    }

    public Critica getCritica() {
        return critica;
    }
    public void setCritica(Critica critica) {
        this.critica = critica;
    }
}
