package entity;

import entity.Department;
import entity.Faculty;
import entity.Useraccount;
import entity.YearOfStudy;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(StudentRegistration.class)
public class StudentRegistration_ { 

    public static volatile SingularAttribute<StudentRegistration, String> firstName;
    public static volatile SingularAttribute<StudentRegistration, String> lastName;
    public static volatile ListAttribute<StudentRegistration, Useraccount> useraccountList;
    public static volatile SingularAttribute<StudentRegistration, String> gender;
    public static volatile SingularAttribute<StudentRegistration, String> idNumber;
    public static volatile SingularAttribute<StudentRegistration, Department> department;
    public static volatile SingularAttribute<StudentRegistration, Integer> studId;
    public static volatile SingularAttribute<StudentRegistration, YearOfStudy> yearOfStudy;
    public static volatile SingularAttribute<StudentRegistration, Integer> status;
    public static volatile SingularAttribute<StudentRegistration, Faculty> faculty;

}