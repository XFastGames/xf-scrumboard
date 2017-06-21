/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import entities.MemberDTO;
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
import model.Secured;
import resources.MemberManagementRemote;

/**
 *
 * @author Isuru
 */
@Stateless
@Path("/members")
public class MemberResource extends ResourceFacade<MemberDTO>{

    @EJB(name="AuthenticationManagement")
    private MemberManagementRemote memberrManagement;

    @Override
    public MemberManagementRemote getManager() {
        return memberrManagement;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(MemberDTO entity, @Context UriInfo uriInfo) {
        return super.create(entity,uriInfo);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") String id, MemberDTO entity, @Context UriInfo uriInfo) {
        return super.edit(Long.valueOf(id),entity,uriInfo);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") String id, @Context UriInfo uriInfo) {
        return super.remove(Long.valueOf(id),uriInfo);
    }

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
        List<MemberDTO >found = (List<MemberDTO>) getManager().findAll();
        GenericEntity<List<MemberDTO>> wrapped = new GenericEntity<List<MemberDTO>>(found) {};
        URI location = uriInfo.getAbsolutePath();
        return Response.ok().entity(wrapped).location(location).build();
    }

    @Override
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Long from, @PathParam("to") Long to, @Context UriInfo uriInfo) {
        List<MemberDTO> found = (List<MemberDTO>) getManager().findRange(new int[]{from.intValue(),to.intValue()});
        GenericEntity<List<MemberDTO>> wrapped = new GenericEntity<List<MemberDTO>>(found) {};
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
