/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileControll;

import businessLogic.File_sessionLocal;
import businessLogic.UserAccountbeanLocal;
import common.SessionController;
import entity.Course;
import entity.FileStore;
import entity.DepCoursYear;
import entity.Department;
import entity.Faculty;
import entity.Useraccount;
import entity.YearOfStudy;
import et.gov.eep.commonApplications.utility.JsfUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mapper.FileStoreFacade;
import mapper.YearOfStudyFacade;
import mapper.DepartmentFacade;
import mapper.CourseFacade;
import mapper.FacultyFacade;
import mapper.DepCoursYearFacade;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.*;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author BABI
 */
//@ManagedBean
@Named(value = "fileControl")
@ViewScoped
public class fileControl implements Serializable {

    @Inject
    FileStore fileStore;
    SessionController session = new SessionController();
    @Inject
    DepCoursYear depCoursYear;
    @Inject
    Department department;
    @Inject
    Course course;
    @Inject
    YearOfStudy yearOfStudy;
    @Inject
    Useraccount loggedinUser;
    @Inject
    Faculty faculty;
    @EJB
    YearOfStudyFacade yearOfStudyFacade;
    @EJB
    FileStoreFacade fileStoreFacade;
    @EJB
    CourseFacade courseFacade;
    @EJB
    FacultyFacade facultyFacade;
    @EJB
    DepartmentFacade departmentFacade;
    @EJB
    DepCoursYearFacade depCoursYearFacade;
    @EJB
    File_sessionLocal file_sessionLocal;
    @EJB
    UserAccountbeanLocal userAccountbeanLocal;
    DataModel<FileStore> fileStoreDataModel;
    private String filename;
    private String fileType;
    private byte[] file;
    List<FileStore> allFile;
    StreamedContent files;
    StreamedContent adminSideFiles;
    private String icone = "ui-icon-upload";
    List<Department> departmentList = new ArrayList<>();
    List<Course> courseList = new ArrayList<>();
    List<YearOfStudy> yearList = new ArrayList<>();
    List<Faculty> facuList = new ArrayList<>();

    List<DepCoursYear> depyrList = new ArrayList<>();

