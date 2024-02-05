/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.DepCoursYear;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.DepCoursYearFacade;

/**
 *
 * @author BABI
 */
@Stateless
public class Dcy_session implements Dcy_sessionLocal {

    @EJB
    public DepCoursYearFacade depCoursYearFacade;

    @Override
    public void saveDcy(DepCoursYear depCoursYear) {
        depCoursYearFacade.create(depCoursYear);
    }

    @Override
    public List<DepCoursYear> ViewAcademicsData(DepCoursYear depCoursYear) {
        System.out.println("////////////////////////////inside view academics in local");
        return depCoursYearFacade.findAll();

    }

    @Override
    public void deleteDcy(DepCoursYear depCoursYear) {
        depCoursYearFacade.remove(depCoursYear);
    }
}
