/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Course;
import entity.DepCoursYear;
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
public class DepCoursYearFacade extends AbstractFacade<DepCoursYear> {

    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepCoursYearFacade() {
        super(DepCoursYear.class);
    }

    public DepCoursYear findByDeptCorYr(DepCoursYear depCoursYear) {
        try {
            Query query = em.createNamedQuery("DepCoursYear.findByDeptCorYr", DepCoursYear.class);
            query.setParameter("depFk", depCoursYear.getDepFk());
            query.setParameter("yosFk", depCoursYear.getYosFk());
            query.setParameter("courseFk", depCoursYear.getCourseFk());
            DepCoursYear reg = (DepCoursYear) query.getResultList().get(0);
            return reg;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    public List<DepCoursYear> findByDeptYr(DepCoursYear depCoursYear) {
        Query query = em.createNamedQuery("DepCoursYear.findByDeptYr", DepCoursYear.class);
        query.setParameter("depFk", depCoursYear.getDepFk());
        query.setParameter("yosFk", depCoursYear.getYosFk());
        try {
            ArrayList<DepCoursYear> studInfo = new ArrayList(query.getResultList());
            return studInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
