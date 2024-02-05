package entity;

import entity.Department;
import entity.StudentRegistration;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(Faculty.class)
public class Faculty_ { 

    public static volatile SingularAttribute<Faculty, String> facuName;
    public static volatile ListAttribute<Faculty, StudentRegistration> studentRegistrationList;
    public static volatile SingularAttribute<Faculty, Integer> facId;
    public static volatile ListAttribute<Faculty, Department> departmentList;

}