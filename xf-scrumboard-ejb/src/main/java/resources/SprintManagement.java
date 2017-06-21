/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Sprint;
import entities.SprintDTO;
import entities.Project;
import entities.Task;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import weakentity.SprintProject;
import weakentity.SprintTask;

/**
 *
 * @author Isuru
 */

@Stateless(name="SprintManagement")
public class SprintManagement extends EntityManagement<SprintDTO,Sprint> implements SprintManagementRemote {

    @EJB(beanName="SprintFacade")
    private FacadeLocal<Sprint> sprintFacade;

    @Override
    public FacadeLocal<Sprint> getFacade() {
        return sprintFacade;
    }
    
    @Override
    public Sprint DTO2DAO(SprintDTO myDTO){
        Sprint toReturn = new Sprint();
        if(myDTO==null) return toReturn;
        toReturn.setId(myDTO.getId());
        
        if(myDTO.getProject()!=null){
            SprintProject dto = myDTO.getProject();
            Project dao = new Project();
            dao.setId(dto.getId());
            dao.setName(dto.getName());
            dao.setRelease(dto.getRelease());
            toReturn.setProjectid(dao);
        }
        
        if(myDTO.getTasks() != null && myDTO.getTasks().size()>0){
            List<SprintTask> dtos = myDTO.getTasks();
            toReturn.setTaskCollection(new ArrayList());
            for(SprintTask dto : dtos){
                Task dao = new Task();
                dao.setId(dto.getId());
                dao.setName(dto.getName());
                dao.setDue(dto.getDue());
                toReturn.getTaskCollection().add(dao);
            }
        }
        
        return toReturn;
    }
    
    @Override
    public SprintDTO DAO2DTO(Sprint myDAO){
        SprintDTO toReturn = new SprintDTO();
        if(myDAO==null) return toReturn;
        toReturn.setId(myDAO.getId());
        toReturn.setRelease(myDAO.getRelease());
        if(myDAO.getProjectid()!=null){
            Project dao = myDAO.getProjectid();
            SprintProject dto = new SprintProject();
            dto.setId(dao.getId());
            dto.setName(dao.getName());
            dto.setRelease(dao.getRelease());
            toReturn.setProject(dto);
        }
        if(myDAO.getTaskCollection()!=null){
            Collection<Task> daos = myDAO.getTaskCollection();
            toReturn.setTasks(new ArrayList());
            for(Task dao : daos){
                SprintTask dto = new SprintTask();
                dto.setId(dao.getId());
                dto.setName(dao.getName());
                dto.setDue(dao.getDue());
                toReturn.getTasks().add(dto);
            }
        }
        return toReturn;
    }
    
}
