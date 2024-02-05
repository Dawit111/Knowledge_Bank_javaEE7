package entity;

import entity.AnswerUseraccount;
import entity.Question;
import entity.Useraccount;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, String> answerContent;
    public static volatile SingularAttribute<Answer, Integer> dislikes;
    public static volatile ListAttribute<Answer, AnswerUseraccount> answerUseraccountList;
    public static volatile SingularAttribute<Answer, Question> qIdFk;
    public static volatile SingularAttribute<Answer, Integer> ansId;
    public static volatile SingularAttribute<Answer, Useraccount> userFk;
    public static volatile SingularAttribute<Answer, Integer> likes;

}