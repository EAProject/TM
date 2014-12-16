/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.utils;

import com.tm.ejb.EmailFacadeLocal;
import com.tm.entities.Email;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SuJoshi
 */
@ManagedBean
@SessionScoped
public class TMSetting implements Serializable{
    
    private int totalTMChecking;
    @EJB
    private EmailFacadeLocal emailFacadeLocal;
    private Email email=new Email();
    private String showMessage="";
    
    @PostConstruct
    public void clearValue(){
        System.out.println("Clear value");
        showMessage="";
        //setShowMessage("");
    }
   
    public String settingValues(){
        System.out.println("TOTOAL TM "+totalTMChecking);
        System.out.println("======================");
        return "";
    }

     public String updateEmailInfo(){
        System.out.println("========Update email info===========");
        emailFacadeLocal.create(email);
        showMessage="Update email information";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated Email setting"));
        return "";
    }
     
    public int getTotalTMChecking() {
        return totalTMChecking;
    }

    public void setTotalTMChecking(int totalTMChecking) {
        this.totalTMChecking = totalTMChecking;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    

   
    
    
    
}
