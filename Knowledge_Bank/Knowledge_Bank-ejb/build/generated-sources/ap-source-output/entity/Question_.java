package entity;

import entity.Answer;
import entity.DepCoursYear;
import entity.Useraccount;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile ListAttribute<Question, Answer> answerList;
    public static volatile SingularAttribute<Question, DepCoursYear> catagoryFk;
    public static volatile SingularAttribute<Question, String> question;
    public static volatile SingularAttribute<Question, String> dateAsked;
    public static volatile SingularAttribute<Question, Useraccount> userFk;
    public static volatile SingularAttribute<Question, Integer> qId;

}