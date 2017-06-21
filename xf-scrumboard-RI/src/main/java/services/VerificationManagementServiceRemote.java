/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Remote;

/**
 *
 * @author Isuru
 */
@Remote
public interface VerificationManagementServiceRemote {

    boolean verifyUser(String verificationKey);

    boolean sendVerificationEmail(String toNotify);
    
}
