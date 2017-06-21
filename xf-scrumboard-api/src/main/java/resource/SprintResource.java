/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import resources.ProjectManagementRemote;
import resources.SprintManagementRemote;
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
import entities.ProjectDTO;
import entities.SprintDTO;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.SecurityContext;
import model.Secured;

/**
 *
 * @author Isuru
 */
@Stateless
@Path("/sprints")
public class SprintResource extends ResourceFacade<SprintDTO> {

    @EJB
    private TaskResource taskResource;

    @EJB
    private SprintManagementRemote sprintManagement;
    
    

    @Override
    public SprintManagementRemote getManager() {
        return sprintManagement;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(SprintDTO entity, @Context UriInfo uriInfo) {
        return super.create(entity, uriInfo);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") String id, SprintDTO entity, @Context UriInfo uriInfo) {
        return super.edit(Long.valueOf(id), entity, uriInfo);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") String id, @Context UriInfo uriInfo) {
        return super.remove(Long.valueOf(id), uriInfo);
    }

    @GET
    @Path("test")
    @Produces({MediaType.TEXT_PLAIN})
    public String test() {
        return "you are allowed!";
    }
        
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") String id, @Context UriInfo uriInfo,@Context SecurityContext sc) {
        System.out.println(sc.isUserInRole("XF-APP-USER"));
        return super.find(Long.valueOf(id), uriInfo);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll(@Context UriInfo uriInfo) {
        List<SprintDTO> found = (List<SprintDTO>) getManager().findAll();
        GenericEntity<List<SprintDTO>> wrapped = new GenericEntity<List<SprintDTO>>(found) {};
        URI location = uriInfo.getAbsolutePath();
        return Response.ok().entity(wrapped).location(location).build();
    }

    @Override
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Long from, @PathParam("to") Long to, @Context UriInfo uriInfo) {
        List<SprintDTO> found = (List<SprintDTO>) getManager().findRange(new int[]{from.intValue(), to.intValue()});
        GenericEntity<List<SprintDTO>> wrapped = new GenericEntity<List<SprintDTO>>(found) {};
        URI location = uriInfo.getAbsolutePath();
        return Response.ok().entity(wrapped).location(location).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST(@Context UriInfo uriInfo) {
        return super.count(uriInfo);
    }
    
    @GET
    @Path("{sprintid}/tasks")
    public Response tasks(@PathParam("sprintid") Long id, @Context UriInfo uriInfo) {
        return taskResource.find(id, uriInfo);
    }
    
}
