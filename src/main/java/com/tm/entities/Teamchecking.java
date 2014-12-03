/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SuJoshi
 */
@Entity
@Table(name = "teamchecking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teamchecking.findAll", query = "SELECT t FROM Teamchecking t"),
    @NamedQuery(name = "Teamchecking.findById", query = "SELECT t FROM Teamchecking t WHERE t.id = :id"),
    @NamedQuery(name = "Teamchecking.findByCheckingTime", query = "SELECT t FROM Teamchecking t WHERE t.checkingStartTime = :checkingStartTime"),
    @NamedQuery(name = "Teamchecking.findByChecked", query = "SELECT t FROM Teamchecking t WHERE t.checked = :checked"),
    @NamedQuery(name = "Teamchecking.findByPending", query = "SELECT t FROM Teamchecking t WHERE t.pending = :pending")})
public class Teamchecking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "checking_start_time")
    private String checkingStartTime;
    @Column(name = "checked")
    private Boolean checked;
    @Column(name = "pending")
    private Boolean pending;
    @Column(name = "checking_end_time")
    private String checkingEndTime;
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacherId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private Student studentId;

    public Teamchecking() {
    }

    public Teamchecking(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckingStartTime() {
        return checkingStartTime;
    }

    public void setCheckingStartTime(String checkingStartTime) {
        this.checkingStartTime = checkingStartTime;
    }

    public String getCheckingEndTime() {
        return checkingEndTime;
    }

    public void setCheckingEndTime(String checkingEndTime) {
        this.checkingEndTime = checkingEndTime;
    }
   

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teamchecking)) {
            return false;
        }
        Teamchecking other = (Teamchecking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tm.entities.Teamchecking[ id=" + id + " ]";
    }
    
}
