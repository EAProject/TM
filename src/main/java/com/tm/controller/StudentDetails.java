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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author sunil
 */
@ManagedBean(name = "studentDetails")
@SessionScoped
public class StudentDetails implements Serializable{
    List<Teamchecking> teamcheckings=new ArrayList<>();
    @EJB
    private TeamcheckingFacadeLocal teamcheckingFacadeLocal;
    private Student student=new Student();
    @EJB
    private StudentFacadeLocal studentFacadeLocal;
    @EJB
    private UserFacadeLocal userFacadeLocal;
    
    public String studentTMCheckingDetails(){
        teamcheckings=teamcheckingFacadeLocal.findByStudentChecking();
        System.out.println("Size is>>> "+teamcheckings.size());
       
        for(int i=0;i<teamcheckings.size();i++){
           //tmcheckings.add(t);           
            Teamchecking teamchecking=teamcheckings.get(i);
           //System.out.println("Value is>>>>>>>>> "+teamcheckings.get(i));
           System.out.println("ID IS "+teamchecking.getId());
           // tmcheckings=(List<Teamchecking>) teamcheckings.get(i);
            
        }
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
//           for(Teamchecking t:tmcheckings){
//               System.out.println("IS HERE");        
//        }
//        for(int i=0;i<teamcheckings.size();i++){
//            System.out.println("TEAM CHECKING:: "+teamcheckings.get(i).getId());
//        }
        return "studentTmCheckingDetails";
    }
      public void addUser(Student s){
        User user=new User();
        user.setEmail(s.getEmail());
        user.setPassword(s.getPassword());
        TMRole role=TMRole.STUDENT;
        user.setRole(role.getTmRole());
        user.setStatus(0);
        user.setIsDeleted(Boolean.FALSE);
        user.setCreatedDate(new Date());
        user.setStudent(s);
        userFacadeLocal.create(user);
    }
    public String addTeacher() {
        student.setIsDeleted(false);
        studentFacadeLocal.create(student);
        
        addUser(student);        
        clearStudentValue();
//        try {
//            teachers = teacherFacadeLocal.findAll();
//        } catch (NullPointerException e) {
//        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully create teacher having email "+student.getEmail()));
        return "createTeacher?faces-redirect=true";
    }
     public void clearStudentValue() {
        student.setFirstName("");
        student.setMiddleName("");
        student.setLastName("");
        student.setEmail("");
    }
     public String showStudentInfo() {
        System.out.println("Inside method");
        //teachers = teacherFacadeLocal.findAll();
       //return "createTeacher";       
       return "createStudent?faces-redirect=true";
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
    
    
    
    
}
