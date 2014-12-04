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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sunil
 */
@Entity
@Table(name = "worker", catalog = "teamreservation", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Worker.findAll", query = "SELECT w FROM Worker w"),
    @NamedQuery(name = "Worker.findByWorkerId", query = "SELECT w FROM Worker w WHERE w.workerId = :workerId"),
    @NamedQuery(name = "Worker.findByWorkerName", query = "SELECT w FROM Worker w WHERE w.workerName = :workerName")})
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "worker_id")
    private Integer workerId;
    @Size(max = 50)
    @Column(name = "worker_name")
    private String workerName;
    @JoinColumn(name = "worker_info", referencedColumnName = "info_id")
    @OneToOne(optional = false)
    private Info workerInfo;

    public Worker() {
    }

    public Worker(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public Info getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(Info workerInfo) {
        this.workerInfo = workerInfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workerId != null ? workerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Worker)) {
            return false;
        }
        Worker other = (Worker) object;
        if ((this.workerId == null && other.workerId != null) || (this.workerId != null && !this.workerId.equals(other.workerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tm.entities.Worker[ workerId=" + workerId + " ]";
    }
    
}
