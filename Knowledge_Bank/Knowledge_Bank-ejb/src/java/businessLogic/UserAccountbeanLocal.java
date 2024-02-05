/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Useraccount;
import javax.ejb.Local;

/**
 *
 * @author BABI
 */
@Local
public interface UserAccountbeanLocal {

    public Useraccount findByUserNameAndPassword(Useraccount useraccount);
    public Useraccount findByPassword(Useraccount useraccount);
    
    public void SaveAccount(Useraccount useraccount);
    
    public boolean getAccountDuplicate(Useraccount useraccount);

    public Useraccount getbYId(Useraccount loggedinUser);

    

}
