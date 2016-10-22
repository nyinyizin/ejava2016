/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@NamedQuery(name="Appointment.findAllAppointments",
  //      query="Select a.appt_id, a.appt_date,a.description,a.pid from People p join Appointment a where p.email =:email ")
@Entity
@Table(name="appointment")
public class Appointment implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "appt_id")
	private Integer appointmentId;
    @Column(name = "description")
    private String description;
    @Column(name = "appt_date")
    private Date apptDate;
    
    @ManyToOne
    @JoinColumn( name = "pid", referencedColumnName = "pid")
    private People people;

    /**
     * @return the appointmentId
     */
    public Integer getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the apptDate
     */
    public Date getApptDate() {
        return apptDate;
    }

    /**
     * @param apptDate the apptDate to set
     */
    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    /**
     * @return the people
     */
    public People getPeople() {
        return people;
    }

    /**
     * @param people the people to set
     */
    public void setPeople(People people) {
        this.people = people;
    }
    
    
}
