/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.entities.Student;

/**
 *
 * @author sunil
 */
public class TotalTMChecking {
    private Student student;
    private Long tmCount;
    private boolean tmStatus;
    
    public TotalTMChecking(Long tmCount,Student student,boolean tmStatus){
        this.tmCount=tmCount;
        this.student=student;
        this.tmStatus=tmStatus;
    }

    public Long getTmCount() {
        return tmCount;
    }

    public void setTmCount(Long tmCount) {
        this.tmCount = tmCount;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isTmStatus() {
        return tmStatus;
    }

    public void setTmStatus(boolean tmStatus) {
        this.tmStatus = tmStatus;
    }
    
    
}
