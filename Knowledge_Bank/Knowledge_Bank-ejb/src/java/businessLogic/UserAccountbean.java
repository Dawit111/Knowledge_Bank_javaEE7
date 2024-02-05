/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Useraccount;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.UseraccountFacade;

/**
 *
 * @author BABI
 */
@Stateless
public class UserAccountbean implements UserAccountbeanLocal {

    @EJB
    UseraccountFacade useraccountFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Useraccount findByUserNameAndPassword(Useraccount useraccount) {
        return useraccountFacade.findByUserNameAndPassword(useraccount);
    }

    @Override
    public Useraccount findByPassword(Useraccount useraccount) {
        return useraccountFacade.findByPassword(useraccount);
    }

    @Override
    public void SaveAccount(Useraccount useraccount) {
        useraccountFacade.create(useraccount);
    }

    @Override
    public boolean getAccountDuplicate(Useraccount useraccount) {
        return useraccountFacade.getAccountDuplicate(useraccount);
    }

    @Override
    public Useraccount getbYId(Useraccount loggedinUser) {
        return useraccountFacade.find(loggedinUser.getId());
    }

}
