/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.manual.ejb;

import com.tm.ejb.TeamcheckingFacadeLocal;
import com.tm.entities.Teamchecking;
import com.tm.utils.Email;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 *
 * @author sunil
 */
@Singleton
public class TimerSessionBean {

    @Resource
    TimerService timerService;

    private Date lastProgrammaticTimeout;
    private Date lastAutomaticTimeout;

    @EJB
    private TeamcheckingFacadeLocal teamcheckingFacadeLocal;
    private String subject="subjectTest";
    private String messageText="messageTest";
            

    private Logger logger = Logger.getLogger("com.sun.tutorial.javaee.ejb.timersession.TimerSessionBean");

    public void setTimer(long intervalDuration) {
        logger.info("Setting a programmatic timeout for " + intervalDuration + " milliseconds from now.");
        Timer timer = timerService.createTimer(intervalDuration, "Created new programmatic timer");
    }

    @Timeout
    public void programmaticTimeout(Timer timer) {
        this.setLastProgrammaticTimeout(new Date());
        logger.info("Programmatic timeout occurred.");
    }

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void automaticTimeout() {
        this.setLastAutomaticTimeout(new Date());
        logger.info("Automatic timeout occured");
        List<Teamchecking> teamcheckings = new ArrayList<>();
        teamcheckings = teamcheckingFacadeLocal.findByEmailChecked();
        try {
            for (Teamchecking teamchecking : teamcheckings) {
                try {
                    String dateInString = teamchecking.getCheckingStartTime();
                    Date dateNow = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = sdf.format(dateNow);

                    SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");
                    java.util.Date utilDate = formatter.parse(dateInString);
                    String tmCheckingDate = sdf.format(utilDate);
                    System.out.println("COMPARE = " + sdf.parse(tmCheckingDate).compareTo(sdf.parse(currentDate)));
                    sendEmail(teamchecking, sdf.parse(tmCheckingDate).compareTo(sdf.parse(currentDate)));

                } catch (ParseException ex) {
                    Logger.getLogger(TimerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (NullPointerException e) {
            System.out.println("catch teamchecking null value");
        }

    }

    public String getLastProgrammaticTimeout() {
        if (lastProgrammaticTimeout != null) {
            return lastProgrammaticTimeout.toString();
        } else {
            return "never";
        }

    }

    public void setLastProgrammaticTimeout(Date lastTimeout) {
        this.lastProgrammaticTimeout = lastTimeout;
    }

    public String getLastAutomaticTimeout() {
        if (lastAutomaticTimeout != null) {
            return lastAutomaticTimeout.toString();
        } else {
            return "never";
        }
    }

    public void setLastAutomaticTimeout(Date lastAutomaticTimeout) {
        this.lastAutomaticTimeout = lastAutomaticTimeout;
    }

    public void sendEmail(Teamchecking teamchecking, int checkDays) {
        if (checkDays == 0) {
            System.out.println("Student id is " + teamchecking.getStudentId().getEmail());
            boolean updateCheckedStatus = teamcheckingFacadeLocal.UpdateChecked(teamchecking);
            if (updateCheckedStatus == true) {
                Email email = new Email();
                email.setToemail(teamchecking.getStudentId().getEmail());
                email.setSubject(subject);
                email.setMessagetext(messageText, "testHASH");
                email.sendEMail();
            }

        } else if (checkDays > 0) {
            //update status checked=1

        } else {
            //update status checked=1;   
        }
    }

}
