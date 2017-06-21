/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.naming.AuthenticationException;
import models.Credentials;

/**
 *
 * @author Isuru
 */
public interface AuthenticationManagementServiceRemote {
    
    Credentials authenticateUser(String email, String password) 
            throws AuthenticationException;
    
}
