/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.TeamDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Isuru
 */
@Remote
public interface TeamManagementRemote extends ManagementRemote<TeamDTO>{

    @Override
    TeamDTO create(TeamDTO entity);

    @Override
    TeamDTO edit(TeamDTO entity);

    @Override
    TeamDTO remove(TeamDTO entity);

    @Override
    TeamDTO find(Object id);

    @Override
    List<TeamDTO> findAll();

    @Override
    List<TeamDTO> findRange(int[] range);

    int count();
    
}