    @PostConstruct
    public void init() {
        if (session.getSession().getAttribute("userId") != null) {
            loggedinUser = new Useraccount();
            System.out.println("session.getSession().getAttribute(\"userId\")===" + session.getSession().getAttribute("userId"));
            loggedinUser.setId(Integer.valueOf(session.getSession().getAttribute("userId").toString()));
            loggedinUser = userAccountbeanLocal.getbYId(loggedinUser);
            System.out.println("fullname0===" + loggedinUser.getUserProfile().getFirstName());
        } else {
            session.Signout();
        }

    }

//<editor-fold defaultstate="collapsed" desc="getter and setter">
    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public SessionController getSession() {
        if (session == null) {
            session = new SessionController();
        }
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public Useraccount getLoggedinUser() {
        if (loggedinUser == null) {
            loggedinUser = new Useraccount();
        }
        return loggedinUser;
    }

    public void setLoggedinUser(Useraccount loggedinUser) {
        this.loggedinUser = loggedinUser;
    }

    public FileStore getFileStore() {
        if (fileStore == null) {
            fileStore = new FileStore();
        }
        return fileStore;
    }

    public void setFileStore(FileStore fileStore) {

        this.fileStore = fileStore;
    }

    public DataModel<FileStore> getFileStoreDataModel() {
        //allFile = fileStoreFacade.findAll();
        searchFiles();
        getAllFile();

        return fileStoreDataModel;
    }

    public void setFileStoreDataModel(DataModel<FileStore> fileStoreDataModel) {
        this.fileStoreDataModel = fileStoreDataModel;
    }

    public void recreatDatamodel() {
        fileStoreDataModel = null;
        fileStoreDataModel = new ListDataModel(new ArrayList(allFile));
    }

    public List<FileStore> getAllFile() {

        System.out.println("getter val");
        if (allFile == null) {
            //System.out.println("all file is null");
            allFile = new ArrayList<>();
        }
        recreatDatamodel();
        searchFiles();
        return allFile;
    }

    public void setAllFile(List<FileStore> allFile) {
        this.allFile = allFile;
    }

    public List<Faculty> getFacuList() {
        facuList = facultyFacade.findAll();
        return facuList;
    }

    public void setFacuList(List<Faculty> facuList) {
        this.facuList = facuList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Course> getCourseList() {
        courseList = courseFacade.findAll();
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<DepCoursYear> getDepyrList() {
        if (depyrList == null) {
            depyrList = new ArrayList<>();
        }
        return depyrList;
    }

    public void setDepyrList(List<DepCoursYear> depyrList) {
        this.depyrList = depyrList;
    }

    public List<YearOfStudy> getYearList() {
        yearList = yearOfStudyFacade.findAll();
        return yearList;
    }

    public void setYearList(List<YearOfStudy> yearList) {
        this.yearList = yearList;
    }

    public YearOfStudy getYearOfStudy() {
        if (yearOfStudy == null) {
            yearOfStudy = new YearOfStudy();
        }
        return yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Faculty getFaculty() {
        if (faculty == null) {
            faculty = new Faculty();
        }
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Department getDepartment() {
        if (department == null) {
            department = new Department();
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Course getCourse() {
        if (course == null) {
            course = new Course();
        }
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public DepCoursYear getDepCoursYear() {
        if (depCoursYear == null) {
            depCoursYear = new DepCoursYear();
        }
        return depCoursYear;
    }

    public void setDepCoursYear(DepCoursYear depCoursYear) {
        this.depCoursYear = depCoursYear;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public StreamedContent getFiles() throws Exception {
        // if (fileSelected == true) {
        // recreatDatamodel();
        fileStore = fileStoreDataModel.getRowData();
        File filePath = new File("" + fileStore.getName() + "."
                + fileStore.getType());

        if (fileStore.getFiles() != null) {
            FileUtils.writeByteArrayToFile(filePath, fileStore.getFiles());
        }
        InputStream input = new FileInputStream(filePath);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        files = new DefaultStreamedContent(input, externalContext.getMimeType(filePath.getName()), fileStore.getName() + "." + fileStore.getType());

//       }
//       else {
//       JsfUtil.addFatalMessage("failed! please select the row");
//       }
        return files;
    }

    public void setFiles(StreamedContent files) {
        this.files = files;
    }

    public StreamedContent getAdminSideFiles() throws Exception {
        if (fileSelected == true) {
            // recreatDatamodel();
            //fileStore = fileStoreDataModel.getRowData();
            File filePath = new File("" + fileStore.getName() + "."
                    + fileStore.getType());

            if (fileStore.getFiles() != null) {
                FileUtils.writeByteArrayToFile(filePath, fileStore.getFiles());
            }
            InputStream input = new FileInputStream(filePath);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            adminSideFiles = new DefaultStreamedContent(input, externalContext.getMimeType(filePath.getName()), fileStore.getName() + "." + fileStore.getType());

        } else {
            JsfUtil.addFatalMessage("failed! please select the row");
        }
        return adminSideFiles;
    }

    public void setAdminSideFiles(StreamedContent adminSideFiles) {
        this.adminSideFiles = adminSideFiles;
    }

//</editor-fold>
    public void handlePatientPhotoUpload(FileUploadEvent event) {
        filename = event.getFile().getFileName().split("\\.")[0];
        fileType = event.getFile().getFileName().split("\\.")[1];
        System.out.println("file name==============" + filename);
        System.out.println("file type==============" + fileType);
        try {
            file = extractByteArray(event.getFile().getInputstream());
            fileStore.setFiles(file);
            fileStore.setName(filename);
            fileStore.setType(fileType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] extractByteArray(InputStream inputStream1) {
        try {
            byte[] byteFile = null;
            int len = 0;
            len = inputStream1.available();
            byteFile = new byte[len];
            inputStream1.read(byteFile, 0, len);
            return byteFile;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void saveFile() {
        System.out.println("file name===" + fileStore.getName());
        //fileStore.setName(fileStore.getName());
        if (fileStore.getDepCourse()==null){
        JsfUtil.addFatalMessage("please check the Academics fields");
        }else if(fileStore.getFiles()==null){
        JsfUtil.addFatalMessage("please choose a file");
        }
        else {
        fileStore.setUserFk(loggedinUser);
        file_sessionLocal.saveFile(fileStore);
        JsfUtil.addSuccessMessage("uploaded succesfully");
        faculty = new Faculty();
        department = new Department();
        yearOfStudy = new YearOfStudy();
        course = new Course();
        fileStore = new FileStore();
        }
      
    }

    public void deleteFile() {
        //fileStore=fileStoreDataModel.getRowData();
        file_sessionLocal.deleteFile(fileStore);
        JsfUtil.addSuccessMessage("deleted succesfully");
    }
//=============================ROW SELECTION METHODS==================================
    public boolean fileSelected = false;

    public boolean isFileSelected() {
        return fileSelected;
    }

    public void setFileSelected(boolean fileSelected) {
        this.fileSelected = fileSelected;
    }

    public void onRowSelect(SelectEvent event) throws Exception {
        fileStore = (FileStore) event.getObject();
        fileSelected = true;
    }

    //================================================================================
    public void viewFilesPageForAdminOrUser() throws IOException {
        session = new SessionController();
        System.out.println("the role is as th follwonfgakjrfgnrkjfkj" + session.getSession().getAttribute("role"));
        if (session.getSession().getAttribute("role").equals("admin")) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("download.xhtml");
        } else if (session.getSession().getAttribute("role").equals("stud")) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("userHomePage.xhtml");
        }
    }

    public void uploadPage() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("upload.xhtml");
    }

    public void viewAdminOrUserDashboard() throws IOException {

        if (session.getSession().getAttribute("role").equals("admin")) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("Admin_Dashboard.xhtml");
        } else if (session.getSession().getAttribute("role").equals("stud")) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("userDashboard.xhtml");
        }
    }

    public void refreshAdminOrUserUploadPage() throws IOException {

        if (session.getSession().getAttribute("role").equals("admin")) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("upload.xhtml");
        } else if (session.getSession().getAttribute("role").equals("stud")) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("upload.xhtml");
        }
    }

    public void downloadPage() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("download.xhtml");
    }

    public void discussionPage() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("QApage.xhtml");

    }
    public void refreshUserHomePage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("userHomePage.xhtml");
    }

    //---------------------------------------------------------------------------------------------------------

    public void FacultyVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            faculty.setFacId(Integer.valueOf(event.getNewValue().toString()));
            faculty = facultyFacade.find(faculty.getFacId());
            departmentList = departmentFacade.findAllDepByFac(faculty.getFacId());
        }

    }

    public void searchFiles() {
        System.out.println("i am in search file findAll------------------");
//        department = new Department();
//        yearOfStudy = new YearOfStudy();
//        course = new Course();
//        System.out.println("course=========="+course.getCourseName());
//        if (department == null && yearOfStudy == null && course == null) {
//            allFile = fileStoreFacade.findAll();
        //  }
        if (department.getDepId() != null && yearOfStudy.getYearId() == null) {
            allFile = fileStoreFacade.findBydepartment(department.getDepId());
            System.out.println("filesBy Dep==" + allFile);
        } else if (yearOfStudy.getYearId() != null && course == null && department == null) {
            allFile = fileStoreFacade.findByYear(yearOfStudy.getYearId());
        } else if (yearOfStudy.getYearId() != null && course == null && department != null) {
            allFile = fileStoreFacade.findByYearAndDepartment(yearOfStudy.getYearId(), department.getDepId());
        } else if (yearOfStudy.getYearId() != null && course != null && department != null) {
            allFile = fileStoreFacade.findByYearAndDepartmentAndCourse(yearOfStudy.getYearId(), department.getDepId(), course.getCoId());
        } else if (searchByFileName == true) {
            allFile = file_sessionLocal.findByFileName(fileStore.getName());
        } else {
            System.out.println("nnnnnnnnnnn");
            allFile = fileStoreFacade.findAll();
        }
    }
    public boolean searchByFileName = false;

    public void searchByFileName() {
        System.out.println("inside if search method");
        if (fileStore.getName().isEmpty()) {
            allFile = fileStoreFacade.findAll();
        } else {
            searchByFileName = true;
            searchFiles();
            System.out.println("file size=========" + allFile.size());
        }

    }

    public void DeptVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            department.setDepId(Integer.valueOf(event.getNewValue().toString()));
            department = departmentFacade.find(department.getDepId());
            System.out.println("event dept name=########==" + department.getDepName());
            System.out.println("event  dept Id=########==" + department);
            depCoursYear = new DepCoursYear();
            depCoursYear.setDepFk(department);
            searchFiles();
            // allFile = fileStoreFacade.findBydepartment(department.getDepId());

        }

    }

    public void YearOfStudyVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            yearOfStudy.setYearId(Integer.valueOf(event.getNewValue().toString()));
            yearOfStudy = yearOfStudyFacade.find(yearOfStudy.getYearId());
            System.out.println("Size before===" + depyrList.size());
            depCoursYear.setYosFk(yearOfStudy);
            searchFiles();
            depyrList = depCoursYearFacade.findByDeptYr(depCoursYear);
            System.out.println("Size===" + depyrList.size());
            //allFile = fileStoreFacade.findByYear(yearOfStudy.getYearId());
            System.out.println("year name-----------" + allFile);

        }

    }

    public void CourseVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val CourseVCL===" + event.getNewValue());
            course = new Course();
            course.setCoId(Integer.valueOf(event.getNewValue().toString()));
            course = courseFacade.find(course.getCoId());

            depCoursYear.setYosFk(yearOfStudy);
            depCoursYear.setDepFk(department);
            depCoursYear.setCourseFk(course);
            depCoursYear = depCoursYearFacade.findByDeptCorYr(depCoursYear);
            fileStore.setDepCourse(depCoursYear);
            searchFiles();
            //allFile = fileStoreFacade.findByCourse(course.getCoId());
        }
    }

    //---------------------------------------------------------------------------------------------------------
    public int fileAmount(int files) {
        allFile = file_sessionLocal.findAll();
        return files = allFile.size();
    }

    public fileControl() {
    }
}
