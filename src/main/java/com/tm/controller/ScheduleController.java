///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.tm.controller;
//
///**
// *
// * @author sunil
// */
//
//import java.util.Calendar;
//import java.util.Date;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;
// 
//import org.primefaces.event.ScheduleEntryMoveEvent;
//import org.primefaces.event.ScheduleEntryResizeEvent;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.model.DefaultScheduleEvent;
//import org.primefaces.model.DefaultScheduleModel;
//import org.primefaces.model.LazyScheduleModel;
//import org.primefaces.model.ScheduleEvent;
//import org.primefaces.model.ScheduleModel;
// 
//@ManagedBean
//@ViewScoped
//public class ScheduleController {
// private ScheduleModel eventModel;
//	
//	private ScheduleModel lazyEventModel;
//
//	private ScheduleEvent event = new DefaultScheduleEvent();
//	
//	private String theme;
//
//	public ScheduleController() {
//		eventModel = new DefaultScheduleModel();
//		eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
//		eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
//		eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
//		eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
//		
//		lazyEventModel = new LazyScheduleModel() {
//			
//			@Override
//			public void fetchEvents(Date start, Date end) {
//				clear();
//				
//				Date random = getRandomDate(start);
//				addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
//				
//				random = getRandomDate(start);
//				addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
//			}	
//		};
//	}
//	
//	public void addEvent(ActionEvent actionEvent) {
//		if(event.getId() == null)
//			eventModel.addEvent(event);
//		else
//			eventModel.updateEvent(event);
//		
//		event = new DefaultScheduleEvent();
//	}
//	
//	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
//		event = selectEvent.getScheduleEvent();
//	}
//	
//	public void onDateSelect(DateSelectEvent selectEvent) {
//		event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());
//	}
//	
//	public void onEventMove(ScheduleEntryMoveEvent event) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//		
//		addMessage(message);
//	}
//	
//	public void onEventResize(ScheduleEntryResizeEvent event) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//		
//		addMessage(message);
//	}
//	
//	private void addMessage(FacesMessage message) {
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}
//	
//	//Getters and Setters
//}
