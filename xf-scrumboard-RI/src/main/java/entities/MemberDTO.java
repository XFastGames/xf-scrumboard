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
import weakentity.MemberAdminTeam;
import weakentity.MemberTask;
import weakentity.MemberTeam;

/**
 *
 * @author Isuru
 */
@XmlRootElement
public class MemberDTO implements Serializable, Identifiable, Linkable {

    private Long id = null;
    private String name;
    private String email;
    private Boolean verified;
    private String privilage;
    private List<MemberTeam> partOfTeams;
    private List<MemberAdminTeam> adminOfTeams;
    private List<MemberTask> assignedTasks;
    
    private List<Link> _links = new ArrayList();

    public MemberDTO() {
        
    }

    public MemberDTO(Long id) {
        this.id = id;
    }

    public MemberDTO(Long id, String privilage) {
        this.id = id;
        this.privilage = privilage;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getPrivilage() {
        return privilage;
    }

    public void setPrivilage(String privilage) {
        this.privilage = privilage;
    }

    public List<MemberTeam> getPartOfTeams() {
        return partOfTeams;
    }

    public void setPartOfTeams(List<MemberTeam> partOfTeams) {
        this.partOfTeams = partOfTeams;
    }

    public List<MemberAdminTeam> getAdminOfTeams() {
        return adminOfTeams;
    }

    public void setAdminOfTeams(List<MemberAdminTeam> adminOfTeams) {
        this.adminOfTeams = adminOfTeams;
    }

    public List<MemberTask> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<MemberTask> assignedTasks) {
        this.assignedTasks = assignedTasks;
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
        if (!(object instanceof MemberDTO)) {
            return false;
        }
        MemberDTO other = (MemberDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MemberDTO[ id=" + id + " ]";
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
