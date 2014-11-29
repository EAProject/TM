/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.ejb.TeacherFacadeLocal;
import com.tm.entities.Teacher;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author SuJoshi
 */
@ManagedBean(name = "teacherBean")
@SessionScoped
public class TeacherBean implements Serializable{
    @EJB
    private TeacherFacadeLocal teacherFacadeLocal;
    Teacher teacher=new Teacher();
    List<Teacher> teachers=new ArrayList<Teacher>();
    
    public TeacherBean(){
        try {
             System.out.println("IN TRY CATCH>>>>>>>>>>>>>>>>");
             teachers=teacherFacadeLocal.findAll();
             System.out.println("after query");
        } catch (NullPointerException e) {
        }
      System.out.println("Size is >>>>>>"+teachers.size());
        
    }
    public String showTeacherInfo(){
        System.out.println("Inside method");
        teachers=teacherFacadeLocal.findAll();
        System.out.println("SHOW TEACHER INFO "+teachers.size());
        return "index";
    }
    
    public String addTeacher(){
        teacher.setIsDeleted(false);
        teacherFacadeLocal.create(teacher);
        
        
        clearTeacherValue();
        return "success";
    }
    public void clearTeacherValue(){
        teacher.setFirstName("");
        teacher.setMiddleName("");
        teacher.setLastName("");
        teacher.setEmail("");
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    
    
}
