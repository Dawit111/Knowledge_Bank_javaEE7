/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;


import common.utility.StringDateManipulation;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Baya
 */
public class SessionController implements Serializable {

    boolean renderpatientimg;
    boolean renderstafftimg;
//<editor-fold defaultstate="collapsed" desc="getter and setter">


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean isRenderpatientimg() {
        return renderpatientimg;
    }

    public void setRenderpatientimg(boolean renderpatientimg) {
        this.renderpatientimg = renderpatientimg;
    }

    public boolean isRenderstafftimg() {
        return renderstafftimg;
    }

    public void setRenderstafftimg(boolean renderstafftimg) {
        this.renderstafftimg = renderstafftimg;
    }

   
//</editor-fold>

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    HttpSession session = request.getSession();

    public void session(String UserName, String Password, int UserId, String Role) {
        System.out.println("insed Session method");
      

        session.setAttribute("username", UserName);
        session.setAttribute("password", Password);
        session.setAttribute("userId", UserId);
        session.setAttribute("role", Role);
        //session.setAttribute("fullname", FullName);
    }

    public void Signout() {
        String systemBundle = "commn/System_Bundle";
        //String ip = Utility.getBundleValue(systemBundle, "ip");
        //String port = Utility.getBundleValue(systemBundle, "port");
        System.out.println("inside signout ");
        session = request.getSession();
        session.invalidate();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session = request.getSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        try {
            System.out.println("inside signout try");
            String URL = "faces/login.xhtml";
            System.out.println("URl==" + URL);
            //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),null, "faces/login.xhtml");
            // externalContext.redirect("http://" + ip + ":8080/Online_EMR-war/../../faces/login.xhtml");
            externalContext.redirect(URL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



    

}
