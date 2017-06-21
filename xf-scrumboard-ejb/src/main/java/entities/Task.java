/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Isuru
 */
@Entity
@Table(name = "TASK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name")
    , @NamedQuery(name = "Task.findByDue", query = "SELECT t FROM Task t WHERE t.due = :due")
    , @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id")})
public class Task implements Serializable, Identifiable {

    @Column(name = "COMPLETE")
    private Boolean complete;

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Lob
    @Size(max = 32700)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DUE")
    @Temporal(TemporalType.DATE)
    private Date due;
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @JoinTable(name = "TASKMEMBER", joinColumns = {
        @JoinColumn(name = "TASKID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MEMBERID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Memberr> memberrCollection;
    @JoinColumn(name = "SPRINTID", referencedColumnName = "ID")
    @ManyToOne
    private Sprint sprintid;

    public Task() {
    }

    public Task(Long id) {
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Memberr> getMemberrCollection() {
        return memberrCollection;
    }

    public void setMemberrCollection(Collection<Memberr> memberrCollection) {
        this.memberrCollection = memberrCollection;
    }

    public Sprint getSprintid() {
        return sprintid;
    }

    public void setSprintid(Sprint sprintid) {
        this.sprintid = sprintid;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Task[ id=" + id + " ]";
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
    
}
