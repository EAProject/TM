/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.utils;

/**
 *
 * @author SuJoshi
 */
public enum TMRole {
    IT(0), TEACHER(1), STUDENT(2);
    private int tmRole;

    private TMRole(int s) {
        tmRole = s;
    }

    public int getTmRole() {
        return tmRole;
    }

//    public void setTmRole(int tmRole) {
//        this.tmRole = tmRole;
//    }
}
