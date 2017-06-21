/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import exception.InvalidTokenException;
import exception.RestrictedAccessException;
import java.text.ParseException;
import java.util.Date;
import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import model.Secured;
import models.Credentials;
import models.SecurityToken;
import services.AuthenticationManagementServiceRemote;

/**
 *
 * @author Isuru
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
@Stateless
public class SecurityTokenFilter implements ContainerRequestFilter {

    @EJB
    private AuthenticationManagementServiceRemote authenticationManagement;
    
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws NotAuthorizedException, InvalidTokenException,RestrictedAccessException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        
        SecurityToken st = null;
        String token = authorizationHeader.substring("Token ".length()).trim();
        try {
            st = validateToken(token);
        } catch (InvalidTokenException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
            throw e;
        }
        
        System.out.print("Authentication service is "+(authenticationManagement==null?"not":"")+" reachacle");

        Credentials cr = new Credentials();
        cr.setPrivilage(st.getGroup());
        requestContext.setSecurityContext(cr);
        
        System.out.print("Current user is "+(requestContext.getSecurityContext().isUserInRole("XF-APP-USER")));
        
        if(!cr.isUserInRole("XF-APP-ADMIN")){
             throw new RestrictedAccessException("requested resource is restricted");
        }
    }

    private SecurityToken validateToken(String token) throws InvalidTokenException {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        SecurityToken toCheck;
        try {
            toCheck = SecurityToken.fromString(token);
        } catch (ParseException e) {
            throw new InvalidTokenException(e.getMessage());
        } catch (IllegalStateException e) {
            throw new InvalidTokenException("token not issued by the host");
        }
        if (isExpired(toCheck)) {
            throw new InvalidTokenException("authentication is expired, please reaunthenticate");
        }
        
        System.out.println("GROUP = " + toCheck.getGroup());
        return toCheck;
    }

    private boolean isExpired(SecurityToken toCheck) {
        Date today = new Date();
        return (toCheck.getExpire().before(today));
    }

}
