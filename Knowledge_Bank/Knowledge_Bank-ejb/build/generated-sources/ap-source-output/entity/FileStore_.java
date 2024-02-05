package entity;

import entity.DepCoursYear;
import entity.Useraccount;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-10-26T09:22:39")
@StaticMetamodel(FileStore.class)
public class FileStore_ { 

    public static volatile SingularAttribute<FileStore, String> dateOfUpload;
    public static volatile SingularAttribute<FileStore, String> name;
    public static volatile SingularAttribute<FileStore, String> description;
    public static volatile SingularAttribute<FileStore, byte[]> files;
    public static volatile SingularAttribute<FileStore, DepCoursYear> depCourse;
    public static volatile SingularAttribute<FileStore, Useraccount> userFk;
    public static volatile SingularAttribute<FileStore, String> type;
    public static volatile SingularAttribute<FileStore, Integer> fileId;

}