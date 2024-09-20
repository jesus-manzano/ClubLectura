package es.ujaen.clublectura;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
/*
@EmbeddedIdentityStoreDefinition({
        @Credentials( callerName = "admin", password = "secret1", groups = {"ADMINISTRADORES"}),
        @Credentials( callerName = "user", password = "secret2", groups = {"USUARIOS"})
})
 */

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml?error",
                useForwardToLogin = false
        )
)
@ApplicationScoped
@FacesConfig
public class AppConfig {}
