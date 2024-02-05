package entity;

import entity.Answer;
import entity.Useraccount;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(AnswerUseraccount.class)
public class AnswerUseraccount_ { 

    public static volatile SingularAttribute<AnswerUseraccount, Answer> ansFk;
    public static volatile SingularAttribute<AnswerUseraccount, Integer> auId;
    public static volatile SingularAttribute<AnswerUseraccount, Useraccount> userAccFk;
    public static volatile SingularAttribute<AnswerUseraccount, String> likedOrDisliked;

}