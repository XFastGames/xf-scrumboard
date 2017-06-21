/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.MemberDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Isuru
 */
@Remote
public interface MemberManagementRemote extends ManagementRemote<MemberDTO> {

    @Override
    MemberDTO create(MemberDTO entity);

    @Override
    MemberDTO edit(MemberDTO entity);

    @Override
    MemberDTO remove(MemberDTO entity);

    @Override
    MemberDTO find(Object id);
    
    MemberDTO findByEmail(String email); 
    
    boolean UserExistWithEmail(String email);
    
    MemberDTO findByToken(String token);

    @Override
    List<MemberDTO> findAll();

    @Override
    List<MemberDTO> findRange(int[] range);

    @Override
    int count();
    
}
