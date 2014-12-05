/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Teamchecking;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sunil
 */
@Stateless
public class TeamcheckingFacade extends AbstractFacade<Teamchecking> implements TeamcheckingFacadeLocal {
    @PersistenceContext(unitName = "tm-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeamcheckingFacade() {
        super(Teamchecking.class);
    }

    @Override
    public boolean Update(Teamchecking teamchecking, Object data) {
        try {
            System.out.println("FIND IN FACADE "+data);
            Teamchecking tmChecking=em.find(Teamchecking.class,Integer.parseInt((String)data));
            System.out.println(">>>>>>>>>>>");
            System.out.println("FIND TM CHECKING "+tmChecking.getId());
            System.out.println("STUDENT NAME IS:: "+teamchecking.getStudentId()+" ::::: "+teamchecking.getPending());
            tmChecking.setStudentId(teamchecking.getStudentId());
            tmChecking.setPending(Boolean.FALSE);
            em.merge(tmChecking);
            return true;
        } catch (Exception e) {
            System.out.println("Some error while updating Teamchecking");
            e.printStackTrace();
        }
        return false;
    }
    
}
