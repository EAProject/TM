/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Email;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sunil
 */
@Stateless
public class EmailFacade extends AbstractFacade<Email> implements EmailFacadeLocal {
    @PersistenceContext(unitName = "tm-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailFacade() {
        super(Email.class);
    }
    
     @Override
     public Email findByEmailContent() {
        try {
            Query query = em.createQuery("SELECT e FROM Email e ORDER BY e.id desc");
            query.setMaxResults(1);
            Email email=(Email) query.getSingleResult();
            return email;
        } catch (Exception e) {            
            System.out.println("Order by email id");
            e.printStackTrace();
        }
        return null;
    }
}
