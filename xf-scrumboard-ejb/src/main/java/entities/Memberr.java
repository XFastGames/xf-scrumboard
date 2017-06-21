/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memberr.findAll", query = "SELECT m FROM Memberr m")
    , @NamedQuery(name = "Memberr.findByName", query = "SELECT m FROM Memberr m WHERE m.name = :name")
    , @NamedQuery(name = "Memberr.findByEmail", query = "SELECT m FROM Memberr m WHERE m.email = :email")
    , @NamedQuery(name = "Memberr.findByVerified", query = "SELECT m FROM Memberr m WHERE m.verified = :verified")
    , @NamedQuery(name = "Memberr.findByVerificationkey", query = "SELECT m FROM Memberr m WHERE m.verificationkey = :verificationkey")
    , @NamedQuery(name = "Memberr.findByPassword", query = "SELECT m FROM Memberr m WHERE m.password = :password")
    , @NamedQuery(name = "Memberr.findBySeqquestion", query = "SELECT m FROM Memberr m WHERE m.seqquestion = :seqquestion")
    , @NamedQuery(name = "Memberr.findBySeqanswer", query = "SELECT m FROM Memberr m WHERE m.seqanswer = :seqanswer")
    , @NamedQuery(name = "Memberr.findByPrivilage", query = "SELECT m FROM Memberr m WHERE m.privilage = :privilage")
    , @NamedQuery(name = "Memberr.findById", query = "SELECT m FROM Memberr m WHERE m.id = :id")
    , @NamedQuery(name = "Memberr.findByToken", query = "SELECT m FROM Memberr m WHERE m.token = :token")})
public class Memberr implements Serializable, Identifiable, Principal {

    @Size(max = 100)
    @Column(name = "TOKEN")
    private String token;

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "VERIFIED")
    private Boolean verified;
    @Size(max = 100)
    @Column(name = "VERIFICATIONKEY")
    private String verificationkey;
    @Size(max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 200)
    @Column(name = "SEQQUESTION")
    private String seqquestion;
    @Size(max = 200)
    @Column(name = "SEQANSWER")
    private String seqanswer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRIVILAGE")
    private String privilage;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @JoinTable(name = "TEAMMEMBER", joinColumns = {
        @JoinColumn(name = "MEMBERID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "TEAMID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Team> teamMemberCollection;
    @ManyToMany(mappedBy = "memberrCollection")
    private Collection<Task> taskCollection;
    @OneToMany(mappedBy = "admin")
    private Collection<Team> teamAdminCollection;

    public Memberr() {
    }

    public Memberr(Long id) {
        this.id = id;
    }

    public Memberr(Long id, String privilage) {
        this.id = id;
        this.privilage = privilage;
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

    public String getVerificationkey() {
        return verificationkey;
    }

    public void setVerificationkey(String verificationkey) {
        this.verificationkey = verificationkey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSeqquestion() {
        return seqquestion;
    }

    public void setSeqquestion(String seqquestion) {
        this.seqquestion = seqquestion;
    }

    public String getSeqanswer() {
        return seqanswer;
    }

    public void setSeqanswer(String seqanswer) {
        this.seqanswer = seqanswer;
    }

    public String getPrivilage() {
        return privilage;
    }

    public void setPrivilage(String privilage) {
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

    @XmlTransient
    public Collection<Team> getTeamMemberCollection() {
        return teamMemberCollection;
    }

    public void setTeamMemberCollection(Collection<Team> teamMemberCollection) {
        this.teamMemberCollection = teamMemberCollection;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    @XmlTransient
    public Collection<Team> getTeamAdminCollection() {
        return teamAdminCollection;
    }

    public void setTeamAdminCollection(Collection<Team> teamAdminCollection) {
        this.teamAdminCollection = teamAdminCollection;
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
        if (!(object instanceof Memberr)) {
            return false;
        }
        Memberr other = (Memberr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Memberr[ id=" + id + " ]";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
