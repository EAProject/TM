/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Appointment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sunil
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> implements AppointmentFacadeLocal {
    @PersistenceContext(unitName = "tm-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }
    
}
