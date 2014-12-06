/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.ejb.TeacherFacadeLocal;
import com.tm.ejb.UserFacadeLocal;
import com.tm.entities.Teacher;
import com.tm.entities.User;
import com.tm.utils.TMRole;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author SuJoshi
 */
@ManagedBean(name = "teacherBean")
@SessionScoped
public class TeacherBean implements Serializable {

    @EJB
    private TeacherFacadeLocal teacherFacadeLocal;
    Teacher teacher = new Teacher();
    List<Teacher> teachers = new ArrayList<Teacher>();
    @EJB
    private UserFacadeLocal userFacadeLocal;

    public TeacherBean() {
        try {
            teachers = teacherFacadeLocal.findAll();
        } catch (NullPointerException e) {
        }

    }

    public void onRowCancel(RowEditEvent event) {
        try {
            Teacher teacher = (Teacher) event.getObject();
            if (teacher != null) {
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Teacher teacher = (Teacher) event.getObject();
            if (teacher != null) {
                teacherFacadeLocal.edit(teacher);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated Teacher"));
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String showTeacherInfo() {
        System.out.println("Inside method");
        teachers = teacherFacadeLocal.findAll();
        return "createTeacher?faces-redirect=true";
    }

    public void addUser(Teacher t) {
        User user = new User();
        user.setEmail(t.getEmail());
        user.setPassword(t.getPassword());
        TMRole role = TMRole.TEACHER;
        user.setRole(role.getTmRole());
        user.setStatus(0);
        user.setIsDeleted(Boolean.FALSE);
        user.setCreatedDate(new Date());
        user.setTeacher(t);
        userFacadeLocal.create(user);
    }

    public String addTeacher() {
        teacher.setIsDeleted(false);
        teacherFacadeLocal.create(teacher);
        addUser(teacher);
        clearTeacherValue();
        try {
            teachers = teacherFacadeLocal.findAll();
        } catch (NullPointerException e) {
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully create teacher having email " + teacher.getEmail()));
        return "createTeacher?faces-redirect=true";
    }

    public void clearTeacherValue() {
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
