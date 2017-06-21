/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.MemberDTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.SecurityContext;
import models.Credentials;
import models.SecurityToken;
import resources.MemberFacadeLocal;
import resources.MemberManagementRemote;

/**
 *
 * @author Isuru
 */
@Stateless
public class AuthorisationService implements AuthorisationServiceLocal {

    @EJB
    private MemberManagementRemote memberrManagement;

    @Override
    public SecurityContext getSecureUserForToken(SecurityToken s){
        MemberDTO user = memberrManagement.findByToken(s.toString());
        Credentials cre = new Credentials();
        cre.setUsername(user.getEmail());
        cre.setPrivilage(user.getPrivilage());
        cre.setSecure(true);
        return cre;
    }
    
}
