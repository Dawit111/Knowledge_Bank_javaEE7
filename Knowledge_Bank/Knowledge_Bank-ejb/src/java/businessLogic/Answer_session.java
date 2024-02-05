/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Answer;
import entity.Question;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.AnswerFacade;

/**
 *
 * @author BABI
 */
@Stateless
public class Answer_session implements Answer_sessionLocal {
    @EJB
    AnswerFacade answerFacade;

    @Override
   public List<Answer> findAllAnswerOfQuestion(Question question) {
    return answerFacade.findAllAnswerOfQuestion(question.getQId());
   }
    @Override
   public void postAnswer(Answer answer){
     answerFacade.create(answer);
   }
}
