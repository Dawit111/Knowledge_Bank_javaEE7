/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileControll;

import businessLogic.Answer_sessionLocal;
import businessLogic.Question_sessionLocal;
import businessLogic.Dcy_sessionLocal;
import businessLogic.UserAccountbeanLocal;
import businessLogic.UserAnswer_sessionLocal;
import common.JsfUtil;
import common.SessionController;
import entity.Answer;
import entity.Course;
import entity.Question;
import entity.DepCoursYear;
import entity.Department;
import entity.Faculty;
import entity.Useraccount;
import entity.YearOfStudy;
import entity.AnswerUseraccount;
import java.io.IOException;
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
import javax.inject.Inject;
import javax.inject.Named;
import mapper.AnswerFacade;
import mapper.CourseFacade;
import mapper.DepCoursYearFacade;
import mapper.DepartmentFacade;
import mapper.FacultyFacade;
import mapper.QuestionFacade;
import mapper.YearOfStudyFacade;
import mapper.AnswerUseraccountFacade;

@Named(value = "qA_controller")
public class QA_controller implements Serializable {

    @Inject
    Question question;
    @Inject
    Answer answer;
    @Inject
    DepCoursYear depCoursYear;
    @Inject
    Faculty faculty;
    @Inject
    Department department;
    @Inject
    Course course;
    @Inject
    YearOfStudy yearOfStudy;
    @Inject
    AnswerUseraccount answerUseraccount;
    @EJB
    Question_sessionLocal question_sessionaLocal;
    @EJB
    Answer_sessionLocal answer_sessionLocal;
    @EJB
    Dcy_sessionLocal dcy_sessionLocal;
    @EJB
    UserAccountbeanLocal userAccountbeanLocal;
    @EJB
    QuestionFacade questionFacade;
    @EJB
    AnswerFacade answerFacade;
    @EJB
    FacultyFacade facultyFacade;
    @EJB
    DepartmentFacade departmentFacade;
    @EJB
    DepCoursYearFacade depCoursYearFacade;
    @EJB
    CourseFacade courseFacade;
    @EJB
    YearOfStudyFacade yearOfStudyFacade;
    @EJB
    UserAnswer_sessionLocal userAnswer_sessionLocal;
    @EJB
    AnswerUseraccountFacade answerUseraccountFacade;
    @Inject
    Useraccount loggedinUser;
    SessionController session = new SessionController();

    List<Department> departmentList = new ArrayList<>();
    List<Course> courseList = new ArrayList<>();
    List<YearOfStudy> yearList = new ArrayList<>();
    List<Faculty> facuList = new ArrayList<>();
    List<Question> questionList = new ArrayList<>();
    DataModel<Question> questionDataModel;
    List<Answer> answerList = new ArrayList<>();
    DataModel<Answer> answerDataModel;

    List<DepCoursYear> depyrList = new ArrayList<>();
    List<AnswerUseraccount> likeDuplicateList = new ArrayList<>();

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

    public boolean questionPnl = false;
    public boolean QAPnl = true;
    public boolean disableRefreshBtn = false;
    public boolean disableGoToFilesPageBtn = false;
    public String askOrQAbundle = "Ask Question";
    public String askOrQAtitle = "ask";



    public boolean isDisableGoToFilesPageBtn() {
        return disableGoToFilesPageBtn;
    }

    public void setDisableGoToFilesPageBtn(boolean disableGoToFilesPageBtn) {
        this.disableGoToFilesPageBtn = disableGoToFilesPageBtn;
    }

    public boolean isDisableRefreshBtn() {
        return disableRefreshBtn;
    }

