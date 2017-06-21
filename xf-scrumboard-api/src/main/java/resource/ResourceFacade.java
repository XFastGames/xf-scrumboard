package resource;

import resources.ManagementRemote;
import entities.Identifiable;
import entities.MemberDTO;
import exception.NotFoundException;
import exception.NotIdentifiableException;
import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Isuru
 */
public abstract class ResourceFacade<T> {
    
    public abstract ManagementRemote<T> getManager();
    
    public Response create(T entity, @Context UriInfo uriInfo) {
        Identifiable created = (Identifiable) getManager().create(entity);
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId())).build();
        return Response.created(location).entity(created).build();
    }

    public Response edit(Long id, T entity, @Context UriInfo uriInfo) {
        T found = (T) getManager().find(id);
        if(found==null) throw new NotFoundException("resource not found");
        Identifiable toUpdate = (Identifiable) found;
        if(toUpdate == null) throw new NotIdentifiableException("resource lacks an id");
        ((Identifiable)entity).setId(id);
        getManager().edit((T)entity);
        URI location = uriInfo.getAbsolutePathBuilder().build();
        return Response.noContent().location(location).build();
    }

    public Response remove(Long id, @Context UriInfo uriInfo) {
        T found = (T) getManager().find(id);
        if(found==null) throw new NotFoundException("resource not found");
        Identifiable toRemove = (Identifiable) found;
        if(toRemove == null) throw new NotIdentifiableException("resource lacks an id");
        ((Identifiable)found).setId(id);
        getManager().remove(getManager().edit(getManager().find(id)));
        return Response.noContent().build();
    }
    
    public Response find(Long id, @Context UriInfo uriInfo) {
        T found = (T) getManager().find(id);
        if(found==null) throw new NotFoundException("resource not found");
        Identifiable ided = (Identifiable) found;
        if(ided == null) throw new NotIdentifiableException("resource lacks an id");
        URI location = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(ided).location(location).build();
    }

    public abstract Response findAll(UriInfo uriInfo);

    public abstract Response findRange(Long from,Long to, UriInfo uriInfo) ;

    public Response count(@Context UriInfo uriInfo) {
        int count = getManager().count();
        URI location = uriInfo.getAbsolutePath();
        return Response.ok(count).location(location).build();
    }
    
}
