/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.MemberDTO;
import entities.Memberr;
import entities.Task;
import entities.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.Link;
import weakentity.MemberAdminTeam;
import weakentity.MemberTask;
import weakentity.MemberTeam;

/**
 *
 * @author Isuru
 */

@Stateless(name="MemberrManagement")
public class MemberrManagement extends EntityManagement<MemberDTO,Memberr> implements MemberManagementRemote {

    @EJB(beanName="MemberrFacade")
    private MemberFacadeLocal<Memberr> memberrFacade;

    @Override
    public FacadeLocal<Memberr> getFacade() {
        return (FacadeLocal<Memberr>) memberrFacade;
    }
    
    @Override
    public Memberr DTO2DAO(MemberDTO myDTO){
        Memberr toReturn = new Memberr();
        if(myDTO==null) return toReturn;
        toReturn.setId(myDTO.getId());
        
        toReturn.setName(myDTO.getName());
        toReturn.setEmail(myDTO.getEmail());
        toReturn.setPrivilage(myDTO.getPrivilage());
        toReturn.setVerified(myDTO.getVerified());
        
        if( myDTO.getAdminOfTeams()!=null && myDTO.getAdminOfTeams().size()>0){
            List<MemberAdminTeam> dtos = myDTO.getAdminOfTeams();
            toReturn.setTeamAdminCollection(new ArrayList());
            for(MemberAdminTeam dto : dtos){
                Team doa = new Team();
                doa.setId(dto.getId());
                doa.setName(dto.getName());
                doa.setAdmin(toReturn);
                toReturn.getTeamAdminCollection().add(doa);
            }
        }
        
        if( myDTO.getPartOfTeams()!=null && myDTO.getPartOfTeams().size()>0){
            List<MemberTeam> dtos = myDTO.getPartOfTeams();
            toReturn.setTeamMemberCollection(new ArrayList());
            for(MemberTeam dto : dtos){
                Team doa = new Team();
                doa.setId(dto.getId());
                doa.setName(dto.getName());
                toReturn.getTeamMemberCollection().add(doa);
            }
        }
        
        if( myDTO.getAssignedTasks()!=null && myDTO.getAssignedTasks().size()>0){
            List<MemberTask> dtos = myDTO.getAssignedTasks();
            toReturn.setTaskCollection(new ArrayList());
            for(MemberTask dto : dtos){
                Task doa = new Task();
                doa.setId(dto.getId());
                doa.setName(dto.getName());
                doa.setDue(dto.getDue());
                toReturn.getTaskCollection().add(doa);
            }
        }
        
        return toReturn;
    }
    
    @Override
    public MemberDTO DAO2DTO(Memberr myDAO){
        MemberDTO toReturn = new MemberDTO();
        if(myDAO==null) return toReturn;
        
        toReturn.setId(myDAO.getId());        
        toReturn.setEmail(myDAO.getEmail()); 
        toReturn.setName(myDAO.getName());
        toReturn.setPrivilage(myDAO.getPrivilage());      
        toReturn.setVerified(myDAO.getVerified());
        
        toReturn.setLinks(new ArrayList());
        toReturn.getLinks().add(new Link("self","/xf-scrumboard-api/api/members/"+myDAO.getId()));
        
               
        toReturn.getLinks().add(new Link("admin","/xf-scrumboard-api/api/members/"+myDAO.getId()+"/admin"));
        if( myDAO.getTeamAdminCollection()!= null && myDAO.getTeamAdminCollection().size()>0){
            Collection<Team> doas = myDAO.getTeamAdminCollection();
            toReturn.setAdminOfTeams(new ArrayList());
            for(Team dao : doas){
                MemberAdminTeam dto = new MemberAdminTeam();
                dto.setId(dao.getId());
                dto.setName(dao.getName());
                toReturn.getAdminOfTeams().add(dto);
            }
        }
        
        toReturn.getLinks().add(new Link("teams","/xf-scrumboard-api/api/members/"+myDAO.getId()+"/teams"));
        if( myDAO.getTeamMemberCollection()!= null && myDAO.getTeamMemberCollection().size()>0){
            Collection<Team> doas = myDAO.getTeamMemberCollection();
            toReturn.setPartOfTeams(new ArrayList<MemberTeam>());
            for(Team dao : doas){
                MemberTeam dto = new MemberTeam();
                dto.setId(dao.getId());
                dto.setName(dao.getName());
                toReturn.getPartOfTeams().add(dto);
            }
        }
        
        toReturn.getLinks().add(new Link("tasks","/xf-scrumboard-api/api/members/"+myDAO.getId()+"/tasks"));
        if( myDAO.getTaskCollection()!=null && myDAO.getTaskCollection().size()>0){
            Collection<Task> doas = myDAO.getTaskCollection();
            toReturn.setAssignedTasks(new ArrayList());
            for(Task dao : doas){
                MemberTask dto = new MemberTask();
                dto.setId(dao.getId());
                dto.setName(dao.getName());
                dto.setDue(dao.getDue());
                toReturn.getAssignedTasks().add(dto);
            }
        }
        
        return toReturn;
    }

    @Override
    public boolean UserExistWithEmail(String email) {
        boolean found = true;
        try{
            found = memberrFacade.IsUserWithEmail(email);
        }catch(Exception e){
            found = false;
        }
        return found;
    }
    
    @Override
    public MemberDTO findByEmail(String email) {
        return DAO2DTO(memberrFacade.findFromEmail(email));
    }
    
    @Override
    public MemberDTO findByToken(String token) {
        return DAO2DTO(memberrFacade.findFromToken(token));  
    }
    
    
}
