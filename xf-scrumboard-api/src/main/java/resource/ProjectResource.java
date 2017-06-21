/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import resources.ProjectManagementRemote;
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
import javax.annotation.security.RolesAllowed;
import model.Secured;

/**
 *
 * @author Isuru
 */
@Stateless
@Path("/projects")
public class ProjectResource extends ResourceFacade<ProjectDTO> {

    @EJB
    private ProjectManagementRemote projectManagement;

    @Override
    public ProjectManagementRemote getManager() {
        return projectManagement;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(ProjectDTO entity, @Context UriInfo uriInfo) {
        return super.create(entity, uriInfo);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") String id, ProjectDTO entity, @Context UriInfo uriInfo) {
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
        return "testing";
    }

    @Secured
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") String id, @Context UriInfo uriInfo) {
        return super.find(Long.valueOf(id), uriInfo);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll(@Context UriInfo uriInfo) {
        List<ProjectDTO> found = (List<ProjectDTO>) getManager().findAll();
        GenericEntity<List<ProjectDTO>> wrapped = new GenericEntity<List<ProjectDTO>>(found) {};
        URI location = uriInfo.getAbsolutePath();
        return Response.ok().entity(wrapped).location(location).build();
    }

    @Override
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Long from, @PathParam("to") Long to, @Context UriInfo uriInfo) {
        List<ProjectDTO> found = (List<ProjectDTO>) getManager().findRange(new int[]{from.intValue(), to.intValue()});
        GenericEntity<List<ProjectDTO>> wrapped = new GenericEntity<List<ProjectDTO>>(found) {};
        URI location = uriInfo.getAbsolutePath();
        return Response.ok().entity(wrapped).location(location).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST(@Context UriInfo uriInfo) {
        return super.count(uriInfo);
    }
    
}
