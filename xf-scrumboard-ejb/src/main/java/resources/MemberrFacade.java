/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Memberr;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Isuru
 */
@Stateless(name = "MemberrFacade")
public class MemberrFacade extends AbstractFacade<Memberr> implements MemberFacadeLocal<Memberr> {

    @PersistenceContext(unitName = "com.xfastgames_xf-scrumboard-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemberrFacade() {
        super(Memberr.class);
    }

    @Override
    public Memberr findFromEmail(String email) throws NoResultException {
        return (Memberr) getEntityManager().createNamedQuery("Memberr.findByEmail").setParameter("email", email).getSingleResult();
    }
    
    @Override
    public boolean IsUserWithEmail(String email){
        boolean toReturn = true;
        try{
           toReturn =  getEntityManager().createNamedQuery("Memberr.findByEmail").setParameter("email", email).getSingleResult() != null;
        }catch(NoResultException e){
            toReturn = false;
        }
        return toReturn;
    }

    @Override
    public Memberr findFromToken(String token) throws NoResultException {
        return (Memberr) getEntityManager().createNamedQuery("Memberr.findByToken").setParameter("token", token).getSingleResult();
    }
    
    @Override
    public Memberr findFromVerfificationKey(String key) throws NoResultException{
        return (Memberr)  getEntityManager().createNamedQuery("Memberr.findByVerificationkey").setParameter("verificationkey", key).getSingleResult();
    }


}
