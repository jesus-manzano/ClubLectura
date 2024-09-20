package es.ujaen.clublectura.model.dao;

import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.qualifiers.DAOMap;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped      //Elegible for Dependency Injection
@DAOMap
public class LibrosDAOMap implements LibrosDAO, Serializable {
    private Map<String, Libro> libros = null;
    public LibrosDAOMap() {
        if (libros == null) {
            libros = new HashMap<>();
            libros.put("2234567890", new Libro("2234567890",
                    "PURGATORIO", "Hace treinta y cinco años, Imanol Azkarate fue " +
                    "secuestrado y ejecutado, pero sus dos asesinos nunca fueron detenidos ni identificados. Uno de ellos, Josu " +
                    "Etxebeste, conocido restaurador guipuzcoano, guardó todas las cartas y dibujos que hizo el secuestrado durante el " +
                    "cautiverio. Ahora, ha decidido confesar su crimen y dar todo ese material a Alasne, la hija de la víctima, y " +
                    "entregarse al comisario Ignacio Sánchez, el policía que investigó el secuestro. Sin embargo, Josu solo confesará " +
                    "si Sánchez reconoce a su vez que fue un despiadado torturador. Mientras pugnan por conciliar su pasado armado con " +
                    "un presente sin rencor ni violencia, los resortes durmientes de la Organización se movilizan. Antiguos militantes " +
                    "que, como Etxebeste, nunca fueron detenidos y que no tienen ninguna intención de confesar y cambiar sus cómodas " +
                    "vidas en la Euskadi del posconflicto tratarán de detener ese acercamiento por todos los medios posibles.",
                    "https://imagessl2.casadellibro.com/a/l/t5/12/9788401028212.jpg"));
        }
    }

    @Override
    public Libro buscaIsbn(String isbn) {
        Libro localizado = libros.get(isbn);
        if (localizado != null) localizado= new Libro(localizado);
        return localizado;
    }

    @Override
    public Libro buscaId(String id) {
        return null;
    }

    @Override
    public List<Libro> buscaTodos() {
        return libros.values().stream().collect(Collectors.toList());
    }

    @Override
    public int numLibros() {
        return libros.size();
    }

    @Override
    public boolean crea(Libro l) {
        libros.put(l.getIsbn() , new Libro(l) );
        return true;
    }

    @Override
    public boolean guarda(Libro c) {
            boolean result=false;
            if (libros.containsKey(c.getIsbn())) {
                Libro nc=new Libro(c);
                libros.replace(c.getIsbn(),c);
                result=true;
            }
            return result;
    }

    @Override
    public boolean elimina(String isbn) {
        boolean result=false;
        if ( libros.containsKey(isbn) ) {
            libros.remove(isbn);
            result = true;
        }
        return result;
    }
}
