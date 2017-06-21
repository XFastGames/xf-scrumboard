/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import resources.SprintManagementRemote;
import resources.TaskManagementRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.UriInfo;
import entities.TaskDTO;

/**
 *
 * @author Isuru
 */
@Stateless
@Path("/tasks")
public class TaskResource extends ResourceFacade<TaskDTO> {

    @EJB
    private TaskManagementRemote taskManagement;

    @Override
    public TaskManagementRemote getManager() {
        return taskManagement;
    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(TaskDTO entity, @Context UriInfo uriInfo) {
        return super.create(entity, uriInfo);
    }

    @Override
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, TaskDTO entity, @Context UriInfo uriInfo) {
        return super.edit(Long.valueOf(id), entity, uriInfo);
    }

    @Override
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        return super.remove(Long.valueOf(id), uriInfo);
    }

    @Override
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        return super.find(id, uriInfo);
    }
    
    public Response findOf(@PathParam("id") String id, @Context UriInfo uriInfo) {
        return super.find(Long.valueOf(id), uriInfo);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll(@Context UriInfo uriInfo) {
        List<TaskDTO> found = (List<TaskDTO>) getManager().findAll();
        GenericEntity<List<TaskDTO>> wrapped = new GenericEntity<List<TaskDTO>>(found) {};
        URI location = uriInfo.getAbsolutePath();
        return Response.ok().entity(wrapped).location(location).build();
    }

    @Override
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Long from, @PathParam("to") Long to, @Context UriInfo uriInfo) {
        List<TaskDTO> found = (List<TaskDTO>) getManager().findRange(new int[]{from.intValue(), to.intValue()});
        GenericEntity<List<TaskDTO>> wrapped = new GenericEntity<List<TaskDTO>>(found) {};
        URI location = uriInfo.getAbsolutePath();
        return Response.ok().entity(wrapped).location(location).build();
    }

    @Override
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response count(@Context UriInfo uriInfo) {
        return super.count(uriInfo);
    }
    
}
