/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Useraccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BABI
 */
@Stateless
public class UseraccountFacade extends AbstractFacade<Useraccount> {

    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UseraccountFacade() {
        super(Useraccount.class);
    }

    public Useraccount findByUserNameAndPassword(Useraccount useraccount) {
        try {
            Query query = em.createNamedQuery("Useraccount.findByUsernameAndPassword", Useraccount.class);
            query.setParameter("username", useraccount.getUsername());
            query.setParameter("password", useraccount.getPassword());
            Useraccount user = (Useraccount) query.getResultList().get(0);
            return user;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    public Useraccount findByPassword(Useraccount useraccount) {
        try {
            Query query = em.createNamedQuery("Useraccount.findByPassword", Useraccount.class);
            query.setParameter("password", useraccount.getPassword());
            Useraccount admin = (Useraccount) query.getResultList().get(0);
            return admin;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }
     public boolean getAccountDuplicate(Useraccount useraccount) {
        boolean duplication;
        Query query = em.createNamedQuery("Useraccount.findByUserProfile", Useraccount.class);
        query.setParameter("userProfile", useraccount.getUserProfile());
        
        try {
            if (query.getResultList().size() > 0) {
                duplication = true;
            } else {
                duplication = false;
            }
            return duplication;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
