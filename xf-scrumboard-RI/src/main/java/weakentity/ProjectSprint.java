/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weakentity;

import entities.Identifiable;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isuru
 */

@XmlRootElement
public class ProjectSprint implements Serializable, Identifiable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date release;

    public ProjectSprint() {

    }

    public ProjectSprint(Long id) {
        this.id = id;
    }

    public ProjectSprint(Long id, Date due) {
        this.id = id;
        this.release = due;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof ProjectSprint)) {
            return false;
        }
        ProjectSprint other = (ProjectSprint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TeamDTO.Admin[ id=" + id + " ]";
    }
}
