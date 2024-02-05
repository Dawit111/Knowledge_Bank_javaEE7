/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.StudentRegistration;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.StudentRegistrationFacade;

/**
 *
 * @author BABI
 */
@Stateless
public class User_session implements User_sessionLocal {

    @EJB
    public StudentRegistrationFacade studentRegistrationFacade;

    @Override
    public void saveUser(StudentRegistration studentRegistration) {
        System.out.println("inside saveeee,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        studentRegistrationFacade.create(studentRegistration);
    }

    @Override
    public List<StudentRegistration> viewUser(StudentRegistration studentRegistration) {
        System.out.println("inside viewUser,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        return studentRegistrationFacade.findAll();
    }

    @Override
    public List<StudentRegistration> findAll() {
        System.out.println("inside findAll,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        return studentRegistrationFacade.findAll();
    }

    @Override
    public void deleteUser(StudentRegistration studentRegistration) {
        System.out.println("inside delete,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

        studentRegistrationFacade.remove(studentRegistration);
    }

    @Override
    public void editUser(StudentRegistration studentRegistration) {
        System.out.println("inside editeUser in the session,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        studentRegistrationFacade.edit(studentRegistration);
    }

    @Override
    public StudentRegistration findById(StudentRegistration studentRegistration) {
        return studentRegistrationFacade.findById(studentRegistration);
    }

    @Override
    public List<StudentRegistration> findByFname(String fname) {
        return studentRegistrationFacade.findByFname(fname);
    }

    @Override
    public boolean getStudentInfoDuplicate(StudentRegistration register) {
        return studentRegistrationFacade.getStudentInfoDuplicate(register);
    }

    @Override
    public List<StudentRegistration> SearchStudentId(StudentRegistration register) {
        return studentRegistrationFacade.SearchStudentId(register);
    }
}

    // Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")

