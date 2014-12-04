/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "info", catalog = "teamreservation", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Info.findAll", query = "SELECT i FROM Info i"),
    @NamedQuery(name = "Info.findByInfoId", query = "SELECT i FROM Info i WHERE i.infoId = :infoId"),
    @NamedQuery(name = "Info.findByAddress", query = "SELECT i FROM Info i WHERE i.address = :address"),
    @NamedQuery(name = "Info.findByCity", query = "SELECT i FROM Info i WHERE i.city = :city"),
    @NamedQuery(name = "Info.findByState", query = "SELECT i FROM Info i WHERE i.state = :state")})
public class Info implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "info_id")
    private Integer infoId;
    @Size(max = 50)
    @Column(name = "address")
    private String address;
    @Size(max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "state")
    private String state;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "workerInfo")
    private Worker worker;

    public Info() {
    }

    public Info(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infoId != null ? infoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Info)) {
            return false;
        }
        Info other = (Info) object;
        if ((this.infoId == null && other.infoId != null) || (this.infoId != null && !this.infoId.equals(other.infoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tm.entities.Info[ infoId=" + infoId + " ]";
    }
    
}
