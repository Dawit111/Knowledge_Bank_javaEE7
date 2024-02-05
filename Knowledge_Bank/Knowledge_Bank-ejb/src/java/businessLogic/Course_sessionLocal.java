/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import javax.ejb.Local;
import entity.Course;
import java.util.List;
/**
 *
 * @author BABI
 */
@Local
public interface Course_sessionLocal {
   public void deleteCourse(Course course);

    public void saveCourse(Course course);

}
