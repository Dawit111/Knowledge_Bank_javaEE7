/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.AnswerUseraccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.AnswerUseraccount;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author BABI
 */
@Stateless
public class AnswerUseraccountFacade extends AbstractFacade<AnswerUseraccount> {

    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerUseraccountFacade() {
        super(AnswerUseraccount.class);
    }

    public boolean getLikeDuplicate(AnswerUseraccount answerUseraccount) {
        boolean duplication;
        Query query = em.createNamedQuery("AnswerUseraccount.findByUserIDAndAnswerID", AnswerUseraccount.class);
        query.setParameter("ansFk", answerUseraccount.getAnsFk());
        query.setParameter("userAccFk", answerUseraccount.getUserAccFk());
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

    public AnswerUseraccount findByLikeDuplicate(AnswerUseraccount answerUseraccount) {
        Query query = em.createNamedQuery("AnswerUseraccount.findAnsUserduplicate", AnswerUseraccount.class);
        query.setParameter("ansFk", answerUseraccount.getAnsFk());
        query.setParameter("userAccFk", answerUseraccount.getUserAccFk());
        AnswerUseraccount reg = (AnswerUseraccount) query.getResultList().get(0);
        return reg;
    }

}
