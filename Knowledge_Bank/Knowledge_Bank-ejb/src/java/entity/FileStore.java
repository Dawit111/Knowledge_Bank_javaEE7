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
import javax.persistence.Lob;
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
@Table(name = "FILE_STORE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileStore.findAll", query = "SELECT f FROM FileStore f"),
    @NamedQuery(name = "FileStore.findByFileId", query = "SELECT f FROM FileStore f WHERE f.fileId = :fileId"),
    @NamedQuery(name = "FileStore.findByName", query = "SELECT f FROM FileStore f WHERE f.name = :name"),
    @NamedQuery(name = "FileStore.findByDepartment", query = "SELECT f FROM FileStore f WHERE f.depCourse.depFk.depId = :depId"),
    @NamedQuery(name = "FileStore.findByYear", query = "SELECT f FROM FileStore f WHERE f.depCourse.yosFk.yearId = :yearId"),
    @NamedQuery(name = "FileStore.findByCourse", query = "SELECT f FROM FileStore f WHERE f.depCourse.courseFk.coId = :coId"),
    @NamedQuery(name = "FileStore.findByYearOfStudyAndDepartment", query = "SELECT f FROM FileStore f WHERE f.depCourse.yosFk.yearId = :yearId AND f.depCourse.depFk.depId= :depId"),
    @NamedQuery(name = "FileStore.findByYearOfStudyAndDepartmentAndCourse", query = "SELECT f FROM FileStore f WHERE f.depCourse.yosFk.yearId = :yearId AND f.depCourse.depFk.depId= :depId AND f.depCourse.courseFk.coId=:coId"),
    @NamedQuery(name = "FileStore.findByType", query = "SELECT f FROM FileStore f WHERE f.type = :type"),
    @NamedQuery(name = "FileStore.findByDateOfUpload", query = "SELECT f FROM FileStore f WHERE f.dateOfUpload = :dateOfUpload"),
    @NamedQuery(name = "FileStore.findByDescription", query = "SELECT f FROM FileStore f WHERE f.description = :description"),
    @NamedQuery(name = "FileStore.findByFileNameLike", query = "SELECT f FROM FileStore f WHERE f.name Like :name")})
public class FileStore implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILESTORE_SEQ")
    @SequenceGenerator(name = "FILESTORE_SEQ", sequenceName = "FILESTORE_SEQ", allocationSize = 1)
    @Column(name = "FILE_ID")
    private Integer fileId;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 100)
    @Column(name = "DATE_OF_UPLOAD")
    private String dateOfUpload;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Lob
    @Column(name = "FILES")
    private byte[] files;
    @JoinColumn(name = "DEP_COURSE", referencedColumnName = "DCY_ID")
    @ManyToOne
    private DepCoursYear depCourse;

    @JoinColumn(name = "USER_FK", referencedColumnName = "ID")
    @ManyToOne
    private Useraccount userFk;

    public FileStore() {
    }

    public FileStore(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(String dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }

    public DepCoursYear getDepCourse() {
        return depCourse;
    }

    public void setDepCourse(DepCoursYear depCourse) {
        this.depCourse = depCourse;
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
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileStore)) {
            return false;
        }
        FileStore other = (FileStore) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FileStore[ fileId=" + fileId + " ]";
    }

}
