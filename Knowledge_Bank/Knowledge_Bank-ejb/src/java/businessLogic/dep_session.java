/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import javax.ejb.Stateless;
import entity.Department;
import java.util.List;
import javax.ejb.EJB;
import mapper.DepartmentFacade;

@Stateless
public class dep_session implements dep_sessionLocal {

    @EJB
    DepartmentFacade departmentFacade;

    @Override
    public void saveDepartment(Department department) {
        departmentFacade.create(department);
    }

    @Override
    public void deleteDept(Department department) {
        departmentFacade.remove(department);
    }

}
