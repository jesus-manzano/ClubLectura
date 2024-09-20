package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Favorito;
import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.qualifiers.DAOMap;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class FavoritoDAOMap implements FavoritoDAO, Serializable {

    private HashMap<Integer, Favorito> favoritos;
    private Integer idFavorito = 1;

    public FavoritoDAOMap(){
        favoritos = new HashMap<>();
    }

    @Override
    public Favorito buscaId(Integer id){
        Favorito localizado = favoritos.get(id);
        if(localizado != null){
            localizado = new Favorito(localizado);
        }
        return localizado;
    }

    @Override
    public List<Favorito> buscaTodos(){
        return favoritos.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Libro> buscaPorUser(String user){
        return null;
    }

    @Override
    public void borra(String user, String isbn){
    }

    @Override
    public boolean crea(Favorito fav){
        Favorito nf = new Favorito(fav);
        nf.setIdFavorito(idFavorito);
        favoritos.put(idFavorito, nf);
        fav.setIdFavorito(idFavorito);
        idFavorito++;
        return true;
    }

    @Override
    public boolean guarda(Favorito f){
        boolean result=false;
        if(favoritos.containsKey(f.getIdFavorito())){
            Favorito nf = new Favorito(f);
            favoritos.replace(f.getIdFavorito(), f);
            result=true;
        }
        return result;
    }

    @Override
    public boolean elimina(Integer id) {
        boolean result = false;
        if (favoritos.containsKey(id)){
            favoritos.remove(id);
            result=true;
        }
        return result;
    }

    @Override
    public int numFavoritos() { return favoritos.size(); }
}
