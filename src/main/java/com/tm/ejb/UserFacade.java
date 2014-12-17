/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.User;
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
    public User checkUserName(String email) {
        try {
             User user=new User();           
             Query q=em.createQuery("SELECT u FROM User u WHERE u.isDeleted = :isDeleted AND u.email = :email");
             q.setParameter("isDeleted", false); 
             q.setParameter("email", email); 
            user=(User) q.getSingleResult();
            return user;
        } catch (Exception e) {
            System.out.println("EXCEPTION IN USERS LIST");
            //e.printStackTrace();
        }
        return null;
    }
    
}
