package es.ujaen.clublectura.api;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class LibrosValidationExceptionManager
        implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<Object> errors = new ArrayList<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            //attribute name is the last part, e.g. method.arg0.propname
            String[] parts=cv.getPropertyPath().toString().split("\\.");
            Object m = new Object () { //Temp anonymous inner class
                public String name = parts[parts.length-1];
                public String message = cv.getMessage();
            };
            errors.add(m);
        };
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}
