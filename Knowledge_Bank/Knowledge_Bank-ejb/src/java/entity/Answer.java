/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.lang.Integer;

import java.util.List;
//import java.math.Integer;
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
@Table(name = "ANSWER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAnsId", query = "SELECT a FROM Answer a WHERE a.ansId = :ansId"),
    @NamedQuery(name = "Answer.findByAnswerContent", query = "SELECT a FROM Answer a WHERE a.answerContent = :answerContent"),
    @NamedQuery(name = "Answer.findByLikes", query = "SELECT a FROM Answer a WHERE a.likes = :likes"),
    @NamedQuery(name = "Answer.findByAllAnsOfQuestion", query = "SELECT a FROM Answer a WHERE a.qIdFk.qId = :qId"),
    @NamedQuery(name = "Answer.findByDislikes", query = "SELECT a FROM Answer a WHERE a.dislikes = :dislikes")})
public class Answer implements Serializable {

    @Column(name = "LIKES")
    private Integer likes;
    @Column(name = "DISLIKES")
    private Integer dislikes;
    @OneToMany(mappedBy = "ansFk")
    private List<AnswerUseraccount> answerUseraccountList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANS_SEQ")
    @SequenceGenerator(name = "ANS_SEQ", sequenceName = "ANS_SEQ", allocationSize = 1)
    @Column(name = "ANS_ID")
    private Integer ansId;
    @Size(max = 2000)
    @Column(name = "ANSWER_CONTENT")
    private String answerContent;
    @JoinColumn(name = "Q_ID_FK", referencedColumnName = "Q_ID")
    @ManyToOne
    private Question qIdFk;
    @JoinColumn(name = "USER_FK", referencedColumnName = "ID")
    @ManyToOne
    private Useraccount userFk;

    public Answer() {
    }

    public Answer(Integer ansId) {
        this.ansId = ansId;
    }

    public Integer getAnsId() {
        return ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Question getQIdFk() {
        return qIdFk;
    }

    public void setQIdFk(Question qIdFk) {
        this.qIdFk = qIdFk;
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
        hash += (ansId != null ? ansId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.ansId == null && other.ansId != null) || (this.ansId != null && !this.ansId.equals(other.ansId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Answer[ ansId=" + ansId + " ]";
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    @XmlTransient
    public List<AnswerUseraccount> getAnswerUseraccountList() {
        return answerUseraccountList;
    }

    public void setAnswerUseraccountList(List<AnswerUseraccount> answerUseraccountList) {
        this.answerUseraccountList = answerUseraccountList;
    }

}
