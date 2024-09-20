package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Critica;
import es.ujaen.clublectura.qualifiers.DAOMap;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class CriticaDAOMap implements CriticaDAO, Serializable {

    private Map<Integer, Critica> mapaCritica;
    private int idCliente=1;

    public CriticaDAOMap(){
        mapaCritica = new HashMap<>();
    }

    @Override
    public boolean crea(Critica critica){
        critica.setId(idCliente++);
        mapaCritica.put(critica.getId(), new Critica(critica));
        return true;
    }

    @Override
    public boolean elimina(Integer id) {
        boolean result=false;
        if (mapaCritica.containsKey(id) ) {
            mapaCritica.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<Critica> buscaTodos(){
        return mapaCritica.values().stream().collect(Collectors.toList());
    }

    @Override
    public Critica buscaId(Integer id) {
        return mapaCritica.get(id);
    }

    @Override
    public List<Critica> buscaPorIsbn(String isbn){
        return null;
    }

    @Override
    public boolean guarda(Critica c) {
        boolean result=false;
        if (mapaCritica.containsKey(c.getId())) {
            Critica nc=new Critica(c);
            mapaCritica.replace(c.getId(),c);
            result=true;
        }
        return result;
    }

    @Override
    public int numCritica() {
        return mapaCritica.size();
    }
}
