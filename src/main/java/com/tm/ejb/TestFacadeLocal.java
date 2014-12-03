/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Test;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sunil
 */
@Local
public interface TestFacadeLocal {

    void create(Test test);

    void edit(Test test);

    void remove(Test test);

    Test find(Object id);

    List<Test> findAll();

    List<Test> findRange(int[] range);

    int count();
    
}
