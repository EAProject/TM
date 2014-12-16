/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Student;
import com.tm.entities.Teamchecking;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
            Teamchecking tmChecking = em.find(Teamchecking.class, Integer.parseInt((String) data));
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
    public List<Object[]> findByStudentChecking() {
        try {
            List<Object[]> results = new ArrayList<>();
            Query query = em.createQuery("SELECT t.studentId,COUNT(t) FROM Teamchecking t WHERE t.pending= :pending GROUP BY t.studentId");
            query.setParameter("pending", false);
            results = query.getResultList();
            return results;
        } catch (Exception e) {
            System.out.println("Exception here");
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Teamchecking> findByEmailChecked() {
        try {
            List<Teamchecking> results = new ArrayList<>();
            Query query = em.createNamedQuery("Teamchecking.findByChecked");
            query.setParameter("checked", false);
            results = query.getResultList();
            return results;
        } catch (Exception e) {            
            System.out.println("Find by checked is 0 in team checking");
            e.printStackTrace();
        }
        return null;
    }
     @Override
     public boolean UpdateChecked(Teamchecking teamchecking) {
        try {           
            Teamchecking tmChecking = em.find(Teamchecking.class, teamchecking.getId());           
            tmChecking.setChecked(Boolean.TRUE);
            em.merge(tmChecking);
            return true;
        } catch (Exception e) {
            System.out.println("Some error while updating Teamchecking status checked");
            e.printStackTrace();
        }
        return false;
    }
   

}
