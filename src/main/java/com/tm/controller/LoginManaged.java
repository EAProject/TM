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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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

        User user = facadeLocal.checkUserName(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                System.out.println("Logged in successfully::::::: ");
                int loggedInID = 0;
                loggedInID = checkValidateMember(user);
                System.out.println("VVVVVVVVVVVVVV " + loggedInID);
                if (loggedInID != 0) {
                    System.out.println("Logged in ID>> " + loggedInID);
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    session.setAttribute("userId", loggedInID);
                    return "home";
                } else {
                    System.out.println("Problem while loggedIn");
                }

            } else {
                System.out.println("Invalid login..");
            }
        } else {
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

    public int checkValidateMember(User u) {
        int loggedInID = 0;
        try {
            if (u.getTeacher().getId() != null) {
                loggedInID = u.getTeacher().getId();
            } else {
                System.out.println("Problem with loggedIn ID in teacher");
            }
        } catch (NullPointerException e) {
            System.out.println("EXCEPTION IN TEACHER ID");
        }
        try {
            if (u.getStudent().getId() != null) {
                loggedInID = u.getStudent().getId();
            } else {
                System.out.println("Problem with loggedIn ID in student");
            }
        } catch (NullPointerException e) {
            System.out.println("EXCEPTION IN STUDENT ID");
        }
        return loggedInID;
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
