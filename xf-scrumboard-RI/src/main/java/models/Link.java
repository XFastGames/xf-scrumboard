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
public class Link implements Serializable{
    
    private String rel;
    private String href;
    
    public Link(){
        
    }
    
    public Link(String rel, String href){
        this.rel = rel;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rel != null ? rel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.rel == null && other.rel != null) || (this.rel != null && !this.rel.equals(other.rel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Link[ rel=" + rel + " href=" + href +  "  ]";
    }
}
