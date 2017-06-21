/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Isuru
 */
public class Credentials implements Serializable, SecurityContext, Principal {
    
    private String username;
    private String password;
    private String privilage;
    private boolean secure;
    private String scheme;
    private SecurityToken token;
    
    public Credentials(){
        
    }
    
    public Credentials(SecurityContext outOf){
        scheme = outOf.getAuthenticationScheme();
        secure = outOf.isSecure();
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


    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public SecurityToken getToken() {
        return token;
    }

    public void setToken(SecurityToken token) {
        this.token = token;
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
        if (!(object instanceof Credentials)) {
            return false;
        }
        Credentials other = (Credentials) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Credentials[ username=" + username + " ]";
    }

    @Override
    public Principal getUserPrincipal() {
        return this;
    }

    @Override
    public boolean isUserInRole(String role) {
        return getPrivilage().equals(role);
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return getScheme();
    }

    @Override
    public String getName() {
        return getUsername();
    }
    
    
    
}
