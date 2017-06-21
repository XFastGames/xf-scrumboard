/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Isuru
 */
@Local
public interface FacadeLocal<DAO> {

    DAO create(DAO memberr);

    DAO edit(DAO memberr);

    DAO remove(DAO memberr);

    DAO find(Object id);

    List<DAO> findAll();

    List<DAO> findRange(int[] range);

    int count();
    
}
