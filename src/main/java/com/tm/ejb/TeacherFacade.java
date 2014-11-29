/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Teacher;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SuJoshi
 */
@Stateless
public class TeacherFacade extends AbstractFacade<Teacher> implements TeacherFacadeLocal {
    @PersistenceContext(unitName = "tm-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeacherFacade() {
        super(Teacher.class);
    }
    
}