/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.AnswerUseraccount;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.AnswerUseraccountFacade;

/**
 *
 * @author BABI
 */
@Stateless
public class UserAnswer_session implements UserAnswer_sessionLocal {

    @EJB
    AnswerUseraccountFacade answerUseraccountFacade;

    @Override
    public void saveLikedOnce(AnswerUseraccount answerUseraccount) {
        answerUseraccountFacade.create(answerUseraccount);
    }

    @Override
    public void saveLikedTwice(AnswerUseraccount answerUseraccount) {
        System.out.println("-------------------i am in remove bcause liked twice");
        answerUseraccountFacade.remove(answerUseraccount);
    }

    @Override
    public boolean getLikeDuplicate(AnswerUseraccount answerUseraccount) {
        return answerUseraccountFacade.getLikeDuplicate(answerUseraccount);
    }

    @Override
    public AnswerUseraccount findByLikeDuplicate(AnswerUseraccount answerUseraccount) {
        return answerUseraccountFacade.findByLikeDuplicate(answerUseraccount);
    }

}
