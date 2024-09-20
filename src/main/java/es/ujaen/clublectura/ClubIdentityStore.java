package es.ujaen.clublectura;

import es.ujaen.clublectura.model.dao.ClientesDAOJPA;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ClubIdentityStore implements IdentityStore {

    @Inject
    private ClientesDAOJPA clientes;

    private static final Logger logger = Logger.getLogger(ClubIdentityStore.class.getName());

    public ClubIdentityStore() {}

    @Override
    public CredentialValidationResult validate(Credential credential) {
        return validate((UsernamePasswordCredential) credential);
    }

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        String username = usernamePasswordCredential.getCaller(); //Retrieve credentials from auth method
        String password = usernamePasswordCredential.getPasswordAsString();

        if (password.equals(clientes.getPwd(username))) { //Check for valid credentials in back-end
            Set<String> roles = new HashSet<>();
            roles.add(clientes.getRol(username)); //Get roles for valid user from back-end
            logger.log(Level.SEVERE, "Esta correcto: " + clientes.getPwd(username));
            return new CredentialValidationResult(username, roles); //Authenticate user in Application Server
        }

        logger.log(Level.SEVERE, "No esta correcto: " + clientes.getPwd(username));

        return CredentialValidationResult.INVALID_RESULT; //Credentials invalid. Deny access
    }
}
