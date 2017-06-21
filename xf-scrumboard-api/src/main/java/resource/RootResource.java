/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import java.text.ParseException;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import model.Secured;
import models.SecurityToken;
import services.AuthorisationServiceLocal;

/**
 * REST Web Service
 *
 * @author Isuru
 */

@Stateless
@Path("resources")
public class RootResource {

    @EJB
    private AuthorisationServiceLocal authorisationService;

    @Context
    private UriInfo context;
    
    @Context
    private ContainerRequestContext request;
    
    public void authenticate() {
        String token = request.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Token ".length()).trim();
        try{ request.setSecurityContext(authorisationService.getSecureUserForToken(SecurityToken.fromString(token)));}catch(Exception e) {}
    }
    
    @Path("members")
    //@RolesAllowed({"XF-APP-USER"})
    public MemberResource admin(@Context UriInfo uriInfo) throws ParseException {
        authenticate();
        return new MemberResource();
    }

}
