/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.MemberDTO;
import entities.Memberr;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import models.RegisterRequest;
import models.SecurityToken;



import resources.MemberFacadeLocal;
import resources.MemberManagementRemote;

/**
 *
 * @author Isuru
 */
@Stateful(name = "RegistrationManagementService")
@ApplicationScoped
public class RegistrationManagementService implements RegistrationManagementServiceRemote {

    @EJB
    private MemberManagementRemote memberrManagement;

    @EJB
    private MemberFacadeLocal memberrFacade;
    
    
    @Override
    public Response registerUser(RegisterRequest request) throws Exception {
       System.out.println("Checking for user with "+request.getUsername());
       if(memberrFacade.IsUserWithEmail(request.getUsername())) throw new Exception("That username is already taken");
       
       MemberDTO newMember = new MemberDTO();
       newMember.setEmail(request.getUsername());
       newMember.setName(request.getName());
       newMember.setVerified(false);
       newMember.setPrivilage("XF-APP-USER");
       newMember = memberrManagement.create(newMember);
       System.out.println("New member created with id "+ newMember.getId());
       
       Memberr registedUser = (Memberr) memberrFacade.find(newMember.getId());
       registedUser.setPassword(request.getPassword());
       registedUser.setSeqquestion(request.getSeqquestion());
       registedUser.setSeqanswer(request.getSeqanswer());
       memberrFacade.edit(registedUser);
       
       return Response.ok().build();
    }

    public SecurityToken generateToken(Date expire) {
        SecurityToken toReturn = new SecurityToken();
        toReturn.setKey(UUID.randomUUID().toString());
        toReturn.setExpire(expire);
        return toReturn;
    }

    public Date AdvanceDayBy(Date toAdvance, int advanceBy, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(field, advanceBy);
        return cal.getTime();
    }

}
