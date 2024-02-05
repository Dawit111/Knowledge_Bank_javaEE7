/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Faculty;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author BABI
 */
@Local
public interface Faculty_sessionLocal {
    public void saveFaculty(Faculty faculty);
    public void deleteFaculty(Faculty faculty);

}
