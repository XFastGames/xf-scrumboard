/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exception.RegistrationException;
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
import models.RegisterRequest;


@Stateless
@Path("/register")
public class RegistrationService {

    @EJB
    private VerificationManagementServiceRemote verificationManagementService;


    @EJB
    private RegistrationManagementServiceRemote registrationManagementService;

    
    
    @Context
    private UriInfo context;
    
    @Context
    private SecurityContext sc;

    /**
     * Creates a new instance of AuthenticationResource
     */
    public RegistrationService() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response register(@FormParam("name") String name, @FormParam("username") String username, @FormParam("password") String password, @FormParam("seqquestion") String seqquestion , @FormParam("seqanswer") String seqanswer) throws RegistrationException{
        if(name==null || username==null || password==null || seqquestion==null || seqanswer==null) throw new RegistrationException("missing data"); 
        RegisterRequest request = new RegisterRequest(name,username,password,seqquestion,seqanswer);
        Response toReturn = null;
        try{
            toReturn = registrationManagementService.registerUser(request);
            System.out.println("Registed Sucessfully");
            verificationManagementService.sendVerificationEmail(username);
        }catch(Exception e){
            throw new RegistrationException(e.getMessage());
        }
        return toReturn;
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
        return "Registration services are " + ((registrationManagementService == null) ? "not " : "") + "available" ;
    }
}
