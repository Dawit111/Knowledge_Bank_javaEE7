/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import javax.ejb.Local;
import entity.Answer;
import entity.Question;
import java.util.List;

/**
 *
 * @author BABI
 */
@Local
public interface Answer_sessionLocal {
    public List<Answer> findAllAnswerOfQuestion(Question question);
    
    public void postAnswer(Answer answer);
}
