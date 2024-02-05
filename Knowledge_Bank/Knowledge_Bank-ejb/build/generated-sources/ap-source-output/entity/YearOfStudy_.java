package entity;

import entity.DepCoursYear;
import entity.StudentRegistration;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(YearOfStudy.class)
public class YearOfStudy_ { 

    public static volatile ListAttribute<YearOfStudy, StudentRegistration> studentRegistrationList;
    public static volatile SingularAttribute<YearOfStudy, String> name;
    public static volatile ListAttribute<YearOfStudy, DepCoursYear> depCoursYearList;
    public static volatile SingularAttribute<YearOfStudy, Integer> yearId;

}