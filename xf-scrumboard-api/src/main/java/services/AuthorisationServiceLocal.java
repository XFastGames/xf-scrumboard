/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Local;
import javax.ws.rs.core.SecurityContext;
import models.SecurityToken;

/**
 *
 * @author Isuru
 */
@Local
public interface AuthorisationServiceLocal {
    SecurityContext getSecureUserForToken(SecurityToken s);
}
