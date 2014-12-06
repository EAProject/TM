/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.ejb.TeamcheckingFacadeLocal;
import com.tm.entities.Teamchecking;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

    public List<Teamchecking> getTeamcheckings() {
        return teamcheckings;
    }

    public void setTeamcheckings(List<Teamchecking> teamcheckings) {
        this.teamcheckings = teamcheckings;
    }
    
}
