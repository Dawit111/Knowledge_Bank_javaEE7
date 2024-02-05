package entity;

import entity.Course;
import entity.Department;
import entity.FileStore;
import entity.Question;
import entity.YearOfStudy;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(DepCoursYear.class)
public class DepCoursYear_ { 

    public static volatile SingularAttribute<DepCoursYear, Department> depFk;
    public static volatile SingularAttribute<DepCoursYear, Integer> dcyId;
    public static volatile SingularAttribute<DepCoursYear, Course> courseFk;
    public static volatile ListAttribute<DepCoursYear, Question> questionList;
    public static volatile ListAttribute<DepCoursYear, FileStore> fileStoreList;
    public static volatile SingularAttribute<DepCoursYear, YearOfStudy> yosFk;

}