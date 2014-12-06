/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.ejb.StudentFacadeLocal;
import com.tm.ejb.TeamcheckingFacadeLocal;
import com.tm.ejb.UserFacadeLocal;
import com.tm.entities.Student;
import com.tm.entities.Teamchecking;
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
 * @author sunil
 */
@ManagedBean(name = "studentDetails")
@SessionScoped
public class StudentDetails implements Serializable {

    List<Teamchecking> teamcheckings = new ArrayList<>();
    @EJB
    private TeamcheckingFacadeLocal teamcheckingFacadeLocal;
    private Student student = new Student();
    @EJB
    private StudentFacadeLocal studentFacadeLocal;
    @EJB
    private UserFacadeLocal userFacadeLocal;
    List<Student> students = new ArrayList<Student>();

    public StudentDetails() {
        System.out.println("Inside the constructor::");
        try {
            students = studentFacadeLocal.findAll();
        } catch (NullPointerException e) {
            System.out.println("Null pointer except");
        }
    }

    public String studentTMCheckingDetails() {
        teamcheckings = teamcheckingFacadeLocal.findByStudentChecking();
        System.out.println("Size is>>> " + teamcheckings.size());

        for (int i = 0; i < teamcheckings.size(); i++) {
            Teamchecking teamchecking = teamcheckings.get(i);
            System.out.println("ID IS " + teamchecking.getId());

        }
        return "studentTmCheckingDetails";
    }

    public void addUser(Student s) {
        System.out.println("EMAIL IS " + s.getEmail());
        User user = new User();
        user.setEmail(s.getEmail());
        user.setPassword(s.getPassword());
        TMRole role = TMRole.STUDENT;
        user.setRole(role.getTmRole());
        user.setStatus(0);
        user.setIsDeleted(Boolean.FALSE);
        user.setCreatedDate(new Date());
        user.setStudent(s);
        userFacadeLocal.create(user);
    }

    public String addStudent() {
        student.setIsDeleted(false);
        System.out.println("Student email is " + student.getEmail());
        System.out.println("Student last is " + student.getLastName());
        System.out.println("Student first is " + student.getFirstName());

        studentFacadeLocal.create(student);

        addUser(student);
        try {
            students = studentFacadeLocal.findAll();
            System.out.println("Size is "+students.size());
        } catch (NullPointerException e) {
        }
        clearStudentValue();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully create teacher having email " + student.getEmail()));
        return "createStudent?faces-redirect=true";
    }

    public void clearStudentValue() {
        student.setFirstName("");
        student.setMiddleName("");
        student.setLastName("");
        student.setEmail("");
    }

    public String showStudentInfo() {
        //teachers = teacherFacadeLocal.findAll();
        return "createStudent";       
        //return "createStudent?faces-redirect=true";
    }
    public void onStudentRowEdit(RowEditEvent event) {
        try {
            Student stud = (Student) event.getObject();
            if (stud != null) {
                studentFacadeLocal.edit(student);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated Student"));
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void onStudentRowCancel(RowEditEvent event) {
        try {
            Student stud = (Student) event.getObject();
            if (stud != null) {
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("bundle").getString("msg.error.save"), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Teamchecking> getTeamcheckings() {
        return teamcheckings;
    }

    public void setTeamcheckings(List<Teamchecking> teamcheckings) {
        this.teamcheckings = teamcheckings;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    

}
