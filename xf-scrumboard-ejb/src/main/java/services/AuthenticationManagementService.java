/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Memberr;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.naming.AuthenticationException;
import models.Credentials;
import models.SecurityToken;



import resources.MemberFacadeLocal;

/**
 *
 * @author Isuru
 */
@Stateful(name = "AuthenticationManagement")
@ApplicationScoped
public class AuthenticationManagementService implements AuthenticationManagementServiceRemote {

    @EJB(beanName = "MemberrFacade")
    private MemberFacadeLocal memberrFacade;

    public MemberFacadeLocal getFacade() {
        return memberrFacade;
    }

    @Override
    public Credentials authenticateUser(String email, String hashedPassword) throws AuthenticationException {

        Memberr currentUser = null;
        try {
            currentUser = (Memberr) getFacade().findFromEmail(email);
        } catch (Exception e) {
            throw new AuthenticationException("No user found for that email!");
        }

        if (currentUser == null) {
            throw new AuthenticationException("No user found for that email!");
        }
        if (!currentUser.getPassword().equals(hashedPassword)) {
            throw new AuthenticationException("Email and Password combination is wrong!");
        }

        Credentials userCredential = new Credentials();
        userCredential.setUsername(currentUser.getEmail());
        userCredential.setPassword(currentUser.getPassword());
        userCredential.setPrivilage(currentUser.getPrivilage());
        userCredential.setSecure(true);

        //generate token that expires after a day
        SecurityToken st = generateToken(AdvanceDayBy(new Date(), 1, Calendar.DAY_OF_MONTH),currentUser.getPrivilage());

        //save the token on the database and userCredentials to send;
        currentUser.setToken(st.toString());
        userCredential.setToken(st);
        return userCredential;

    }

    public SecurityToken generateToken(Date expire,String privilage) {
        SecurityToken toReturn = new SecurityToken();
        toReturn.setKey(UUID.randomUUID().toString());
        toReturn.setExpire(expire);
        toReturn.setGroup(privilage);
        return toReturn;
    }

    public Date AdvanceDayBy(Date toAdvance, int advanceBy, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(field, advanceBy);
        return cal.getTime();
    }

}
