package mycontroller;

import businessLogic.dep_sessionLocal;
import businessLogic.Course_sessionLocal;
import businessLogic.Dcy_sessionLocal;
import businessLogic.Faculty_sessionLocal;
import businessLogic.UserAccountbeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import businessLogic.User_sessionLocal;
import businessLogic.dep_session;

import common.JsfUtil;
import common.SessionController;
import entity.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import mapper.CourseFacade;
import mapper.DepCoursYearFacade;
import mapper.DepartmentFacade;
import mapper.FacultyFacade;
import mapper.StudentRegistrationFacade;
import mapper.YearOfStudyFacade;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BABI
 */
@SessionScoped
@ManagedBean
@RequestScoped
@Named("StudentController")
@ViewScoped
public class StudentController implements Serializable {

    @Inject
    StudentRegistration register;
    @Inject
    Useraccount useraccount;
    @Inject
    Useraccount loggedinUser;
    @EJB
    UserAccountbeanLocal userAccountbeanLocal;
    @EJB
    User_sessionLocal user_sessionLocal;
    @EJB
    FacultyFacade facultyFacade;
    @EJB
    Faculty_sessionLocal faculty_sessionLocal;
    @EJB
    DepartmentFacade departmentFacade;
    @EJB
    YearOfStudyFacade yearOfStudyFacade;
    @EJB
    CourseFacade courseFacade;
    @EJB
    DepCoursYearFacade depCoursYearFacade;
    @EJB
    Dcy_sessionLocal dcy_sessionLocal;
    @EJB
    Course_sessionLocal course_sessionLocal;
    @EJB
    dep_sessionLocal dep_sessionLocal;

    private StudentRegistrationFacade registerFacade;
    List<StudentRegistration> studentList = new ArrayList<>();
    DataModel<StudentRegistration> regDatamodel;
    @Inject
    Department department;
    @Inject
    Faculty faculty;
    @Inject
    YearOfStudy yearOfstudy;
    @Inject
    Course course;
    @Inject
    DepCoursYear depCoursYear;
    SessionController session = new SessionController();

    List<Course> courseList = new ArrayList<>();
    List<Department> depList = new ArrayList<>();
    List<Department> AllDepList = new ArrayList<>();
    List<Faculty> facuList = new ArrayList<>();
    List<YearOfStudy> yearofstudyList = new ArrayList<>();
    List<DepCoursYear> academicsList = new ArrayList<>();
    DataModel<DepCoursYear> acadDataModel;

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

    public StudentRegistration getRegister() {
        return register;
    }

    public void setRegister(StudentRegistration register) {
        this.register = register;
    }

    public StudentRegistrationFacade getStudentRegistrationFacade() {
        return registerFacade;
    }

