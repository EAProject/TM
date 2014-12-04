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
    private String test="AAA";

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    
    

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();        
        teamcheckings = new ArrayList<>();
        teamcheckings = teamcheckingFacadeLocal.findAll();  
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

    public Date getRandomDate(Date base) {
        System.out.println("GET RANDOM DATE");
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month
        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendar Today " + calendar.get(Calendar.YEAR) + ":::" + calendar.get(Calendar.MONTH) + "::" + calendar.get(Calendar.DATE));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }


    public void addEvent(ActionEvent actionEvent) {
        System.out.println("EVENT ID min >>>>>>>>>>>>>>>"+event.getData());
        
      

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        int teacherId = (int) session.getAttribute("userId");
        int studentId=1;
        Student student = studentFacadeLocal.find(studentId);
        System.out.println("STUDENT IS>>>>>>>>>>>>>>>>>> ");
        System.out.println("Teacher Name is " + student.getFirstName());

        Teamchecking teamchecking = new Teamchecking();
        teamchecking.setStudentId(student);
        teamchecking.setPending(Boolean.FALSE);
        teamcheckingFacadeLocal.edit(teamchecking);
        System.out.println("IS FINISHED");
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        System.out.println("EVENT SELECT");
         System.out.println("ID IS "+event.getData());
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        System.out.println(">>>>>>>>>>>>> " + event.getData());
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
         System.out.println("ONEVENT MODE>>>>>> "+event);
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
