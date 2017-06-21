/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xfastgames;

import exception.NotFoundException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import services.VerificationManagementServiceRemote;

/**
 * REST Web Service
 *
 * @author Isuru
 */

@Stateless
@Path("verify")
public class VerificationService {

    @EJB
    private VerificationManagementServiceRemote verificationManagementService;

    @Context
    private UriInfo context;

    
    /**
     * Creates a new instance of VerificationService
     */
    public VerificationService() {
    }

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String check() {
        return "Verfification services are "+((verificationManagementService==null)?"not ":"")+"online";
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verify(@FormParam("key") String key) {
        if(verificationManagementService.verifyUser(key))
            return Response.ok().build();
        else
            throw new NotFoundException();
    }
}
