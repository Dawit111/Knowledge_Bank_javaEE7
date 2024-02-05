/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.StudentRegistration;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author BABI
 */
@Local
public interface User_sessionLocal {
    
    /**
     *
     * @param studentRegistration
     */
    public void saveUser(StudentRegistration studentRegistration);
   // public List<StudentRegistration> findAll();

    public List<StudentRegistration> viewUser(StudentRegistration studentRegistration);
    
    public List<StudentRegistration> findAll();
    
    public void deleteUser(StudentRegistration studentRegistration);
    
    public void editUser(StudentRegistration studentRegistration);
    
    public StudentRegistration findById(StudentRegistration useraccount);
    
    public List<StudentRegistration> findByFname(String fname);
    
    public boolean getStudentInfoDuplicate(StudentRegistration register);

    public List<StudentRegistration> SearchStudentId(StudentRegistration register);
}
