/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Memberr;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;
import resources.MemberFacadeLocal;

/**
 *
 * @author Isuru
 */
@Stateless
public class VerificationManagementService implements VerificationManagementServiceRemote {

    @EJB
    private MemberFacadeLocal memberrFacade;

    @Override
    public boolean verifyUser(String verificationKey) {
        boolean toReturn;
        Memberr found;
        try{
            found = (Memberr) memberrFacade.findFromVerfificationKey(verificationKey);
            found.setVerified(true);
            memberrFacade.edit(found);
            toReturn = true;
        }catch(Exception e){
            toReturn = false;
        }
        return toReturn;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean sendVerificationEmail(String toNotify) {
        boolean toReturn;
        Memberr found;
        
        try{
            found = (Memberr) memberrFacade.findFromEmail(toNotify);
            found.setVerificationkey(generateVerificationKey());
            memberrFacade.edit(found);
            System.out.println("Sending the confirmation email!");
            sendEmailToConfirm("isuru@xfastgames.com",found.getVerificationkey());
            toReturn = true;
        }catch(Exception e){
            toReturn = false;
        }
        return toReturn;
    }
    
    private String generateVerificationKey(){
        return (UUID.randomUUID().toString());
    }
    
    // Inner Authenticator for the mail protocol
    public class MyAuthenticator extends Authenticator {

        PasswordAuthentication mypa;

        public MyAuthenticator(String username, String password) {
            mypa = new PasswordAuthentication(username, password);
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return mypa;
        }
    }
    
    public boolean sendEmailToConfirm(String to, String verificationKey) {
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            MyAuthenticator myPA = new MyAuthenticator("", ""); // see MyAuthenticator class
            Session session = Session.getInstance(props, myPA);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("rnadhisha4@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("isuru@xfastgames.com", false));
            msg.setSubject("Verify your account!");
            String link = "http://localhost/verify/?key="+verificationKey;
            msg.setText("Please verify your account by using this "+ link);
            msg.setHeader("X-Mailer", "Gmail");
            msg.setSentDate(new Date());
            Transport.send(msg,"rnadhisha4@gmail.com", "Fuckingpussy");
            System.out.println("Mailer = Message sent OK.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
