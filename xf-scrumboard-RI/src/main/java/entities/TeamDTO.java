/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import models.Link;

import weakentity.TeamAdmin;
import weakentity.TeamMember;
import weakentity.TeamProject;

/**
 *
 * @author Isuru
 */
@XmlRootElement
public class TeamDTO implements Serializable, Identifiable, Linkable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private List<TeamMember> members;
    private List<TeamProject> projects;
    private TeamAdmin admin;
    
    private List<Link> _links = new ArrayList();


    public TeamDTO() {
    }

    public TeamDTO(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    public List<TeamProject> getProjects() {
        return projects;
    }

    public void setProjects(List<TeamProject> projects) {
        this.projects = projects;
    }

    public TeamAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(TeamAdmin admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeamDTO)) {
            return false;
        }
        TeamDTO other = (TeamDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TeamDTO[ id=" + id + " ]";
    }
    
    
    @Override
    public List<Link> getLinks() {
        return _links;
    }

    @Override
    public void setLinks(List<Link> _links) {
        this._links = _links;
    }

}
