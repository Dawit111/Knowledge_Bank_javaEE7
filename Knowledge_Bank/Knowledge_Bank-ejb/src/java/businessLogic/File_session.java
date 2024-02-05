/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import javax.ejb.Stateless;
import entity.FileStore;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import mapper.FileStoreFacade;

/**
 *
 * @author BABI
 */
@Stateless

public class File_session implements File_sessionLocal {

    @EJB
    public FileStoreFacade filestoreFacade;

    @Override
    public void saveFile(FileStore fileStore) {
        System.out.println("yessssssssssssssssssssssssssssss");
        filestoreFacade.create(fileStore);

    }

    @Override
    public FileStore findbyFId(Integer fileId) {
        return filestoreFacade.findByFileId(fileId);
    }

    @Override
    public List<FileStore> findAll() {
        return filestoreFacade.findAll();
    }
    
    @Override
    public void deleteFile(FileStore fileStore){
        System.out.println("-------inside file delete-----------");
        filestoreFacade.remove(fileStore);
    }
    @Override
    public List<FileStore> findByFileName(String name){
       return filestoreFacade.findByFileName(name);
    }
}
