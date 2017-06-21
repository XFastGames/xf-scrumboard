/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.TaskDTO;
import entities.TeamDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Isuru
 */
@Remote
public interface TaskManagementRemote extends ManagementRemote<TaskDTO>{

    @Override
    TaskDTO create(TaskDTO entity);

    @Override
    TaskDTO edit(TaskDTO entity);

    @Override
    TaskDTO remove(TaskDTO entity);

    @Override
    TaskDTO find(Object id);

    @Override
    List<TaskDTO> findAll();

    @Override
    List<TaskDTO> findRange(int[] range);

    int count();
    
}
