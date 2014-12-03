/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Appointment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sunil
 */
@Local
public interface AppointmentFacadeLocal {

    void create(Appointment appointment);

    void edit(Appointment appointment);

    void remove(Appointment appointment);

    Appointment find(Object id);

    List<Appointment> findAll();

    List<Appointment> findRange(int[] range);

    int count();
    
}
