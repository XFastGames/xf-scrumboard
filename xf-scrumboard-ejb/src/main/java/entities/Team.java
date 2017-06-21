/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Isuru
 */
@Entity
@Table(name = "TEAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
    , @NamedQuery(name = "Team.findByName", query = "SELECT t FROM Team t WHERE t.name = :name")
    , @NamedQuery(name = "Team.findById", query = "SELECT t FROM Team t WHERE t.id = :id")})
public class Team implements Serializable, Identifiable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @ManyToMany(mappedBy = "teamMemberCollection")
    private Collection<Memberr> memberrCollection;
    @OneToMany(mappedBy = "teamid")
    private Collection<Project> projectCollection;
    @JoinColumn(name = "ADMIN", referencedColumnName = "ID")
    @ManyToOne
    private Memberr admin;

    public Team() {
    }

    public Team(Long id) {
        this.id = id;
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

    @XmlTransient
    public Collection<Memberr> getMemberrCollection() {
        return memberrCollection;
    }

    public void setMemberrCollection(Collection<Memberr> memberrCollection) {
        this.memberrCollection = memberrCollection;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Memberr getAdmin() {
        return admin;
    }

    public void setAdmin(Memberr admin) {
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
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Team[ id=" + id + " ]";
    }
    
}
