/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Faculty;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.FacultyFacade;

@Stateless
public class Faculty_session implements Faculty_sessionLocal {

    @EJB
    FacultyFacade facultyFacade;
    
    @Override
    public void saveFaculty(Faculty faculty) {
        facultyFacade.create(faculty);
    }

    @Override
    public void deleteFaculty(Faculty faculty) {
        facultyFacade.remove(faculty);
    }

}
