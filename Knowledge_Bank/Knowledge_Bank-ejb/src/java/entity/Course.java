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
@Table(name = "COURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCoId", query = "SELECT c FROM Course c WHERE c.coId = :coId"),
    @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName")})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_SEQ")
    @SequenceGenerator(name = "COURSE_SEQ", sequenceName = "COURSE_SEQ", allocationSize = 1)
    @Column(name = "CO_ID")
    private Integer coId;
    @Size(max = 50)
    @Column(name = "COURSE_NAME")
    private String courseName;
    @OneToMany(mappedBy = "courseFk")
    private List<DepCoursYear> depCoursYearList;

    public Course() {
    }

    public Course(Integer coId) {
        this.coId = coId;
    }

    public Integer getCoId() {
        return coId;
    }

    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @XmlTransient
    public List<DepCoursYear> getDepCoursYearList() {
        return depCoursYearList;
    }

    public void setDepCoursYearList(List<DepCoursYear> depCoursYearList) {
        this.depCoursYearList = depCoursYearList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coId != null ? coId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.coId == null && other.coId != null) || (this.coId != null && !this.coId.equals(other.coId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Course[ coId=" + coId + " ]";
    }
    
}
