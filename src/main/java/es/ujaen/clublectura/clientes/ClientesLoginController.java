package es.ujaen.clublectura.clientes;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Logger;

@ViewScoped
@Named("ctrlLoginClientes")
public class ClientesLoginController implements Serializable {
    private static final Logger logger = Logger.getLogger(ClientesLoginController.class.getName());

    @Inject
    FacesContext fc;

    //SecurityContext and ExternalContext needed for programatic authentication
    @Inject
    SecurityContext sc;

    @Inject
    ExternalContext ec;

    @Inject
    HttpServletRequest request; //needed for logout

    //Sample logout action for Form/CustomForm Authentication
    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        //ec.invalidateSession();
        return "/index?faces-redirect=true";
    }
}
