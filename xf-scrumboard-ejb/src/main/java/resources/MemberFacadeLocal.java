/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import javax.ejb.Local;
import javax.persistence.NoResultException;

/**
 *
 * @author Isuru
 */
@Local
public interface MemberFacadeLocal<DAO> extends FacadeLocal<DAO>{

    DAO findFromEmail(String email) throws NoResultException;
    
    DAO findFromVerfificationKey(String key) throws NoResultException;
    
    boolean IsUserWithEmail(String email);
             
    DAO findFromToken(String token) throws NoResultException;
    
}
