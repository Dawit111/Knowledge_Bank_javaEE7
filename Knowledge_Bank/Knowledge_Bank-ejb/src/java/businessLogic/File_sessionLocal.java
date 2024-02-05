/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.FileStore;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author BABI
 */
@Local
public interface File_sessionLocal {

    public void saveFile(FileStore filestore);

    public FileStore findbyFId(Integer fid);
    
    public List<FileStore> findAll();
    
    public void deleteFile(FileStore fileStore);

    public List<FileStore> findByFileName(String name);

    
}
