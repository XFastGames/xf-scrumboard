/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Memberr;
import entities.Project;
import entities.ProjectDTO;
import entities.Sprint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import weakentity.ProjectMember;
import weakentity.ProjectSprint;

/**
 *
 * @author Isuru
 */

@Stateless(name="ProjectManagement")
public class ProjectManagement extends EntityManagement<ProjectDTO,Project> implements ProjectManagementRemote {

    @EJB(beanName="ProjectFacade")
    private FacadeLocal<Project> projectFacade;
    
    @Override
    public FacadeLocal<Project> getFacade() {
        return projectFacade;
    }
    
    @Override
    public Project DTO2DAO(ProjectDTO myDTO){
        Project toReturn = new Project();
        if(myDTO==null) return toReturn;
        
        toReturn.setId(myDTO.getId());
        toReturn.setName(myDTO.getName());
        toReturn.setRelease(myDTO.getRelease());
        
        
        return toReturn;
    }
    
    @Override
    public ProjectDTO DAO2DTO(Project myDAO){
        ProjectDTO toReturn = new ProjectDTO();
        if(myDAO==null) return toReturn;
        
        toReturn.setId(myDAO.getId());        
        toReturn.setName(myDAO.getName());
        toReturn.setRelease(myDAO.getRelease());
        
        if(myDAO.getTeamid()!=null){
            if(  myDAO.getTeamid().getMemberrCollection()!=null && 
                 myDAO.getTeamid().getMemberrCollection().size()>0){
                Collection<Memberr> doas = myDAO.getTeamid().getMemberrCollection();
                toReturn.setTeam(new ArrayList());
                for(Memberr dao : doas){
                    ProjectMember dto = new ProjectMember();
                    dto.setId(dao.getId());
                    dto.setName(dao.getName());
                    toReturn.getTeam().add(dto);
                }
            }
        }
        
        if( myDAO.getSprintCollection()!=null &&
            myDAO.getSprintCollection().size()>0){
            Collection<Sprint> doas = myDAO.getSprintCollection();
            toReturn.setSprints(new ArrayList());
            for(Sprint doa : doas){
                ProjectSprint dto = new ProjectSprint();
                dto.setId(doa.getId());               
                dto.setRelease(doa.getRelease());
                toReturn.getSprints().add(dto);
            }
        }
        return toReturn;
    }
    
}
