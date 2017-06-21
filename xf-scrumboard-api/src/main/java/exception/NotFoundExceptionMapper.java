/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import model.ErrorMessage;

/**
 *
 * @author Isuru
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>{

    @Override
    public Response toResponse(NotFoundException exception) {
        ErrorMessage error = new ErrorMessage(Response.Status.NOT_FOUND.getStatusCode(),exception.getMessage(), "http://xfastgames.com");
        Response toReturn = Response.status(Response.Status.NOT_FOUND).entity(error).build();
        return toReturn;
    }
    
}
