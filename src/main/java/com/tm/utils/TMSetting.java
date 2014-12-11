/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.utils;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author SuJoshi
 */
@ManagedBean
@SessionScoped
public class TMSetting implements Serializable{
    
    private int totalTMChecking;
    
    public String settingValues(){
        System.out.println("TOTOAL TM "+totalTMChecking);
        return "";
    }

    public int getTotalTMChecking() {
        return totalTMChecking;
    }

    public void setTotalTMChecking(int totalTMChecking) {
        this.totalTMChecking = totalTMChecking;
    }

    
    
    
    
}
