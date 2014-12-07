/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Teamchecking;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sunil
 */
@Local
public interface TeamcheckingFacadeLocal {

    void create(Teamchecking teamchecking);

    void edit(Teamchecking teamchecking);

    void remove(Teamchecking teamchecking);

    Teamchecking find(Object id);

    List<Teamchecking> findAll();

    List<Teamchecking> findRange(int[] range);

    int count();
    
    public boolean Update(Teamchecking teamchecking, Object data);
    
     List<Object[]> findByStudentChecking();
    
}
