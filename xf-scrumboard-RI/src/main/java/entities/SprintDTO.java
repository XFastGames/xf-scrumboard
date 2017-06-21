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

import weakentity.SprintProject;
import weakentity.SprintTask;

/**
 *
 * @author Isuru
 */
@XmlRootElement
public class SprintDTO implements Serializable, Identifiable, Linkable {

    private Long id;
    private Date release;
    private SprintProject project;
    private List<SprintTask> tasks;
    
    private List<Link> _links = new ArrayList();

    public SprintDTO() {
    }

    public SprintDTO(Long id) {
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

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public List<SprintTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<SprintTask> tasks) {
        this.tasks = tasks;
    }

    public SprintProject getProject() {
        return project;
    }

    public void setProject(SprintProject project) {
        this.project = project;
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
        if (!(object instanceof SprintDTO)) {
            return false;
        }
        SprintDTO other = (SprintDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SprintDTO[ id=" + id + " ]";
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
