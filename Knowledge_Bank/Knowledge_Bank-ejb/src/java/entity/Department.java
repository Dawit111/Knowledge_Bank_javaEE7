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
import javax.persistence.CascadeType;
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
@Table(name = "DEPARTMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByDepId", query = "SELECT d FROM Department d WHERE d.depId = :depId"),
    @NamedQuery(name = "Department.findByFacID", query = "SELECT d FROM Department d WHERE d.facuId.facId = :facId"),
    @NamedQuery(name = "Department.findByDepName", query = "SELECT d FROM Department d WHERE d.depName = :depName")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEP_SEQ")
    @SequenceGenerator(name = "DEP_SEQ", sequenceName = "DEP_SEQ", allocationSize = 1)
    @Column(name = "DEP_ID")
    private Integer depId;
    @Size(max = 50)
    @Column(name = "DEP_NAME")
    private String depName;
    @JoinColumn(name = "FACU_ID", referencedColumnName = "FAC_ID")
    @ManyToOne
    private Faculty facuId;
    @OneToMany(mappedBy = "depFk")
    private List<DepCoursYear> depCoursYearList;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<StudentRegistration> studentRegistrationList;

    public Department() {
    }

    public Department(Integer depId) {
        this.depId = depId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Faculty getFacuId() {
        return facuId;
    }

    public void setFacuId(Faculty facuId) {
        this.facuId = facuId;
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
        hash += (depId != null ? depId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.depId == null && other.depId != null) || (this.depId != null && !this.depId.equals(other.depId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Department[ depId=" + depId + " ]";
    }

}
