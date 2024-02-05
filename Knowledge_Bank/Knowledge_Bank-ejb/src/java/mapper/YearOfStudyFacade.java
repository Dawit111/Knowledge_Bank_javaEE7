/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.YearOfStudy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BABI
 */
@Stateless
public class YearOfStudyFacade extends AbstractFacade<YearOfStudy> {
    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public YearOfStudyFacade() {
        super(YearOfStudy.class);
    }
    
}
