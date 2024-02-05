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
@Table(name = "FACULTY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f"),
    @NamedQuery(name = "Faculty.findByFacId", query = "SELECT f FROM Faculty f WHERE f.facId = :facId"),
    @NamedQuery(name = "Faculty.findByFacuName", query = "SELECT f FROM Faculty f WHERE f.facuName = :facuName")})
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACU_SEQ")
    @SequenceGenerator(name = "FACU_SEQ", sequenceName = "FACU_SEQ", allocationSize = 1)
    @Column(name = "FAC_ID")
    private Integer facId;
    @Size(max = 50)
    @Column(name = "FACU_NAME")
    private String facuName;
    @OneToMany(mappedBy = "facuId")
    private List<Department> departmentList;
    @OneToMany(mappedBy = "faculty")
    private List<StudentRegistration> studentRegistrationList;

    public Faculty() {
    }

    public Faculty(Integer facId) {
        this.facId = facId;
    }

    public Integer getFacId() {
        return facId;
    }

    public void setFacId(Integer facId) {
        this.facId = facId;
    }

    public String getFacuName() {
        return facuName;
    }

    public void setFacuName(String facuName) {
        this.facuName = facuName;
    }

    @XmlTransient
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
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
        hash += (facId != null ? facId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faculty)) {
            return false;
        }
        Faculty other = (Faculty) object;
        if ((this.facId == null && other.facId != null) || (this.facId != null && !this.facId.equals(other.facId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Faculty[ facId=" + facId + " ]";
    }
    
}
