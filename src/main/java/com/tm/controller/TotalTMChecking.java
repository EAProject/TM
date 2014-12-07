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
    
    public TotalTMChecking(Long tmCount,Student student){
        this.tmCount=tmCount;
        this.student=student;
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
    
    
}
