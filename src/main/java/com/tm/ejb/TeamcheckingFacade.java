/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Teamchecking;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public List<Teamchecking> findByStudentChecking() {
        try {
            List<Teamchecking> teamcheckings=new ArrayList<>();
            //Query query=em.createNativeQuery("SELECT student_id, COUNT(*) FROM teamchecking WHERE pending=0 GROUP BY student_id",Teamchecking.class);
             Query query=em.createQuery("SELECT t FROM Teamchecking t");
           
            //Query query=em.createQuery("SELECT t.student_id, COUNT(*) FROM teamchecking t WHERE t.pending=0 GROUP BY t.student_id");
            teamcheckings=(List<Teamchecking>)query.getResultList();
            System.out.println("Size in facade is "+teamcheckings.size());
            for(Teamchecking t:teamcheckings){
                System.out.println("::::::::::::::>> "+t);
                System.out.println(":::::::::::::: "+t.getStudentId());
            }
            return teamcheckings;
        } catch (Exception e) {
            System.out.println("Exception here");
            e.printStackTrace();
        }
        return null;
    }
    
}
