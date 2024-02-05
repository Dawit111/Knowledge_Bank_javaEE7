/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.FileStore;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BABI
 */
@Stateless
public class FileStoreFacade extends AbstractFacade<FileStore> {

    @PersistenceContext(unitName = "Knowledge_Bank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FileStoreFacade() {
        super(FileStore.class);
    }

    public FileStore findByFileId(Integer fileId) {
        Query query = em.createNamedQuery("FileStore.findByFileId", FileStore.class);
        query.setParameter("fileId", fileId);
        return (FileStore) query.getResultList().get(0);
    }

    public List<FileStore> findBydepartment(Integer depId) {
        Query query = em.createNamedQuery("FileStore.findByDepartment", FileStore.class);
        query.setParameter("depId", depId);
        return query.getResultList();
    }

    public List<FileStore> findByYear(Integer yearId) {
        Query query = em.createNamedQuery("FileStore.findByYear", FileStore.class);
        query.setParameter("yearId", yearId);
        return query.getResultList();
    }

    public List<FileStore> findByCourse(Integer coId) {
        Query query = em.createNamedQuery("FileStore.findByCourse", FileStore.class);
        query.setParameter("coId", coId);
        return query.getResultList();
    }

    public List<FileStore> findByYearAndDepartment(Integer yearId, Integer depId) {
        Query query = em.createNamedQuery("FileStore.findByYearOfStudyAndDepartment", FileStore.class);
        query.setParameter("yearId", yearId);
        query.setParameter("depId", depId);
        return query.getResultList();
    }

    public List<FileStore> findByYearAndDepartmentAndCourse(Integer yearId, Integer depId, Integer coId) {
        Query query = em.createNamedQuery("FileStore.findByYearOfStudyAndDepartmentAndCourse", FileStore.class);
        query.setParameter("yearId", yearId);
        query.setParameter("depId", depId);
        query.setParameter("coId", coId);
        return query.getResultList();
    }

    public List<FileStore> findByFileName(String name) {
        Query query = em.createNamedQuery("FileStore.findByFileNameLike", FileStore.class);
        query.setParameter("name",'%' + name + '%');
        return query.getResultList();
    }

}
