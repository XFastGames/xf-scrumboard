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
public class SprintTask implements Serializable, Identifiable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Long id;
    private Date due;

    public SprintTask() {

    }

    public SprintTask(Long id, String name, Date due) {
        this.id = id;
        this.name = name;
        this.due = due;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
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
        if (!(object instanceof SprintTask)) {
            return false;
        }
        SprintTask other = (SprintTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SprintDTO.Task[ id=" + id + " ]";
    }

}
