/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BABI
 */
@Entity
@Table(name = "ANSWER_USERACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnswerUseraccount.findAll", query = "SELECT a FROM AnswerUseraccount a"),
    @NamedQuery(name = "AnswerUseraccount.findAnsUserduplicate", query = "SELECT a FROM AnswerUseraccount a WHERE a.userAccFk = :userAccFk AND a.ansFk = :ansFk"),
    @NamedQuery(name = "AnswerUseraccount.findByUserIDAndAnswerID", query = "SELECT a FROM AnswerUseraccount a WHERE a.userAccFk = :userAccFk AND a.ansFk = :ansFk"),
    @NamedQuery(name = "AnswerUseraccount.findByLikedOrDisliked", query = "SELECT a FROM AnswerUseraccount a WHERE a.likedOrDisliked = :likedOrDisliked"),
    @NamedQuery(name = "AnswerUseraccount.findByAuId", query = "SELECT a FROM AnswerUseraccount a WHERE a.auId = :auId")})
public class AnswerUseraccount implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_SEQ")
    @SequenceGenerator(name = "AU_SEQ", sequenceName = "AU_SEQ", allocationSize = 1)
    @Column(name = "AU_ID")
    private Integer auId;
    @JoinColumn(name = "ANS_FK", referencedColumnName = "ANS_ID")
    @ManyToOne
    private Answer ansFk;
    @JoinColumn(name = "USER_ACC_FK", referencedColumnName = "ID")
    @ManyToOne
    private Useraccount userAccFk;
    @Column(name = "LIKED_OR_DISLIKED")
    private String likedOrDisliked;
    @Size(max = 20)

    public AnswerUseraccount() {
    }

    public AnswerUseraccount(Integer auId) {
        this.auId = auId;
    }

    public Integer getAuId() {
        return auId;
    }

    public void setAuId(Integer auId) {
        this.auId = auId;
    }

    public Answer getAnsFk() {
        return ansFk;
    }

    public void setAnsFk(Answer ansFk) {
        this.ansFk = ansFk;
    }

    public Useraccount getUserAccFk() {
        return userAccFk;
    }

    public void setUserAccFk(Useraccount userAccFk) {
        this.userAccFk = userAccFk;
    }
        public String getLikedOrDisliked() {
        return likedOrDisliked;
    }

    public void setLikedORDisliked(String likedOrDisliked) {
        this.likedOrDisliked = likedOrDisliked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auId != null ? auId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswerUseraccount)) {
            return false;
        }
        AnswerUseraccount other = (AnswerUseraccount) object;
        if ((this.auId == null && other.auId != null) || (this.auId != null && !this.auId.equals(other.auId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AnswerUseraccount[ auId=" + auId + " ]";
    }
    
}
