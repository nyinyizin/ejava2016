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
public class PeopleBean {

    @PersistenceContext
    private EntityManager em;

    public Optional<People> find(final String email) {
        TypedQuery<People> query = em.createNamedQuery(
                "People.findPeople", People.class);

        return (Optional.ofNullable(query.getSingleResult()));

    }

    public void save(People people) {
        em.persist(people);
    }

}
