/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.ejb.UserFacadeLocal;
import com.tm.entities.User;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sunil
 */
@ManagedBean
@SessionScoped
public class LoginManaged {

    private String username;
    private String password;
    private String errorMessage;
    @EJB
    private UserFacadeLocal facadeLocal;

    public String checkLogin() {
        System.out.println("SIZE IS>>>>>>>>>>>>>>>> ");
        
        User user=facadeLocal.checkUserName(username);
        if(user!=null){
           if(user.getPassword().equals(password)){
               System.out.println("Logged in successfully");
                 return "home";
           }else{
                System.out.println("Invalid login..");
           }
        }else{
            System.out.println("Invalid login");
        }
//        if (username.equalsIgnoreCase("admin") && password.equals("admin")) {
//            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//            session.setAttribute("userId", 2);
//            return "teacherHome";
//        } else {
//            System.out.println("ERROR");
//            errorMessage = "Invalid Login";
//        }
        return "login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
