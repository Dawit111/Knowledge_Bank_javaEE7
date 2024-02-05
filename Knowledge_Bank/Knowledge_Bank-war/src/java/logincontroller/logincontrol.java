/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logincontroller;

import businessLogic.UserAccountbeanLocal;
import common.JsfUtil;
import common.SessionController;
import entity.Useraccount;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author BABI
 */
@Named("logincontrol")
@ViewScoped
public class logincontrol implements Serializable {

    @Inject
    Useraccount useraccount;

    @EJB
    UserAccountbeanLocal userAccountbeanLocal;

    SessionController sessionController = new SessionController();
    /**
     * Creates a new instance of logincontrol
     */

   public String password;

    //<editor-fold defaultstate="collapsed" desc="getter and setter">
    public Useraccount getUseraccount() {
        if (useraccount == null) {
            useraccount = new Useraccount();
        }
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }

    public SessionController getSessionController() {
        if (sessionController == null) {
            sessionController = new SessionController();
        }
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//</editor-fold>

    public void verify() throws IOException, NoSuchAlgorithmException {
        Useraccount user = new Useraccount();
        //String encPassword;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        useraccount.setPassword(sb.toString());
        user = userAccountbeanLocal.findByUserNameAndPassword(useraccount);
        System.out.println("inside mtd");
        if (user == null) {
            JsfUtil.addFatalMessage("Invalid username or password");

        } else {
//            System.out.println("studFname-======" + user.getUserProfile().getFirstName());
//            System.out.println("user.getUsername()==" + user.getUsername());
//            System.out.println(" user.getPassword()==" + user.getPassword());
//            System.out.println("user.getId()==" + user.getId());
            sessionController.session(user.getUsername(), user.getPassword(), user.getId(), user.getRole());

            if (user.getRole().equalsIgnoreCase("stud")) {
                if (user.getUserProfile().getStatus() == 1) {
                    System.out.println("inside stud");
                    FacesContext context = FacesContext.getCurrentInstance();
                    ExternalContext externalContext = context.getExternalContext();
                    externalContext.redirect("userDashboard.xhtml");
                } else {
                    JsfUtil.addFatalMessage("sorry! your Status is Inactive,Contact the Adminstrator");
                }

            } //i added this code
            else if (user.getRole().equalsIgnoreCase("admin")) {
                System.out.println("inside admin");
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect("Admin_Dashboard.xhtml");
            }

            //end of it
        }

    }

    public void verifyAdmin() throws IOException, NoSuchAlgorithmException {
        System.err.println("inside"+password);
        useraccount = new Useraccount();
        Useraccount admin = new Useraccount();
          MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        useraccount.setPassword(sb.toString());
        System.out.println("password==" + useraccount);
        admin = userAccountbeanLocal.findByPassword(useraccount);
        if (admin == null) {
            JsfUtil.addFatalMessage("Invalid Password");
        } else if (admin.getRole().equalsIgnoreCase("admin")) {
             sessionController.session(admin.getUsername(), admin.getPassword(), admin.getId(), admin.getRole());
            System.out.println(" admin.getPassword()==----------------------------------------------------" + admin.getPassword());
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("Admin_Dashboard.xhtml");
        }
    }

}
