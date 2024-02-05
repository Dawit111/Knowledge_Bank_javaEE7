/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Answer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BABI
 */
@Stateless
public class AnswerFacade extends AbstractFacade<Answer> {

    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }

    public List<Answer> findAllAnswerOfQuestion(Integer qId) {
        Query query = em.createNamedQuery("Answer.findByAllAnsOfQuestion", Answer.class);
        query.setParameter("qId", qId);
        return query.getResultList();
    }
}
