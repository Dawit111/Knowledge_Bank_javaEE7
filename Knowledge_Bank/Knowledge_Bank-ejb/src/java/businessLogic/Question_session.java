/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import javax.ejb.Stateless;
import entity.Question;
import java.util.List;
import javax.ejb.EJB;
import mapper.QuestionFacade;

/**
 *
 * @author BABI
 */
@Stateless
public class Question_session implements Question_sessionLocal {
    @EJB
    QuestionFacade questionFacade;

    @Override
    public List<Question> findByQuestion(String question) {
      return questionFacade.findByQuestion(question);
    }
    @Override
    public void saveQuestion(Question question){
        questionFacade.create(question);
    }
  
}
