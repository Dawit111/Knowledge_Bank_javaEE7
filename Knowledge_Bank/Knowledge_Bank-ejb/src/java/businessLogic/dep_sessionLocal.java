/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Department;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author BABI
 */
@Local
public interface dep_sessionLocal {
    
   public void saveDepartment(Department department); 

    public void deleteDept(Department department);

}
