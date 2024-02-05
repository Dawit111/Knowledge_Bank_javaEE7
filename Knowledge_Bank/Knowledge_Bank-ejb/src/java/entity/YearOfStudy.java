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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "YEAR_OF_STUDY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "YearOfStudy.findAll", query = "SELECT y FROM YearOfStudy y"),
    @NamedQuery(name = "YearOfStudy.findByYearId", query = "SELECT y FROM YearOfStudy y WHERE y.yearId = :yearId"),
    @NamedQuery(name = "YearOfStudy.findByName", query = "SELECT y FROM YearOfStudy y WHERE y.name = :name")})
public class YearOfStudy implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "YEAR_ID")
    private Integer yearId;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "yosFk")
    private List<DepCoursYear> depCoursYearList;
    @OneToMany(mappedBy = "yearOfStudy")
    private List<StudentRegistration> studentRegistrationList;

    public YearOfStudy() {
    }

    public YearOfStudy(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<DepCoursYear> getDepCoursYearList() {
        return depCoursYearList;
    }

    public void setDepCoursYearList(List<DepCoursYear> depCoursYearList) {
        this.depCoursYearList = depCoursYearList;
    }

    @XmlTransient
    public List<StudentRegistration> getStudentRegistrationList() {
        return studentRegistrationList;
    }

    public void setStudentRegistrationList(List<StudentRegistration> studentRegistrationList) {
        this.studentRegistrationList = studentRegistrationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yearId != null ? yearId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YearOfStudy)) {
            return false;
        }
        YearOfStudy other = (YearOfStudy) object;
        if ((this.yearId == null && other.yearId != null) || (this.yearId != null && !this.yearId.equals(other.yearId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.YearOfStudy[ yearId=" + yearId + " ]";
    }
    
}
