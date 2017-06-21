/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.SprintDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Isuru
 */
@Remote
public interface SprintManagementRemote extends ManagementRemote<SprintDTO>{

    @Override
    SprintDTO create(SprintDTO entity);

    @Override
    SprintDTO edit(SprintDTO entity);

    @Override
    SprintDTO remove(SprintDTO entity);

    @Override
    SprintDTO find(Object id);

    @Override
    List<SprintDTO> findAll();

    @Override
    List<SprintDTO> findRange(int[] range);

    int count();
    
}
