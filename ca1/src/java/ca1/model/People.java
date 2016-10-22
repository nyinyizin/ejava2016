/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "people")
public class People implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pid")
    private Integer peopleId;
    @Column(name = "name")
    @Basic(optional = false)
    private String name;
    @Column(name = "email")
    @Basic(optional = false)
    private String email;
    @OneToMany (mappedBy = "people")
    private ArrayList<Appointment> appointmentlist;

    /**
     * @return the peopleId
     */
    public Integer getPeopleId() {
        return peopleId;
    }

    /**
     * @param peopleId the peopleId to set
     */
    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the appointmentlist
     */
    public ArrayList<Appointment> getAppointmentlist() {
        return appointmentlist;
    }

    /**
     * @param appointmentlist the appointmentlist to set
     */
    public void setAppointmentlist(ArrayList<Appointment> appointmentlist) {
        this.appointmentlist = appointmentlist;
    }

}
