/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycontroller;

import businessLogic.UserAccountbeanLocal;
import businessLogic.User_sessionLocal;
import common.JsfUtil;
import entity.StudentRegistration;
import java.io.Serializable;
import entity.Useraccount;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named("userAccountController")
public class UserAccountController implements Serializable {

    @Inject
    Useraccount useraccount;
    @EJB
    UserAccountbeanLocal userAccountbeanLocal;
    @Inject
    StudentRegistration register;
    @EJB
    User_sessionLocal user_sessionLocal;

    public Useraccount getUseraccount() {
        if (useraccount == null) {
            useraccount = new Useraccount();
        }
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }

    public StudentRegistration getRegister() {
        return register;
    }

    public void setRegister(StudentRegistration register) {
        this.register = register;
    }

    public List<StudentRegistration> searchStudentByIdNo(String idNumber) {
        register = new StudentRegistration();
        List<StudentRegistration> registeredStud = null;
        register.setIdNumber(idNumber);
        registeredStud = user_sessionLocal.SearchStudentId(register);
        return registeredStud;
    }

    public void getStudentByIDNumber(SelectEvent event) {
        register = (StudentRegistration) event.getObject();

    }

    String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void saveAcc() throws NoSuchAlgorithmException {
        if (useraccount.getPassword().equals(confirmPassword)) {
            useraccount.setUserProfile(register);
            if (userAccountbeanLocal.getAccountDuplicate(useraccount)) {
                JsfUtil.addFatalMessage("Unable to create Account,This Id Number is Invalid!!!");
            } else {
                try {
                    String encPassword;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(useraccount.getPassword().getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < bytes.length; i++) {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    encPassword = sb.toString();
                    useraccount.setPassword(encPassword);
                    System.out.println("password==" + useraccount.getPassword());
                    useraccount.setRole("stud");
                    useraccount.setUserProfile(register);
                    userAccountbeanLocal.SaveAccount(useraccount);
                    JsfUtil.addSuccessMessage("Account Create success");
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        } else {
            JsfUtil.addFatalMessage("The password and ConfirmPassword do not match!");
        }
        clearUserAcc();
    }
    public void clearUserAcc(){
    useraccount = new Useraccount();
    register = new StudentRegistration();
    confirmPassword = new String();
    }

    public UserAccountController() {
    }

}