    public void setStudentRegistrationFacade(StudentRegistrationFacade registerFacade) {
        this.registerFacade = registerFacade;
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

    public Course getCourse() {
        if (course == null) {
            course = new Course();
        }
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public Faculty getFaculty() {
        if (faculty == null) {
            faculty = new Faculty();
        }
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public YearOfStudy getYearOfstudy() {
        if (yearOfstudy == null) {
            yearOfstudy = new YearOfStudy();
        }
        return yearOfstudy;
    }

    public void setYearOfstudy(YearOfStudy yearOfstudy) {
        this.yearOfstudy = yearOfstudy;
    }

    public List<Course> getCourseList() {
        courseList = courseFacade.findAll();
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Department> getAllDepList() {
        AllDepList = departmentFacade.findAll();
        return AllDepList;
    }

    public void setAllDepList(List<Department> AllDepList) {
        this.AllDepList = AllDepList;
    }

    public List<Department> getDepList() {
        return depList;
    }

    public void setDepList(List<Department> depList) {
        this.depList = depList;
    }

    public List<Faculty> getFacuList() {
        facuList = facultyFacade.findAll();
        return facuList;
    }

    public void setFacuList(List<Faculty> facuList) {
        this.facuList = facuList;
    }

    public List<YearOfStudy> getYearofstudyList() {
        yearofstudyList = yearOfStudyFacade.findAll();
        return yearofstudyList;
    }

    public void setYearofstudyList(List<YearOfStudy> yearofstudyList) {
        this.yearofstudyList = yearofstudyList;
    }

    public List<DepCoursYear> getAcademicsList() {
        academicsList = dcy_sessionLocal.ViewAcademicsData(depCoursYear);
        recreatAcadDataModel();
        return academicsList;
    }

    public void setAcademicsList(List<DepCoursYear> academicsList) {
        this.academicsList = academicsList;
    }

    public DataModel<DepCoursYear> getAcadDataModel() {
        getAcademicsList();
        return acadDataModel;
    }

    public void setAcadDataModel(DataModel<DepCoursYear> acadDataModel) {
        this.acadDataModel = acadDataModel;
    }

    public DataModel<StudentRegistration> getRegDatamodel() {

        //getStudentList();
        return regDatamodel;
    }

    public void setRegDatamodel(DataModel<StudentRegistration> regDatamodel) {
        this.regDatamodel = regDatamodel;
    }

    public List<StudentRegistration> getStudentList() {
        studentList = user_sessionLocal.viewUser(register);
        recreatRegDatamodel();
        return studentList;
    }

    public void setStudentList(List<StudentRegistration> studentList) {
        this.studentList = studentList;
    }

    public StudentRegistration getStudentRegistration() {
        if (register == null) {
            register = new StudentRegistration();
        }
        return register;
    }

    public void setStudentRegistration(StudentRegistration register) {
        this.register = register;
    }

    public Useraccount getUseraccount() {
        if (useraccount == null) {
            useraccount = new Useraccount();
        }
        return useraccount;
    }

    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }

//</editor-fold>
    /**
     * Creates a new instance of studController
     */
    public void recreatRegDatamodel() {
        regDatamodel = null;
        regDatamodel = new ListDataModel(new ArrayList(studentList));
    }

    public void recreatAcadDataModel() {
        acadDataModel = null;
        acadDataModel = new ListDataModel(new ArrayList(academicsList));

    }
//=============================ROW SELECTION METHODS==================================

    public void UserSelection(SelectEvent event) {
        register = new StudentRegistration();
        System.out.println("event val==" + event.getObject());
        register = (StudentRegistration) event.getObject();
        faculty = register.getFaculty();
        depList = departmentFacade.findAllDepByFac(faculty.getFacId());
        department = register.getDepartment();
        yearOfstudy = register.getYearOfStudy();
        regpnl = true;
        searchpnl = false;
        DisableDelete = false;
        icone = "ui-icon-search";
        saveorUpdateBundle = "Update";
        SaveOrUpdateStatus = 1;
    }

    public void AcademicsSelection(SelectEvent event) {
        depCoursYear = new DepCoursYear();
        System.out.println("event val-=====---" + event.getObject());
        depCoursYear = (DepCoursYear) event.getObject();
        yearOfstudy = depCoursYear.getYosFk();
        course = depCoursYear.getCourseFk();
        department = depCoursYear.getDepFk();
        faculty = department.getFacuId();
        depList = departmentFacade.findAllDepByFac(faculty.getFacId());

    }

    public void onRowSelectCourse(SelectEvent event) {
        course = (Course) event.getObject();
    }

    public void onRowSelectDepartment(SelectEvent event) {
        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooo");

        department = (Department) event.getObject();

    }
//==========================================================================================================

    public void save() {
        System.out.println("=======inside save");
        user_sessionLocal.saveUser(register);
        JsfUtil.addSuccessMessage("Data saved successfully");
        clear();
    }

    public List<StudentRegistration> findAll() {
        System.out.println("inside findAll,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        return this.registerFacade.findAll();
    }

    public String registerPage() {
        return "UserRegistration";
    }

    public void clear() {
        register = new StudentRegistration();
        faculty = new Faculty();
        department = new Department();
        yearOfstudy = new YearOfStudy();
        depList = new ArrayList<>();
        course = new Course();
    }

    public void delete() {
        try{
        System.out.println("=======inside delete");
        //register = regDatamodel.getRowData();
        studentList.remove(register);
        user_sessionLocal.deleteUser(register);
        recreatRegDatamodel();
        JsfUtil.addSuccessMessage("Data Deleted Successully");
        } catch(Exception ex){
        JsfUtil.addFatalMessage("cannot Delete! Chiild Record found");
        }
        clear();
    }

    public String editor_page(StudentRegistration nure) {
        System.out.println("inside editor_page...............................................");
        this.register = regDatamodel.getRowData();
        nure = this.register;
        studentList.remove(register);
        return "Admin_edit";
    }

    public String Edit_and_Save() {
        System.out.println("=======inside edited and saved-----------");
        user_sessionLocal.editUser(this.register);
        this.register = new StudentRegistration();
        recreatRegDatamodel();
        //JsfUtil.addSuccessMessage("Data edited successfully");
        return "admin_home_page";

    }

    public void searchStudent() {
        System.out.println("inside if search method");

        if (register.getFirstName().isEmpty()) {
            System.out.println("inside if empty");
            studentList = user_sessionLocal.findAll();

        } else {
            //   System.out.println("inside if else not empty");
            // System.out.println("yaaaaaaaaaaaaaaaaaaaa" + register.getFname());

            studentList = user_sessionLocal.findByFname(register.getFirstName());

        }
        System.out.println("stude size+" + studentList.size());
        recreatRegDatamodel();
    }

    public List<StudentRegistration> searchStudentByIdNo(String idNumber) {
        register = new StudentRegistration();
        List<StudentRegistration> registeredStud = null;
        register.setIdNumber(idNumber);
        registeredStud = user_sessionLocal.SearchStudentId(register);
        return registeredStud;
    }

    public void getStudentByIDNumber(SelectEvent event) {
        register = (StudentRegistration) event.getObject();

    }

    /*public String Edit() {
     System.out.println("inside editor save...............................................");
     this.registerFacade.edit(this.register);
     this.register = new StudentRegistration();
     return "admin_home_page";
     }*/
//---------------------------------------------------------------------------------------------
    private String saveorUpdateBundle = "Save";
    private String createOrSearchBundle = "New";
    private String icone = "ui-icon-plus";

    private boolean renderPnlCreateAdditional = false;
    private boolean renderPnlSerchPage = true;
    private boolean btnNewRender = false;
    private boolean DisableDelete = true;
    private Integer SaveOrUpdateStatus = 0;
    private boolean searchpnl = true;
    private boolean regpnl = false;

    public boolean isDisableDelete() {
        return DisableDelete;
    }

    public void setDisableDelete(boolean DisableDelete) {
        this.DisableDelete = DisableDelete;
    }

    public Integer getSaveOrUpdateStatus() {
        return SaveOrUpdateStatus;
    }

    public void setSaveOrUpdateStatus(Integer SaveOrUpdateStatus) {
        this.SaveOrUpdateStatus = SaveOrUpdateStatus;
    }

    public boolean isSearchpnl() {
        return searchpnl;
    }

    public void setSearchpnl(boolean searchpnl) {
        this.searchpnl = searchpnl;
    }

    public boolean isRegpnl() {
        return regpnl;
    }

    public void setRegpnl(boolean regpnl) {
        this.regpnl = regpnl;
    }

    public String getSaveorUpdateBundle() {
        return saveorUpdateBundle;
    }

    public void setSaveorUpdateBundle(String saveorUpdateBundle) {
        this.saveorUpdateBundle = saveorUpdateBundle;
    }

    public String getCreateOrSearchBundle() {
        return createOrSearchBundle;
    }

    public void setCreateOrSearchBundle(String createOrSearchBundle) {
        this.createOrSearchBundle = createOrSearchBundle;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public boolean isRenderPnlCreateAdditional() {
        return renderPnlCreateAdditional;
    }

    public void setRenderPnlCreateAdditional(boolean renderPnlCreateAdditional) {
        this.renderPnlCreateAdditional = renderPnlCreateAdditional;
    }

    public boolean isRenderPnlSerchPage() {
        return renderPnlSerchPage;
    }

    public void setRenderPnlSerchPage(boolean renderPnlSerchPage) {
        this.renderPnlSerchPage = renderPnlSerchPage;
    }

    public boolean isBtnNewRender() {
        return btnNewRender;
    }

    public void setBtnNewRender(boolean btnNewRender) {
        this.btnNewRender = btnNewRender;
    }

    public void createNewOrSearch() {
        if (createOrSearchBundle.equals("New")) {
            regpnl = true;
            searchpnl = false;
            createOrSearchBundle = "Search";
            setIcone("ui-icon-search");
            DisableDelete = true;
        } else if (createOrSearchBundle.equals("Search")) {
            regpnl = false;
            searchpnl = true;
            createOrSearchBundle = "New";
            setIcone("ui-icon-plus");
            DisableDelete = true;
        }
    }

    public void saveOrUpdate() {
        System.out.println("inside method");

        if (SaveOrUpdateStatus == 0) {
            if (user_sessionLocal.getStudentInfoDuplicate(register)) {
                JsfUtil.addFatalMessage("User " + register.getFirstName() + " with ID Number =" + register.getIdNumber() + "Already exists");

            } else {
                System.out.println("inside save");
                register.setFaculty(faculty);
                register.setDepartment(department);
                register.setYearOfStudy(yearOfstudy);
                user_sessionLocal.saveUser(register);
                JsfUtil.addSuccessMessage("Student saved Successfully");
                clear();
            }

        } else {
            register.setFaculty(faculty);
            register.setDepartment(department);
            register.setYearOfStudy(yearOfstudy);
            user_sessionLocal.editUser(register);
            JsfUtil.addSuccessMessage("Student updated Successfully");
            clear();
        }
    }

    public void saveAcademics() {
        System.out.println("=-=-=-=====inside academics save");
        depCoursYear = new DepCoursYear();
        depCoursYear.setDepFk(department);
        depCoursYear.setYosFk(yearOfstudy);
        depCoursYear.setCourseFk(course);
        dcy_sessionLocal.saveDcy(depCoursYear);
        JsfUtil.addSuccessMessage("save success");
        clear();

    }

    public void deleteAcademics() {
        try{
        dcy_sessionLocal.deleteDcy(depCoursYear);
        JsfUtil.addSuccessMessage("Delete success");
        } catch(Exception ex){
        JsfUtil.addFatalMessage("cannot Delete! Chiild Record found");
        }
    }
    String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void saveAcc() throws NoSuchAlgorithmException {
        if (useraccount.getPassword().equals(confirmPassword)) {
            useraccount.setUserProfile(register);
            if (userAccountbeanLocal.getAccountDuplicate(useraccount)) {
                JsfUtil.addFatalMessage("Unable to create Account,This Id Number is Invalid!!!");
            } else {
                try {
                    String encPassword;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(useraccount.getPassword().getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < bytes.length; i++) {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    encPassword = sb.toString();
                    useraccount.setPassword(encPassword);
                    System.out.println("password==" + useraccount.getPassword());
                    useraccount.setRole("stud");
                    useraccount.setUserProfile(register);
                    userAccountbeanLocal.SaveAccount(useraccount);
                    JsfUtil.addSuccessMessage("Account Create success");
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        } else {
            JsfUtil.addFatalMessage("The password and ConfirmPassword do not match!");
        }
    }

//    public void FacultyVCLDep(ValueChangeEvent event) {
//        if (event.getNewValue() != null) {
//           // faculty = (Faculty) event.getNewValue();
//            faculty.setFacId(Integer.valueOf(event.getNewValue().toString()));
//
//            System.out.println(" evrnt value" + faculty);
////            faculty = facultyFacade.find(faculty.getFacId());
//
//            department.setFacuId(faculty);
//            System.out.println(" value dep id" + department.getFacuId().getFacuName());
////            faculty = facultyFacade.find(faculty.getFacId());
////            depList = departmentFacade.findAllDepByFac(faculty.getFacId());
//        }
//
//    }
    public void FacultyVCL(ValueChangeEvent event) {
        if (event.getNewValue().toString() != null) {
            faculty.setFacId(Integer.valueOf(event.getNewValue().toString()));
            System.out.println(" evrnt value" + faculty);
            department.setFacuId(faculty);
            System.out.println(" value dep id" + department.getFacuId());
            faculty = facultyFacade.find(faculty.getFacId());
            depList = departmentFacade.findAllDepByFac(faculty.getFacId());
        }

    }
//        public void FacuVCL(ValueChangeEvent event) {
//        if (event.getNewValue() != null) {
//            faculty.setFacId(Integer.valueOf(event.getNewValue().toString()));
//            System.out.println(" evrnt value" + faculty);
//            department.setFacuId(faculty);
//            System.out.println(" value dep id" + department.getFacuId());
//            faculty = facultyFacade.find(faculty.getFacId());
//            depList = departmentFacade.findAllDepByFac(faculty.getFacId());
//       }

//   }
    public void DeptVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            department = new Department();
            System.out.println("event val===" + event.getNewValue());
            department.setDepId(Integer.valueOf(event.getNewValue().toString()));
            department = departmentFacade.find(department.getDepId());
            System.out.println("event val=########==" + department.getDepName());
        }

    }

    public void YearOfStudyVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            yearOfstudy.setYearId(Integer.valueOf(event.getNewValue().toString()));
            yearOfstudy = yearOfStudyFacade.find(yearOfstudy.getYearId());
        }

    }

    public void CourseVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            course.setCoId(Integer.valueOf(event.getNewValue().toString()));
            course = courseFacade.find(course.getCoId());
        }
    }

    //methods for course managemnt--------------------------------------------------------------------------
    public void deleteCourse() {
        try {
            course_sessionLocal.deleteCourse(course);
        } catch (Exception ex) {
            JsfUtil.addFatalMessage("cannot Delete! Chiild Record found");
        }
    }

    public void saveCourse() {
        course_sessionLocal.saveCourse(course);
    }

    //method for department management------------------------------------------------------------------------
    public void saveDepartment() {
        System.out.println("ffffffffffff" + faculty);
        department.setFacuId(faculty);
        dep_sessionLocal.saveDepartment(department);
        JsfUtil.addSuccessMessage("DEPARTMENT added succesfully");

    }

    public void deleteDepartment() {
        try {
            dep_sessionLocal.deleteDept(department);
            JsfUtil.addSuccessMessage("DEPARTMENT deleted succesfully");
        } catch (Exception ex) {
            JsfUtil.addFatalMessage("cannot Delete! Chiild Record found");
        }

    }

    //-----------------------METHODS FOR FACULTY MANAGEMENT-----------------------------------
    public void saveFaculty() {
        faculty_sessionLocal.saveFaculty(faculty);
        JsfUtil.addSuccessMessage("FACULTY added succesfully");
    }

    public void deleteFaculty() {
        try {
            faculty_sessionLocal.deleteFaculty(faculty);
            JsfUtil.addSuccessMessage("FACULTY deleted succesfully");
        } catch (Exception ex) {
            JsfUtil.addFatalMessage("cannot Delete! Chiild Record found");
        }
    }

//--------------------------------AMOUNT COUNTER METHODS-----------------------------------------------------------------------------------------
    public int userAmmount(int users) {
        System.out.println("user amount" + studentList.size());
        studentList = user_sessionLocal.viewUser(register);
        return users = studentList.size();

    }

    public int FacultyAmount(int faculties) {
        facuList = facultyFacade.findAll();
        return faculties = facuList.size();
    }

    public int departmentAmount(int departments) {
        depList = departmentFacade.findAll();
        return departments = depList.size();
    }

    public int courseAmmount(int courses) {
        courseList = courseFacade.findAll();
        return courses = courseList.size();

    }

    public int academicsAmmount(int academics) {
        academicsList = depCoursYearFacade.findAll();
        return academics = academicsList.size();

    }

    public StudentController() {
    }
}
