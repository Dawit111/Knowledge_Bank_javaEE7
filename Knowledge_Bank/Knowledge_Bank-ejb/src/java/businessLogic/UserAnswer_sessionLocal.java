/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.AnswerUseraccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author BABI
 */
@Local
public interface UserAnswer_sessionLocal {

    public void saveLikedOnce(AnswerUseraccount answerUseraccount);
    
    public boolean getLikeDuplicate(AnswerUseraccount answerUseraccount);

    public void saveLikedTwice(AnswerUseraccount answerUseraccount);
    
    public AnswerUseraccount findByLikeDuplicate(AnswerUseraccount answerUseraccount);
    
}
