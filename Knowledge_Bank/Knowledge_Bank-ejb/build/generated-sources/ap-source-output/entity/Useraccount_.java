package entity;

import entity.AnswerUseraccount;
import entity.StudentRegistration;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(Useraccount.class)
public class Useraccount_ { 

    public static volatile SingularAttribute<Useraccount, String> password;
    public static volatile SingularAttribute<Useraccount, String> role;
    public static volatile ListAttribute<Useraccount, AnswerUseraccount> answerUseraccountList;
    public static volatile SingularAttribute<Useraccount, Integer> id;
    public static volatile SingularAttribute<Useraccount, StudentRegistration> userProfile;
    public static volatile SingularAttribute<Useraccount, String> username;

}