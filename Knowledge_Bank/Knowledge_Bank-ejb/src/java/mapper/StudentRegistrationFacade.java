/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.StudentRegistration;
import static java.rmi.activation.Activatable.register;
import java.util.ArrayList;
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
public class StudentRegistrationFacade extends AbstractFacade<StudentRegistration> {

    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentRegistrationFacade() {
        super(StudentRegistration.class);
    }

    public StudentRegistration findById(StudentRegistration studentRegistration) {
        try {
            Query query = em.createNamedQuery("StudentRegistration.findById", StudentRegistration.class);
            query.setParameter("Id", studentRegistration.getStudId());
            StudentRegistration reg = (StudentRegistration) query.getResultList().get(0);
            return reg;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    public List<StudentRegistration> findByFname(String fname) {
        Query query = em.createNamedQuery("StudentRegistration.findByfnameLike", StudentRegistration.class);
        query.setParameter("fname", fname + '%');
        return query.getResultList();
    }

    public boolean getStudentInfoDuplicate(StudentRegistration register) {
        boolean duplication;
        Query query = em.createNamedQuery("StudentRegistration.findByIdNumberAndFirstName", StudentRegistration.class);
        query.setParameter("idNumber", register.getIdNumber());
        query.setParameter("firstName", register.getFirstName());
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

    public List<StudentRegistration> SearchStudentId(StudentRegistration register) {
        Query q = em.createNamedQuery("StudentRegistration.findByIdNumberLike", StudentRegistration.class);
        q.setParameter("idNumber", register.getIdNumber().toUpperCase() + '%');
        try {
            ArrayList<StudentRegistration> studInfo = new ArrayList(q.getResultList());
            return studInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
