/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SuJoshi
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {
    @PersistenceContext(unitName = "tm-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    @Override
    public List<User> getAllUser() {
        try {
            System.out.println(">###>>>>>>>>>:::>>>>>>>>>>>>");
            List<User> users=new ArrayList<>();
            Query q=em.createNamedQuery("User.findAll");
            System.out.println("AFTER QUERY "+q);
            //q.setParameter("isDeleted", 0);
            users=q.getResultList();
            System.out.println("Size is "+users.size());
            return users;
        } catch (Exception e) {
            System.out.println("EXCEPTION IN USERS LIST");
            e.printStackTrace();
        }
        return null;
    }
    
}
