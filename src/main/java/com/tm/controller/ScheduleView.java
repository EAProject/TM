/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

/**
 *
 * @author sunil
 */
import com.tm.ejb.TeacherFacadeLocal;
import com.tm.ejb.TeamcheckingFacadeLocal;
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
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    @EJB
    private TeamcheckingFacadeLocal teamcheckingFacadeLocal;
    @EJB
    private TeacherFacadeLocal teacherFacadeLocal;
    List<Teamchecking> teamcheckings;
    private String hourMinuteSchedule;

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();

        teamcheckings = new ArrayList<>();
        teamcheckings = teamcheckingFacadeLocal.findAll();
        System.out.println("Size is >> " + teamcheckings.size());
        for (Teamchecking teamchecking : teamcheckings) {
            Date tmStartDate = null;
            Date tmEndDate = null;
            try {
                    tmStartDate = new SimpleDateFormat("MMMM d, yyyy").parse(teamchecking.getCheckingStartTime());
                    tmEndDate = new SimpleDateFormat("MMMM d, yyyy").parse(teamchecking.getCheckingEndTime());
            } catch (ParseException ex) {
                Logger.getLogger(ScheduleView.class.getName()).log(Level.SEVERE, null, ex);
            }
            eventModel.addEvent(new DefaultScheduleEvent(teamchecking.getNote(), tmStartDate, tmEndDate));

        }

        String string1 = "December 03, 2014";
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("MMMM d, yyyy").parse(string1);
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleView.class.getName()).log(Level.SEVERE, null, ex);
        }
        eventModel.addEvent(new DefaultScheduleEvent("DEF", date1, date1));

        System.out.println("Event model is " + eventModel);
        Date startDate = new Date();
        Date finishDate = new Date();
        eventModel.addEvent(new DefaultScheduleEvent("ABC", startDate, finishDate));
        System.out.println("New Date is " + new Date());
        lazyEventModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
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
        System.out.println("EVENT MODEL IS>> " + eventModel);
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

    public String convertStartDate() {
        String date = "Thu Dec 04 00:00:00 CST 2014";
        String[] startDateSeparate = date.split(" ");

        String tmStartDate = startDateSeparate[1] + " " + startDateSeparate[2] + "," + " " + startDateSeparate[5];
        return "";
    }

    public void addEvent(ActionEvent actionEvent) {
        System.out.println("Hour min "+hourMinuteSchedule);
        
        Date date1 = event.getStartDate();
        DateFormat df = new SimpleDateFormat("MMMM d, yyyy");
        String startDate = df.format(date1);
        Date date2 = event.getEndDate();
        DateFormat dfEnd = new SimpleDateFormat("MMMM d, yyyy");
        String endDate = dfEnd.format(date2);

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        int teacherId = (int) session.getAttribute("userId");
        System.out.println("Teacher iD is " + teacherId);
        Teacher teacher = teacherFacadeLocal.find(teacherId);
        System.out.println("Teacher Name is " + teacher.getFirstName());

        Teamchecking teamchecking = new Teamchecking();
        teamchecking.setChecked(Boolean.FALSE);
        teamchecking.setCheckingStartTime(startDate);
        teamchecking.setCheckingEndTime(endDate);
        teamchecking.setTeacherId(teacher);
        teamchecking.setNote(event.getTitle());
        
        teamcheckingFacadeLocal.create(teamchecking);
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        System.out.println(">>>>>>>>>>>>> " + selectEvent.getObject());
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        System.out.println("ON DATE SELECT " + event);
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

    public String getHourMinuteSchedule() {
        return hourMinuteSchedule;
    }

    public void setHourMinuteSchedule(String hourMinuteSchedule) {
        this.hourMinuteSchedule = hourMinuteSchedule;
    }
    
}
