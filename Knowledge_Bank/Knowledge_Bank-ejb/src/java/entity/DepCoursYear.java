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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BABI
 */
@Entity
@Table(name = "DEP_COURS_YEAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepCoursYear.findAll", query = "SELECT d FROM DepCoursYear d"),
    @NamedQuery(name = "DepCoursYear.findByDcyId", query = "SELECT d FROM DepCoursYear d WHERE d.dcyId = :dcyId"),
    @NamedQuery(name = "DepCoursYear.findByDeptYr", query = "SELECT d FROM DepCoursYear d WHERE d.depFk = :depFk AND d.yosFk =:yosFk "),
    @NamedQuery(name = "DepCoursYear.findByDeptCorYr", query = "SELECT d FROM DepCoursYear d WHERE d.depFk = :depFk AND d.courseFk =:courseFk AND d.yosFk =:yosFk ")})
public class DepCoursYear implements Serializable {
    @OneToMany(mappedBy = "catagoryFk")
    private List<Question> questionList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEP_COUR_YEAR_SEQ")
    @SequenceGenerator(name = "DEP_COUR_YEAR_SEQ", sequenceName = "DEP_COUR_YEAR_SEQ", allocationSize = 1)
    @Column(name = "DCY_ID")
    private Integer dcyId;
    @OneToMany(mappedBy = "depCourse")
    private List<FileStore> fileStoreList;
    @JoinColumn(name = "COURSE_FK", referencedColumnName = "CO_ID")
    @ManyToOne
    private Course courseFk;
    @JoinColumn(name = "DEP_FK", referencedColumnName = "DEP_ID")
    @ManyToOne
    private Department depFk;
    @JoinColumn(name = "YOS_FK", referencedColumnName = "YEAR_ID")
    @ManyToOne
    private YearOfStudy yosFk;

    public DepCoursYear() {
    }

    public DepCoursYear(Integer dcyId) {
        this.dcyId = dcyId;
    }

    public Integer getDcyId() {
        return dcyId;
    }

    public void setDcyId(Integer dcyId) {
        this.dcyId = dcyId;
    }

    @XmlTransient
    public List<FileStore> getFileStoreList() {
        return fileStoreList;
    }

    public void setFileStoreList(List<FileStore> fileStoreList) {
        this.fileStoreList = fileStoreList;
    }

    public Course getCourseFk() {
        return courseFk;
    }

    public void setCourseFk(Course courseFk) {
        this.courseFk = courseFk;
    }

    public Department getDepFk() {
        return depFk;
    }

    public void setDepFk(Department depFk) {
        this.depFk = depFk;
    }

    public YearOfStudy getYosFk() {
        return yosFk;
    }

    public void setYosFk(YearOfStudy yosFk) {
        this.yosFk = yosFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dcyId != null ? dcyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepCoursYear)) {
            return false;
        }
        DepCoursYear other = (DepCoursYear) object;
        if ((this.dcyId == null && other.dcyId != null) || (this.dcyId != null && !this.dcyId.equals(other.dcyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (courseFk != null) {
            return courseFk.getCourseName();
        } else {
            return "";
        }
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

}
