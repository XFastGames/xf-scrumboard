/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.ProjectDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Isuru
 */
@Remote
public interface ProjectManagementRemote extends ManagementRemote<ProjectDTO>{

    @Override
    ProjectDTO create(ProjectDTO entity);

    @Override
    ProjectDTO edit(ProjectDTO entity);

    @Override
    ProjectDTO remove(ProjectDTO entity);

    @Override
    ProjectDTO find(Object id);

    @Override
    List<ProjectDTO> findAll();

    @Override
    List<ProjectDTO> findRange(int[] range);

    int count();
    
}
