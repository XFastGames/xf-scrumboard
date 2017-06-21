/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isuru
 */

@XmlRootElement
public class RegisterRequest implements Serializable {
    
    private String name;
    private String username;
    private String password;
    private String seqquestion;
    private String seqanswer;
    private String privilage;

    public RegisterRequest(){
        
    }
    
    public RegisterRequest(String name, String username, String password, String seqquestion, String seqanswer){
       this.name = name;
       this.username = username;
       this.password = password;
       this.seqquestion = seqquestion;
       this.seqanswer = seqanswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public String getPrivilage() {
        return privilage;
    }

    public void setPrivilage(String privilage) {
        this.privilage = privilage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegisterRequest)) {
            return false;
        }
        RegisterRequest other = (RegisterRequest) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.RegisterRequest[ username=" + username + " ]";
    }

}
