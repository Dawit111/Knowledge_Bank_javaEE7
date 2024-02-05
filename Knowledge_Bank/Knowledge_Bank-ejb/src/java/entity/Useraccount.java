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
@Table(name = "USERACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useraccount.findAll", query = "SELECT u FROM Useraccount u"),
    @NamedQuery(name = "Useraccount.findById", query = "SELECT u FROM Useraccount u WHERE u.id = :id"),
    @NamedQuery(name = "Useraccount.findByUsername", query = "SELECT u FROM Useraccount u WHERE u.username = :username"),
    @NamedQuery(name = "Useraccount.findByUsernameAndPassword", query = "SELECT u FROM Useraccount u WHERE u.username = :username AND u.password=:password"),
    @NamedQuery(name = "Useraccount.findByUserProfile", query = "SELECT u FROM Useraccount u WHERE u.userProfile = :userProfile"),
    @NamedQuery(name = "Useraccount.findByUserNameAndPassword", query = "SELECT u FROM Useraccount u WHERE u.username = :username AND u.password=:password"),
    @NamedQuery(name = "Useraccount.findByRole", query = "SELECT u FROM Useraccount u WHERE u.role = :role"),
    @NamedQuery(name = "Useraccount.findByPassword", query = "SELECT u FROM Useraccount u WHERE u.password = :password")})
public class Useraccount implements Serializable {
    @OneToMany(mappedBy = "userAccFk")
    private List<AnswerUseraccount> answerUseraccountList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ACC_SEQ")
    @SequenceGenerator(name = "USER_ACC_SEQ", sequenceName = "USER_ACC_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 50)
    @Column(name = "ROLE")
    private String role;
    @Size(max = 300)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "USER_PROFILE", referencedColumnName = "STUD_ID")
    @ManyToOne
    private StudentRegistration userProfile;

    public Useraccount() {
    }

    public Useraccount(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentRegistration getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(StudentRegistration userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useraccount)) {
            return false;
        }
        Useraccount other = (Useraccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Useraccount[ id=" + id + " ]";
    }

    @XmlTransient
    public List<AnswerUseraccount> getAnswerUseraccountList() {
        return answerUseraccountList;
    }

    public void setAnswerUseraccountList(List<AnswerUseraccount> answerUseraccountList) {
        this.answerUseraccountList = answerUseraccountList;
    }
    
}
