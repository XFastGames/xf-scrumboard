/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import models.Link;
import weakentity.ProjectMember;

import weakentity.ProjectSprint;

/**
 *
 * @author Isuru
 */
@XmlRootElement
public class ProjectDTO implements Serializable, Identifiable, Linkable {

    private Long id;
    private String name;
    private Date release;
    private List<ProjectMember> team;
    private List<ProjectSprint> sprints;
    
    private List<Link> _links = new ArrayList();

    public ProjectDTO() {
    }

    public ProjectDTO(Long id) {
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

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public List<ProjectMember> getTeam() {
        return team;
    }

    public void setTeam(List<ProjectMember> team) {
        this.team = team;
    }

    public List<ProjectSprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<ProjectSprint> sprints) {
        this.sprints = sprints;
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
        if (!(object instanceof ProjectDTO)) {
            return false;
        }
        ProjectDTO other = (ProjectDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProjectDTO[ id=" + id + " ]";
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
