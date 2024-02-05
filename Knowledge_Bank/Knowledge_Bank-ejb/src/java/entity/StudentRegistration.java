/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "STUDENT_REGISTRATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentRegistration.findAll", query = "SELECT s FROM StudentRegistration s"),
    @NamedQuery(name = "StudentRegistration.findByStudId", query = "SELECT s FROM StudentRegistration s WHERE s.studId = :studId"),
    @NamedQuery(name = "StudentRegistration.findByIdNumber", query = "SELECT s FROM StudentRegistration s WHERE s.idNumber = :idNumber"),
    @NamedQuery(name = "StudentRegistration.findByFirstName", query = "SELECT s FROM StudentRegistration s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "StudentRegistration.findByfnameLike", query = "SELECT s FROM StudentRegistration s WHERE s.firstName Like :fname"),
    @NamedQuery(name = "StudentRegistration.findByLastName", query = "SELECT s FROM StudentRegistration s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "StudentRegistration.findByGender", query = "SELECT s FROM StudentRegistration s WHERE s.gender = :gender"),
    @NamedQuery(name = "StudentRegistration.findByStatus", query = "SELECT s FROM StudentRegistration s WHERE s.status = :status"),
    @NamedQuery(name = "StudentRegistration.findByIdNumberAndFirstName", query = "SELECT s FROM StudentRegistration s WHERE s.idNumber = :idNumber AND s.firstName=:firstName OR s.idNumber = :idNumber"),
    @NamedQuery(name = "StudentRegistration.findByIdNumberLike", query = "SELECT s FROM StudentRegistration s WHERE UPPER(s.idNumber) LIKE :idNumber")})
public class StudentRegistration implements Serializable {
    @Column(name = "STATUS")
    private Integer status;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUD_REG_SEQ")
    @SequenceGenerator(name = "STUD_REG_SEQ", sequenceName = "STUD_REG_SEQ", allocationSize = 1)
    @Column(name = "STUD_ID")
    private Integer studId;
    @Size(max = 20)
    @Column(name = "ID_NUMBER")
    private String idNumber;
    @Size(max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 20)
    @Column(name = "GENDER")
    private String gender;
    
    @OneToMany(mappedBy = "userProfile")
    private List<Useraccount> useraccountList;
    @JoinColumn(name = "DEPARTMENT", referencedColumnName = "DEP_ID")
    @ManyToOne
    private Department department;
    
    @JoinColumn(name = "FACULTY", referencedColumnName = "FAC_ID")
    @ManyToOne
    private Faculty faculty;
    
    @JoinColumn(name = "YEAR_OF_STUDY", referencedColumnName = "YEAR_ID")
    @ManyToOne
    private YearOfStudy yearOfStudy;

    public StudentRegistration() {
    }

    public StudentRegistration(Integer studId) {
        this.studId = studId;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @XmlTransient
    public List<Useraccount> getUseraccountList() {
        return useraccountList;
    }

    public void setUseraccountList(List<Useraccount> useraccountList) {
        this.useraccountList = useraccountList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public YearOfStudy getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studId != null ? studId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentRegistration)) {
            return false;
        }
        StudentRegistration other = (StudentRegistration) object;
        if ((this.studId == null && other.studId != null) || (this.studId != null && !this.studId.equals(other.studId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StudentRegistration[ studId=" + studId + " ]";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
