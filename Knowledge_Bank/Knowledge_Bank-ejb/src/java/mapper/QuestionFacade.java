/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Question;
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
public class QuestionFacade extends AbstractFacade<Question> {

    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionFacade() {
        super(Question.class);
    }

    public List<Question> findBydepartment(Integer depId) {
        Query query = em.createNamedQuery("Question.findByDepartment", Question.class);
        query.setParameter("depId", depId);
        return query.getResultList();
    }

    public List<Question> findByYear(Integer yearId) {
        Query query = em.createNamedQuery("Question.findByYear", Question.class);
        query.setParameter("yearId", yearId);
        return query.getResultList();
    }

    public List<Question> findByCourse(Integer coId) {
        Query query = em.createNamedQuery("Question.findByCourse", Question.class);
        query.setParameter("coId", coId);
        return query.getResultList();
    }

    public List<Question> findByYearAndDepartment(Integer yearId, Integer depId) {
        Query query = em.createNamedQuery("Question.findByYearOfStudyAndDepartment", Question.class);
        query.setParameter("yearId", yearId);
        query.setParameter("depId", depId);
        return query.getResultList();
    }

    public List<Question> findByYearAndDepartmentAndCourse(Integer yearId, Integer depId, Integer coId) {
        Query query = em.createNamedQuery("Question.findByYearOfStudyAndDepartmentAndCourse", Question.class);
        query.setParameter("yearId", yearId);
        query.setParameter("depId", depId);
        query.setParameter("coId", coId);
        return query.getResultList();
    }

    public List<Question> findByQuestion(String question) {
        Query query = em.createNamedQuery("Question.findByQuestionLike", Question.class);
        query.setParameter("question",'%' + question + '%');
        //'%' + question + '%' on both sides is used to search across left to right and right to left through out the question
        return query.getResultList();
    }

}
