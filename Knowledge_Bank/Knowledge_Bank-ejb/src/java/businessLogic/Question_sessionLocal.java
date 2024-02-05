/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Question;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author BABI
 */
@Local
public interface Question_sessionLocal {

    public List<Question> findByQuestion(String question);

    public void saveQuestion(Question question);
    
}
