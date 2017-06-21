/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Isuru
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.xfastgames.VerificationService.class);
        resources.add(exception.AuthenticationExceptionMapper.class);
        resources.add(exception.InvalidTokenExceptionMapper.class);
        resources.add(exception.NotAuthorizedExceptionMapper.class);
        resources.add(exception.NotFoundExceptionMapper.class);
        resources.add(exception.NotIdentifiableExceptionMapper.class);
        resources.add(exception.RegistrationExceptionMapper.class);
        resources.add(exception.RestrictedAccessExceptionMapper.class);
        resources.add(filter.CORSFilter.class);
        resources.add(filter.SecurityTokenFilter.class);
        resources.add(resource.MemberResource.class);
        resources.add(resource.ProjectResource.class);
        resources.add(resource.RootResource.class);
        resources.add(resource.SprintResource.class);
        resources.add(resource.TaskResource.class);
        resources.add(resource.TeamResource.class);
        resources.add(services.AuthenticationService.class);
        resources.add(services.RegistrationService.class);
    }
    
}
