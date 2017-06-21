/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import javax.naming.AuthenticationException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import model.ErrorMessage;

/**
 *
 * @author Isuru
 */
@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException>{

    @Override
    public Response toResponse(NotAuthorizedException exception) {
        ErrorMessage error = new ErrorMessage(Response.Status.FORBIDDEN.getStatusCode(), exception.getMessage(), "http://xfastgames.com");
        Response toReturn = Response.status(Response.Status.FORBIDDEN).entity(error).build();
        return toReturn;
    }
    
}
