/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.MemberDTO;
import java.util.List;

/**
 *
 * @author Isuru
 */
public interface ManagementRemote<DTO> {
    
    DTO create(DTO entity);

    DTO edit(DTO entity);

    DTO remove(DTO entity);

    DTO find(Object id);

    List<DTO> findAll();

    List<DTO> findRange(int[] range);

    int count();
    
}
