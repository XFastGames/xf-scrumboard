/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import models.Link;
import weakentity.TaskMember;
import weakentity.TaskSprint;

/**
 *
 * @author Isuru
 */
@XmlRootElement
public class TaskDTO implements Serializable, Identifiable, Linkable{

    private Long id;
    private String name;
    private String description;
    private Date due;
    private boolean complete;
    private Collection<TaskMember> assignedTo;
    private TaskSprint sprintid;

    private List<Link> _links = new ArrayList();

    public TaskDTO() {
    }

    public TaskDTO(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Collection<TaskMember> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Collection<TaskMember> memberDTOCollection) {
        this.assignedTo = memberDTOCollection;
    }

    public TaskSprint getSprintid() {
        return sprintid;
    }

    public void setSprintid(TaskSprint sprintid) {
        this.sprintid = sprintid;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
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
        if (!(object instanceof TaskDTO)) {
            return false;
        }
        TaskDTO other = (TaskDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TaskDTO[ id=" + id + " ]";
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
