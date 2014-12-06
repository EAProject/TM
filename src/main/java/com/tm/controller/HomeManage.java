/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sunil
 */
@ManagedBean
@SessionScoped
public class HomeManage implements Serializable{
    
    public String redirectToHome(){
        return "home";
    }
}
