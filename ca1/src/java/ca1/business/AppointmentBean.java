/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.business;

import ca1.model.Appointment;
import ca1.model.People;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AppointmentBean {

    @PersistenceContext
    private EntityManager em;

    public void save(Appointment appointment) {
        em.persist(appointment);
    }

    public List<Appointment> findAllAppointment(String email) {
        TypedQuery<Appointment> query = em.createNamedQuery(
                "Appointment.findAllAppointments", Appointment.class);
        query.setParameter("email", email);
        return (query.getResultList());
    }

}