    public void setDisableRefreshBtn(boolean disableRefreshBtn) {
        this.disableRefreshBtn = disableRefreshBtn;
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

    public boolean isQuestionPnl() {
        return questionPnl;
    }

    public void setQuestionPnl(boolean questionPnl) {
        this.questionPnl = questionPnl;
    }

    public boolean isQAPnl() {
        return QAPnl;
    }

    public void setQAPnl(boolean QAPnl) {
        this.QAPnl = QAPnl;
    }

    public String getAskOrQAbundle() {
        return askOrQAbundle;
    }

    public void setAskOrQAbundle(String askOrQAbundle) {
        this.askOrQAbundle = askOrQAbundle;
    }

    public String getAskOrQAtitle() {
        return askOrQAtitle;
    }

    public void setAskOrQAtitle(String askOrQAtitle) {
        this.askOrQAtitle = askOrQAtitle;
    }

    public AnswerUseraccount getAnswerUseraccount() {
        if (answerUseraccount == null) {
            answerUseraccount = new AnswerUseraccount();
        }
        return answerUseraccount;
    }

    public void setAnswerUseraccount(AnswerUseraccount answerUseraccount) {
        this.answerUseraccount = answerUseraccount;
    }

    public Question getQuestion() {
        if (question == null) {
            question = new Question();
        }
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        //recreatAnswerDatamodel();
        if (answer == null) {
            answer = new Answer();
        }
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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

    public List<AnswerUseraccount> getLikeDuplicateList() {
        if (likeDuplicateList == null) {
            likeDuplicateList = new ArrayList<>();
        }
        return likeDuplicateList;
    }

    public void setLikeDuplicateList(List<AnswerUseraccount> likeDuplicateList) {
        this.likeDuplicateList = likeDuplicateList;
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

    public List<Question> getQuestionList() {
        searchQuestions();
        if (questionList == null) {
            questionList = new ArrayList<>();
        }
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public DataModel<Question> getQuestionDataModel() {
        searchQuestions();
        recreatQuestionDatamodel();
        return questionDataModel;
    }

    public void setQuestionDataModel(DataModel<Question> questionDataModel) {
        this.questionDataModel = questionDataModel;
    }

    public void recreatQuestionDatamodel() {
        questionDataModel = null;
        questionDataModel = new ListDataModel(new ArrayList(questionList));
    }

    public List<Answer> getAnswerList() {
        viewAnswers();
        if (answerList == null) {
            answerList = new ArrayList<>();
        }
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public DataModel<Answer> getAnswerDataModel() {
        //viewAnswers();
        //recreatAnswerDatamodel();
        recreatAnswerDatamodel();

        return answerDataModel;
    }

    public void setAnswerDataModel(DataModel<Answer> answerDataModel) {
        this.answerDataModel = answerDataModel;
    }

    public void recreatAnswerDatamodel() {
        answerDataModel = null;
        answerDataModel = new ListDataModel(new ArrayList(answerList));
    }

    //-------------------------------------END OF GETTER AND SETTER--------------------------------------------------------------------
    public void searchQuestions() {
        System.out.println("i am in search file findAll------------------");

        if (department.getDepId() != null && yearOfStudy.getYearId() == null) {
            questionList = questionFacade.findBydepartment(department.getDepId());
            System.out.println("filesBy Dep==" + questionList);
        } else if (yearOfStudy.getYearId() != null && course == null && department == null) {
            questionList = questionFacade.findByYear(yearOfStudy.getYearId());
        } else if (yearOfStudy.getYearId() != null && course == null && department != null) {
            questionList = questionFacade.findByYearAndDepartment(yearOfStudy.getYearId(), department.getDepId());
        } else if (yearOfStudy.getYearId() != null && course != null && department != null) {
            questionList = questionFacade.findByYearAndDepartmentAndCourse(yearOfStudy.getYearId(), department.getDepId(), course.getCoId());
        } else if (searchByFileName == true) {
            questionList = question_sessionaLocal.findByQuestion(question.getQuestion());
        } else {
            System.out.println("nnnnnnnnnnn");
            questionList = questionFacade.findAll();
        }

    }
    public boolean searchByFileName;

    public boolean isSearchByFileName() {
        searchByFileName = false;
        return searchByFileName;
    }

    public void setSearchByFileName(boolean searchByFileName) {
        this.searchByFileName = searchByFileName;
    }

    public void searchByQuestion() {
        //recreatQuestionDatamodel();
        System.out.println("inside if search method");
        searchByFileName = true;
        searchQuestions();
        System.out.println("file size=========" + questionList.size());

    }

    public void saveQuestion() {
        JsfUtil.addSuccessMessage("Posted Successfully");
        //question.setCatagoryFk(depCoursYear);
        question.setUserFk(loggedinUser);
        question_sessionaLocal.saveQuestion(question);
        clearQA();

    }

    public void questionAskPage() {
        System.out.println("------------------------------------------------------ask page");
        if (askOrQAtitle.equals("ask")) {
            questionPnl = true;
            QAPnl = false;
            askOrQAtitle = "back";
            askOrQAbundle = "Back to Discussion";
            disableRefreshBtn = true;
            disableGoToFilesPageBtn = true;
        } else if (askOrQAtitle.equals("back")) {
            questionPnl = false;
            QAPnl = true;
            askOrQAtitle = "ask";
            askOrQAbundle = "Ask Question";
            disableRefreshBtn = false;
            disableGoToFilesPageBtn = false;

        }

    }

    public void userHomePage() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("userHomePage.xhtml");

    }

    public void refreshDiscussionPage() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("QApage.xhtml");

    }

    public void FacultyVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            faculty.setFacId(Integer.valueOf(event.getNewValue().toString()));
            faculty = facultyFacade.find(faculty.getFacId());
            departmentList = departmentFacade.findAllDepByFac(faculty.getFacId());
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
            searchQuestions();
            // questionList = questionFacade.findBydepartment(department.getDepId());

        }

    }

    public void YearOfStudyVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            yearOfStudy.setYearId(Integer.valueOf(event.getNewValue().toString()));
            yearOfStudy = yearOfStudyFacade.find(yearOfStudy.getYearId());
            System.out.println("Size before===" + depyrList.size());
            depCoursYear.setYosFk(yearOfStudy);
            searchQuestions();
            depyrList = depCoursYearFacade.findByDeptYr(depCoursYear);
            System.out.println("Size===" + depyrList.size());
            //questionList = questionFacade.findByYear(yearOfStudy.getYearId());
            System.out.println("year name-----------" + questionList);

        }

    }

    public void CourseVCL(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val CourseVCL===" + event.getNewValue());
            course = new Course();
            course.setCoId(Integer.valueOf(event.getNewValue().toString()));
            course = courseFacade.find(course.getCoId());
            depCoursYear.setCourseFk(course);
            searchQuestions();
            //questionList = questionFacade.findByCourse(course.getCoId());
        }
    }
    //------------------------------------------Question vcl------------------------------------------------------------------

    public void FacultyVCL1(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            faculty.setFacId(Integer.valueOf(event.getNewValue().toString()));
            faculty = facultyFacade.find(faculty.getFacId());
            departmentList = departmentFacade.findAllDepByFac(faculty.getFacId());
        }

    }

    public void DeptVCL1(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            department.setDepId(Integer.valueOf(event.getNewValue().toString()));
            department = departmentFacade.find(department.getDepId());
            System.out.println("event dept name=########==" + department.getDepName());
            System.out.println("event  dept Id=########==" + department);
            depCoursYear = new DepCoursYear();
            depCoursYear.setDepFk(department);

        }

    }

    public void YearOfStudyVCL1(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val===" + event.getNewValue());
            yearOfStudy.setYearId(Integer.valueOf(event.getNewValue().toString()));
            yearOfStudy = yearOfStudyFacade.find(yearOfStudy.getYearId());
            System.out.println("Size before===" + depyrList.size());
            depCoursYear.setYosFk(yearOfStudy);

            depyrList = depCoursYearFacade.findByDeptYr(depCoursYear);

        }

    }

    public void CourseVCL1(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            System.out.println("event val CourseVCL===" + event.getNewValue());
            course = new Course();
            course.setCoId(Integer.valueOf(event.getNewValue().toString()));
            course = courseFacade.find(course.getCoId());

            depCoursYear.setYosFk(yearOfStudy);
            depCoursYear.setDepFk(department);
            depCoursYear.setCourseFk(course);
            depCoursYear = depCoursYearFacade.findByDeptCorYr(depCoursYear);

            question.setCatagoryFk(depCoursYear);

            //questionList = questionFacade.findByCourse(course.getCoId());
        }
    }

    //-------------------------------------------Answer method------------------------------------------------------
    public void viewAnswers() {
        System.out.println("-------------in view answer");
        // recreatQuestionDatamodel();
        // question = new Question();
        clearQA();
        question = questionDataModel.getRowData();
        answerList = answer_sessionLocal.findAllAnswerOfQuestion(question);

        // recreatQuestionDatamodel();
//        question = new Question();
//        answer = new Answer();
    }

    public int answerAmount(int amount) {
        // recreatQuestionDatamodel();
        question = questionDataModel.getRowData();
        answerList = answer_sessionLocal.findAllAnswerOfQuestion(question);
        return amount = answerList.size();
    }

    public void postAnswer() {
        //recreatQuestionDatamodel();
        //question = new Question();
        recreatAnswerDatamodel();
        question = questionDataModel.getRowData();
        // System.out.println("i am in qustion "+question.getQuestion());
        answer.setLikes(0);
        answer.setDislikes(0);
        answer.setQIdFk(question);
        answer.setUserFk(loggedinUser);

//        System.out.println("000000000000" + answer.getQIdFk());
//        System.out.println("1111111111111111111" + answer.getAnswerContent());
        answer_sessionLocal.postAnswer(answer);
        JsfUtil.addSuccessMessage("Answer submmited succesfully");
        clearQA();
//        recreatQuestionDatamodel();
//        recreatAnswerDatamodel();
    }

    public void clearQA() {
        answer = new Answer();
        answerList = new ArrayList<>();
        question = new Question();
        questionList = new ArrayList<>();
        faculty = new Faculty();
        department = new Department();
        course = new Course();
        yearOfStudy = new YearOfStudy();

    }

    int likeCounter = 0;

    public int getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(int likeCounter) {
        this.likeCounter = likeCounter;
    }
    public boolean IsLiked;

    public boolean isIsLiked() {
        return IsLiked;
    }

    public void setIsLiked(boolean IsLiked) {
        this.IsLiked = IsLiked;
    }

    public AnswerUseraccountFacade getAnswerUseraccountFacade() {
        return answerUseraccountFacade;
    }

    public void setAnswerUseraccountFacade(AnswerUseraccountFacade answerUseraccountFacade) {
        this.answerUseraccountFacade = answerUseraccountFacade;
    }

    public void doLike() {

        answer = answerDataModel.getRowData();

        answerUseraccount = new AnswerUseraccount();
        answerUseraccount.setAnsFk(answer);
        answerUseraccount.setUserAccFk(loggedinUser);

        if (userAnswer_sessionLocal.getLikeDuplicate(answerUseraccount)) {//comment boolean like duplicate = true
            answerUseraccount = userAnswer_sessionLocal.findByLikeDuplicate(answerUseraccount);//the former answerUseraccount is just object but the latter is setted user Id and answer ID to search by them and hold id of that row.
            System.out.println("the condition is" + answerUseraccount.getLikedOrDisliked());
            if (answerUseraccount.getLikedOrDisliked().equals("liked")) {

                answerUseraccount = userAnswer_sessionLocal.findByLikeDuplicate(answerUseraccount);
                userAnswer_sessionLocal.saveLikedTwice(answerUseraccount);
                likeCounter = answer.getLikes();
                likeCounter--;
                answer.setLikes(likeCounter);
                answerFacade.edit(answer);
                JsfUtil.addSuccessMessage("You Removed Like Suucessfully");
            } else if (answerUseraccount.getLikedOrDisliked().equals("disLiked")) {
                JsfUtil.addFatalMessage("You Must remove Dislike");
            }
        } else {
            answerUseraccount.setLikedORDisliked("liked");
            userAnswer_sessionLocal.saveLikedOnce(answerUseraccount);
            likeCounter = answer.getLikes();
            likeCounter++;
            answer.setLikes(likeCounter);
            answerFacade.edit(answer);
            JsfUtil.addSuccessMessage("You Liked successfully");
//        answerUseraccount.setAnsFk(answer);
//        answerUseraccount.setUserAccFk(loggedinUser);

        }

    }
    int disLikeCounter = 0;

    public int getDisLikeCounter() {
        return disLikeCounter;
    }

    public void setDisLikeCounter(int disLikeCounter) {
        this.disLikeCounter = disLikeCounter;
    }

    public void doDisLike() {

        answer = answerDataModel.getRowData();

        answerUseraccount = new AnswerUseraccount();
        answerUseraccount.setAnsFk(answer);
        answerUseraccount.setUserAccFk(loggedinUser);

        if (userAnswer_sessionLocal.getLikeDuplicate(answerUseraccount)) {
            answerUseraccount = userAnswer_sessionLocal.findByLikeDuplicate(answerUseraccount);
            System.out.println("the condition is" + answerUseraccount.getLikedOrDisliked());
            if (answerUseraccount.getLikedOrDisliked().equals("disLiked")) {

                answerUseraccount = userAnswer_sessionLocal.findByLikeDuplicate(answerUseraccount);
                userAnswer_sessionLocal.saveLikedTwice(answerUseraccount);
                disLikeCounter = answer.getDislikes();
                disLikeCounter--;
                answer.setDislikes(disLikeCounter);
                answerFacade.edit(answer);
                JsfUtil.addSuccessMessage("You removed Dislike");
            } else if (answerUseraccount.getLikedOrDisliked().equals("liked")) {
                JsfUtil.addFatalMessage("You Must remove Like");
            }
        } else {
            answerUseraccount.setLikedORDisliked("disLiked");
            userAnswer_sessionLocal.saveLikedOnce(answerUseraccount);
            disLikeCounter = answer.getDislikes();
            System.out.println("--------3-----" + disLikeCounter);
            disLikeCounter++;
            System.out.println("-----4----------------" + disLikeCounter);
            answer.setDislikes(disLikeCounter);
            answerFacade.edit(answer);
            JsfUtil.addSuccessMessage("You Disliked successfully");

        }

    }

    public AnswerFacade getAnswerFacade() {
        return answerFacade;
    }

    public void setAnswerFacade(AnswerFacade answerFacade) {
        this.answerFacade = answerFacade;
    }

    public int likeAmount(int likeAmount) {
        // return likeAmount = likeCounter;
        answer = answerDataModel.getRowData();
        System.out.println("==========nurediniiiiiiiiiiiiiiii====" + answer.getLikes().intValue());
        return likeAmount = answer.getLikes();

    }

    public int disLikeAmount(int dislikeAmount) {
        answer = answerDataModel.getRowData();
        // System.out.println("==========nurediniiiiiiiiiiiiiiii====" + answer.getLikes().intValue());
        return dislikeAmount = answer.getDislikes();
    }

//private String likeIcone = "pi-thumbs-up";
// public String getLikeIcon(){
//   return likeIcone;
// }
// public void setLikeIcon(String likeIcone){
// 
// this.likeIcone = likeIcone;
//         }
    public QA_controller() {
    }
//-------------------------------AMOUT COUNTER METHODS-------------------------

    public int questionAmmount(int question) {
        questionList = questionFacade.findAll();
        return question = questionList.size();

    }
}
