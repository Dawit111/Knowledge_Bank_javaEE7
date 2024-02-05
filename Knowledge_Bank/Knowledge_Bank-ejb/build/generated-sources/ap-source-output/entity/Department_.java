package entity;

import entity.DepCoursYear;
import entity.Faculty;
import entity.StudentRegistration;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile ListAttribute<Department, StudentRegistration> studentRegistrationList;
    public static volatile SingularAttribute<Department, Integer> depId;
    public static volatile SingularAttribute<Department, Faculty> facuId;
    public static volatile SingularAttribute<Department, String> depName;
    public static volatile ListAttribute<Department, DepCoursYear> depCoursYearList;

}