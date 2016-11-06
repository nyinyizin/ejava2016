/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.business;

import ca2.ejava.model.Note;
import ca2.ejava.model.User;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<Note> findUserNote(String email) {
        TypedQuery<Note> query = em.createNamedQuery(
                "Note.findUserNotes", Note.class);
        query.setParameter("us", email);
        return (query.getResultList());
    }
    
    public Optional<User> find(String userId) {
        return (Optional.ofNullable(em.find(User.class, userId)));
    }
}
