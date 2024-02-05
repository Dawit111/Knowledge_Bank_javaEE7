/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Course;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.CourseFacade;

@Stateless
public class Course_session implements Course_sessionLocal {

    @EJB
    CourseFacade courseFacade;

    @Override
    public void deleteCourse(Course course) {
        courseFacade.remove(course);
    }

    @Override
    public void saveCourse(Course course) {
        courseFacade.create(course);
    }

}
