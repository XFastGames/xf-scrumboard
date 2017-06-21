/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Memberr;
import entities.Sprint;
import entities.Task;
import entities.TaskDTO;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import weakentity.TaskMember;
import weakentity.TaskSprint;

/**
 *
 * @author Isuru
 */

@Stateless(name="TaskManagement")
public class TaskManagement extends EntityManagement<TaskDTO,Task> implements TaskManagementRemote {

    @EJB(beanName="TaskFacade")
    private FacadeLocal<Task> taskFacade;

    @Override
    public FacadeLocal<Task> getFacade() {
        return taskFacade;
    }
    
    @Override
    public Task DTO2DAO(TaskDTO myDTO){
        Task toReturn = new Task();
        if(myDTO==null) return toReturn;
        
        toReturn.setId(myDTO.getId());
        toReturn.setName(myDTO.getName());
        toReturn.setDescription(myDTO.getDescription());
        toReturn.setDue(myDTO.getDue());
        toReturn.setComplete(myDTO.isComplete());
        
        if(myDTO.getSprintid()!=null){
            TaskSprint dto = myDTO.getSprintid();
            Sprint dao = new Sprint();
            dao.setId(dto.getId());
            dao.setRelease(dto.getRelease());
            toReturn.setSprintid(dao);
        }
        
        if(myDTO.getAssignedTo()!=null){
            Collection<TaskMember> dtos = myDTO.getAssignedTo();
            toReturn.setMemberrCollection(new ArrayList());
            for(TaskMember dto : dtos){
                Memberr dao = new Memberr();
                dao.setId(dto.getId());
                dao.setName(dto.getName());
                toReturn.getMemberrCollection().add(dao);
            }
        }
        
        return toReturn;
    }
    
    @Override
    public TaskDTO DAO2DTO(Task myDAO){
        TaskDTO toReturn = new TaskDTO();
        if(myDAO==null) return toReturn;
        
        toReturn.setId(myDAO.getId());
        toReturn.setName(myDAO.getName());
        toReturn.setDescription(myDAO.getDescription());
        toReturn.setDue(myDAO.getDue());
        toReturn.setComplete(myDAO.getComplete());
        
        if(myDAO.getSprintid()!=null){
            Sprint dao = myDAO.getSprintid();
            TaskSprint dto = new TaskSprint();
            dto.setId(dao.getId());
            dto.setRelease(dao.getRelease());
            toReturn.setSprintid(dto);
        }
        
        if(myDAO.getMemberrCollection()!=null){
            Collection<Memberr> daos = myDAO.getMemberrCollection();
            toReturn.setAssignedTo(new ArrayList());
            for(Memberr dao : daos){
                TaskMember dto = new TaskMember();
                dto.setId(dao.getId());
                dto.setName(dao.getName());
                toReturn.getAssignedTo().add(dto);
            }
        }
        return toReturn;
    }
    
}
