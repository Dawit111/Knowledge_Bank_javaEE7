/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BABI
 */
@Entity
@Table(name = "QUESTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQId", query = "SELECT q FROM Question q WHERE q.qId = :qId"),
    @NamedQuery(name = "Question.findByQuestion", query = "SELECT q FROM Question q WHERE q.question = :question"),
    @NamedQuery(name = "Question.findByQuestionLike", query = "SELECT q FROM Question q WHERE q.question Like :question"),
    @NamedQuery(name = "Question.findByDateAsked", query = "SELECT q FROM Question q WHERE q.dateAsked = :dateAsked"),
    @NamedQuery(name = "Question.findByDepartment", query = "SELECT q FROM Question q WHERE q.catagoryFk.depFk.depId = :depId"),
    @NamedQuery(name = "Question.findByYear", query = "SELECT q FROM Question q WHERE q.catagoryFk.yosFk.yearId = :yearId"),
    @NamedQuery(name = "Question.findByCourse", query = "SELECT q FROM Question q WHERE q.catagoryFk.courseFk.coId = :coId"),
    @NamedQuery(name = "Question.findByYearOfStudyAndDepartment", query = "SELECT q FROM Question q WHERE q.catagoryFk.yosFk.yearId = :yearId AND q.catagoryFk.depFk.depId= :depId"),
    @NamedQuery(name = "Question.findByYearOfStudyAndDepartmentAndCourse", query = "SELECT q FROM Question q WHERE q.catagoryFk.yosFk.yearId = :yearId AND q.catagoryFk.depFk.depId= :depId AND q.catagoryFk.courseFk.coId=:coId"),})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUES_SEQ")
    @SequenceGenerator(name = "QUES_SEQ", sequenceName = "QUES_SEQ", allocationSize = 1)
    @Column(name = "Q_ID")
    private Integer qId;
    @Size(max = 200)
    @Column(name = "QUESTION")
    private String question;
    @Size(max = 20)
    @Column(name = "DATE_ASKED")
    private String dateAsked;
    @OneToMany(mappedBy = "qIdFk")
    private List<Answer> answerList;
    @JoinColumn(name = "CATAGORY_FK", referencedColumnName = "DCY_ID")
    @ManyToOne
    private DepCoursYear catagoryFk;

    @JoinColumn(name = "USER_FK", referencedColumnName = "ID")
    @ManyToOne
    private Useraccount userFk;

    public Question() {
    }

    public Question(Integer qId) {
        this.qId = qId;
    }

    public Integer getQId() {
        return qId;
    }

    public void setQId(Integer qId) {
        this.qId = qId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDateAsked() {
        return dateAsked;
    }

    public void setDateAsked(String dateAsked) {
        this.dateAsked = dateAsked;
    }

    @XmlTransient
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public DepCoursYear getCatagoryFk() {
        return catagoryFk;
    }

    public void setCatagoryFk(DepCoursYear catagoryFk) {
        this.catagoryFk = catagoryFk;
    }

    public Useraccount getUserFk() {
        return userFk;
    }

    public void setUserFk(Useraccount userFk) {
        this.userFk = userFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qId != null ? qId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.qId == null && other.qId != null) || (this.qId != null && !this.qId.equals(other.qId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Question[ qId=" + qId + " ]";
    }

}
