/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Task;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Isuru
 */
@Stateless(name="TaskFacade")
public class TaskFacade extends AbstractFacade<Task> implements FacadeLocal<Task> {

    @PersistenceContext(unitName = "com.xfastgames_xf-scrumboard-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }
    
}
