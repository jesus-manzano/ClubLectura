package es.ujaen.clublectura.Favorito;

import es.ujaen.clublectura.qualifiers.DAOMap;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class FavoritoDAOMap implements FavoritoDAO, Serializable {

    private ArrayList<Favorito> favoritos = null;
    private Integer idFavorito = 1;

    public FavoritoDAOMap(){
        if (favoritos == null) {
            favoritos = new ArrayList<Favorito>();
            favoritos.add(idFavorito++,  new Favorito(idFavorito++,"1234567890", "PURGATORIO",
                    "Hace treinta y cinco años, Imanol Azkarate fue secuestrado y" +
                            " ejecutado, pero sus dos asesinos nunca fueron detenidos ni identificados. " +
                            "Uno de ellos, Josu Etxebeste, conocido restaurador guipuzcoano, guardó todas" +
                            " las cartas y dibujos que hizo el secuestrado durante el cautiverio. Ahora, " +
                            "ha decidido confesar su crimen y dar todo ese material a Alasne, la hija de la" +
                            " víctima, y entregarse al comisario Ignacio Sánchez, el policía que investigó" +
                            " el secuestro. Sin embargo, Josu solo confesará si Sánchez reconoce a su vez que" +
                            " fue un despiadado torturador. Mientras pugnan por conciliar su pasado armado con" +
                            " un presente sin rencor ni violencia, los resortes durmientes de la Organización" +
                            " se movilizan. Antiguos militantes que, como Etxebeste, nunca fueron detenidos y " +
                            "que no tienen ninguna intención de confesar y cambiar sus cómodas vidas en la " +
                            "Euskadi del posconflicto tratarán de detener ese acercamiento por todos los medios " +
                            "posibles.", "https://imagessl2.casadellibro.com/a/l/t5/12/9788401028212.jpg"));
        }
    }

    @Override
    public Favorito buscaID(Integer id){
        Favorito localizado = favoritos.get(id);
        if(localizado != null){
            localizado = new Favorito(localizado);
        }
        return localizado;
    }

    @Override
    public Favorito buscaByISBN(String isbn){
        Favorito localizado = null;
        for (Favorito f: favoritos.values()){
            if (f.getIsbn_favorito().equals(isbn)){
                localizado = f;
                break;
            }
        }
        if (localizado != null){
            localizado = new Favorito(localizado);
        }
        return localizado;
    }

    @Override
    public List<Favorito> buscaTodos(){
        return favoritos.values().stream().collect(Collectors.toList());
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
    public boolean borra(Integer id){
        boolean result = false;
        if (favoritos.containsKey(id)){
            favoritos.remove(id);
            result=true;
        }
        return result;
    }

    public int numFavoritos() { return favoritos.size(); }
}
