/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Base64;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import models.Credentials;
import javax.naming.AuthenticationException;

@Stateless
@Path("/authentication")
public class AuthenticationService {

    @EJB
    private AuthenticationManagementServiceRemote authenticationManagement;

    @Context
    private UriInfo context;
    
    @Context
    private SecurityContext sc;

    /**
     * Creates a new instance of AuthenticationResource
     */
    public AuthenticationService() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password") String password ) throws Exception{
        if(username==null || password==null) return Response.status(Response.Status.BAD_REQUEST).build();
        Credentials authnticatedCredentials = authenticate(username, password);
        return Response.ok().entity(authnticatedCredentials).build();
    }

    private Credentials authenticate(String username, String hashedpassword) throws AuthenticationException {
        Credentials userContext = null;
        userContext = authenticationManagement.authenticateUser(username, hashedpassword);
        return userContext;
    }

 
    /**
     * PUT method for updating or creating an instance of AuthenticationManagementService
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(String content) {
    }

    /**
     * DELETE method for logout
     *
     * @param content representation for the resource
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void logout(String content) {
    }

    @GET
    public String check() {
        return "Authorization services are " + ((authenticationManagement == null) ? "not " : "") + "available" ;
    }
}
