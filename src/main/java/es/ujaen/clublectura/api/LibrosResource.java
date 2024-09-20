package es.ujaen.clublectura.api;

import es.ujaen.clublectura.model.Libro;
import es.ujaen.clublectura.model.dao.LibrosDAO;
import es.ujaen.clublectura.qualifiers.DAOMap;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("libro") //Acceso /api/libros
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped //Ojo, javax.enterprise.context.RequestScoped
public class LibrosResource {
    @Inject
    @DAOMap
    private LibrosDAO librosDAO;

    @GET
    public List<Libro> listado() {
        return librosDAO.buscaTodos();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoLibro(@Valid Libro l ) {
        librosDAO.crea(l);
        return Response.ok(l).build();
    }

}