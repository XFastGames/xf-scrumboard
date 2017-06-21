/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Memberr;
import entities.Project;
import entities.Team;
import entities.TeamDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import weakentity.TeamAdmin;
import weakentity.TeamMember;
import weakentity.TeamProject;

/**
 *
 * @author Isuru
 */

@Stateless(name="TeamManagement")
public class TeamManagement extends EntityManagement<TeamDTO,Team> implements TeamManagementRemote {

    @EJB(beanName="TeamFacade")
    private FacadeLocal<Team>  teamFacade;

    @Override
    public FacadeLocal<Team> getFacade() {
        return teamFacade;
    }
    
    @Override
    public Team DTO2DAO(TeamDTO myDTO){
        Team toReturn = new Team();
        if(myDTO==null) return toReturn;
        
        toReturn.setId(myDTO.getId());
        toReturn.setName(myDTO.getName());
        
        if(myDTO.getAdmin()!=null){
            Memberr dao = new Memberr(myDTO.getAdmin().getId());
            toReturn.setAdmin(dao);
        }
        
        if(myDTO.getMembers()!=null && myDTO.getMembers().size()>0){
            List<TeamMember> dtos = myDTO.getMembers();
            toReturn.setMemberrCollection(new ArrayList());
            for(TeamMember dto : dtos){
                Memberr doa = new Memberr(dto.getId());
                toReturn.getMemberrCollection().add(doa);
            }
        }
        
        if(myDTO.getProjects()!=null && myDTO.getProjects().size()>0){
            List<TeamProject> dtos = myDTO.getProjects();
            toReturn.setMemberrCollection(new ArrayList());
            for(TeamProject dto : dtos){
                Project doa = new Project(dto.getId());
                toReturn.getProjectCollection().add(doa);
            }
        }
        
        return toReturn;
    }
    
    @Override
    public TeamDTO DAO2DTO(Team myDAO){
        TeamDTO toReturn = new TeamDTO();
        if(myDAO==null) return toReturn;
        toReturn.setId(myDAO.getId());
        toReturn.setName(myDAO.getName());
        
        if(myDAO.getAdmin()!=null){
            Memberr doa = myDAO.getAdmin();
            TeamAdmin dto = new TeamAdmin();
            dto.setId(doa.getId());
            dto.setName(doa.getName());
            toReturn.setAdmin(dto);
        }
        
        if(myDAO.getMemberrCollection()!=null && myDAO.getMemberrCollection().size()>0){
            Collection<Memberr> doas = myDAO.getMemberrCollection();
            toReturn.setMembers(new ArrayList());
            for(Memberr doa : doas){
                TeamMember tm = new TeamMember();
                tm.setId(doa.getId());               
                tm.setName(doa.getName());
                toReturn.getMembers().add(tm);
            }
        }
        
        if(myDAO.getProjectCollection()!=null && myDAO.getProjectCollection().size()>0){
            toReturn.setProjects(new ArrayList());
            for(Project dao : myDAO.getProjectCollection()){
                TeamProject dto = new TeamProject();
                dto.setId(dao.getId());
                dto.setName(dao.getName());
                toReturn.getProjects().add(dto);
            }
        }
        
        return toReturn;
    }
    
}
