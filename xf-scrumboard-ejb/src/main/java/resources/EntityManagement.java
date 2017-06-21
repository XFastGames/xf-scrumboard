/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Identifiable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isuru
 * @param <DTO> Data Transfer object
 * @param <DAO> Data Access object
 */
public abstract class EntityManagement<DTO,DAO>{

    public abstract DAO DTO2DAO(DTO myDTO);   
    public abstract DTO DAO2DTO(DAO myDTO);
    
    public abstract FacadeLocal<DAO> getFacade();
    
    public DTO create(DTO entity) {
        return DAO2DTO(getFacade().create(DTO2DAO(entity)));
    }

    public DTO edit(DTO entity) {
        Identifiable i = (Identifiable)entity;
        if(find(i.getId())==null) return null; //No Member with the given id found
        return DAO2DTO(getFacade().edit(DTO2DAO(entity)));
    }

    public DTO remove(DTO entity) {
        Identifiable i = (Identifiable)entity;
        if(find(i.getId())==null) return null; //No Member with the given id found
        return DAO2DTO(getFacade().remove(DTO2DAO(entity)));
        
    }

    public DTO find(Object id) {
        DAO found = getFacade().find(id);
        DTO toReturn = null;
        if(found!=null) toReturn = this.DAO2DTO(found);
        return toReturn;
    }

    public List<DTO> findAll() {
        List<DAO> myDAOs = getFacade().findAll();
        List<DTO> myDTOs = new ArrayList<>(); 
        for(DAO DAO : myDAOs){
            myDTOs.add(this.DAO2DTO(DAO));
        }
        return myDTOs;
    }

    public List<DTO> findRange(int[] range) {
        List<DAO> myDAOs = getFacade().findRange(range);
        List<DTO> myDTOs = new ArrayList<>(); 
        for(DAO DAO : myDAOs){
            myDTOs.add(this.DAO2DTO(DAO));
        }
        return myDTOs;
    }

    public int count() {
        return getFacade().count();
    }

}
