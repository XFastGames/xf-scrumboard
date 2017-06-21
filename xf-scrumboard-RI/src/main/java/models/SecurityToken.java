/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author Isuru
 */
public class SecurityToken{
    private String key;
    private Date expire;
    private String group;
    
    public SecurityToken(){ 
    }
    
    public SecurityToken(String key, Date expire, String group){
        this.key = key;
        this.expire = expire;
        this.group = group;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (key != null ? key.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecurityToken)) {
            return false;
        }
        SecurityToken other = (SecurityToken) object;
        if ((this.key == null && other.key != null) || (this.key != null && !this.key.equals(other.key))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat(); 
        String token = key+"|"+formatter.format(expire)+"|"+group;
        token = Base64.getEncoder().encodeToString(token.getBytes());
        return token;
    }
    
    public static SecurityToken fromString(String s) throws ParseException, IllegalStateException
    {
        String token = new String(Base64.getDecoder().decode(s));
        StringTokenizer tokenizer = new StringTokenizer(token, "|");
        if(tokenizer.countTokens()<2) throw new IllegalStateException();
        String key = tokenizer.nextToken();
        String expireString = tokenizer.nextToken();
        String group = tokenizer.nextToken();
        SimpleDateFormat formatter  = new SimpleDateFormat();
        Date expireDate = null;
        try{ expireDate = formatter.parse(expireString); } catch(ParseException e) { throw e; }
        return new SecurityToken(key,expireDate,group);
    }
}
