/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.ejb.StudentFacade;
import com.tm.ejb.StudentFacadeLocal;
import com.tm.ejb.TeacherFacadeLocal;
import com.tm.ejb.TeamcheckingFacadeLocal;
import com.tm.entities.Student;
import com.tm.entities.Teacher;
import com.tm.entities.Teamchecking;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author SuJoshi
 */
@ManagedBean(name = "studentAppointment")
@ViewScoped
public class StudentAppointment implements Serializable{
    
    public String redirectToStudentSchedule(){        
        return "studentTMDate";
    }
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    @EJB
    private TeamcheckingFacadeLocal teamcheckingFacadeLocal;
    @EJB
    private TeacherFacadeLocal teacherFacadeLocal;
    @EJB
    private StudentFacadeLocal studentFacadeLocal;
    List<Teamchecking> teamcheckings;
    private Date hourMinuteSchedule;    

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();        
        teamcheckings = new ArrayList<>();
        teamcheckings = teamcheckingFacadeLocal.findAll(); 
        System.out.println("SIZE IS >> "+teamcheckings.size());
        for (Teamchecking teamchecking : teamcheckings) {
            Date tmStartDate = null;
            Date tmEndDate = null;
            if(teamchecking.getPending()==true){
                    try {
                        tmStartDate = new SimpleDateFormat("MMMM d, yyyy").parse(teamchecking.getCheckingStartTime());
                        tmEndDate = new SimpleDateFormat("MMMM d, yyyy").parse(teamchecking.getCheckingEndTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ScheduleView.class.getName()).log(Level.SEVERE, null, ex);
                }                    
                eventModel.addEvent(new DefaultScheduleEvent(teamchecking.getNote(), tmStartDate, tmEndDate,teamchecking.getId()));
            }            
        }
    }


    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }


    public void addEvent(ActionEvent actionEvent) {
        //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        //int teacherId = (int) session.getAttribute("userId");
        int studentId=1;
        Student student = studentFacadeLocal.find(studentId);
        Teamchecking teamchecking = new Teamchecking();
        teamchecking.setStudentId(student);        
        boolean checkUpdate=teamcheckingFacadeLocal.Update(teamchecking,event.getData());
        FacesMessage message;
        if (checkUpdate == true) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,null, "Selected date successfully");
            addMessage(message);
        } else {
             message = new FacesMessage(FacesMessage.SEVERITY_INFO,null, "Problem while selecting date ");
             addMessage(message);
        }
        System.out.println("RETURN>>");
        init();
    //  return "studentTMDate?faces-redirect=true"; 
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Date getHourMinuteSchedule() {
        return hourMinuteSchedule;
    }

    public void setHourMinuteSchedule(Date hourMinuteSchedule) {
        this.hourMinuteSchedule = hourMinuteSchedule;
    }
}
