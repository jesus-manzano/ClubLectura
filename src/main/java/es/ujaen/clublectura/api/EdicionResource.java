package es.ujaen.clublectura.api;

import es.ujaen.clublectura.model.Edicion;
import es.ujaen.clublectura.model.dao.EdicionDAO;
import es.ujaen.clublectura.qualifiers.DAOMap;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("edicion") //Acceso /api/edicion
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped //Ojo, javax.enterprise.context.RequestScoped
public class EdicionResource {
    @Inject
    @DAOMap
    private EdicionDAO edicionesDAO;

    @GET
    public List<Edicion> listado() {
        return edicionesDAO.buscaTodos();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevaEdicion( @Valid Edicion e ) {
        edicionesDAO.crea(e);
        return Response.ok(e).build();
    }
}