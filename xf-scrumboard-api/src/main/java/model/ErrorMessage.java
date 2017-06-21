/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Isuru
 */
@XmlRootElement(name="error")
public class ErrorMessage implements Serializable {
    private String message;
    private int code;
    private String documentation;
    
    public  ErrorMessage(){
    } 
    
    public  ErrorMessage(int errorCode, String errorMessage, String doc){
        super();
        this.message = errorMessage;
        this.code = errorCode;
        this.documentation = doc;

    } 
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
    
    
}
