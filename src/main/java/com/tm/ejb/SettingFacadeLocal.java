/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.ejb;

import com.tm.entities.Setting;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sunil
 */
@Local
public interface SettingFacadeLocal {

    void create(Setting setting);

    void edit(Setting setting);

    void remove(Setting setting);

    Setting find(Object id);

    List<Setting> findAll();

    List<Setting> findRange(int[] range);

    int count();
    
}
