/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.business;

import ca2.ejava.model.Note;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ca2.ejava.model.User;
/**
 *
 * @author XYZzzzz
 */
@Stateless
public class MenuBean {
    
    @PersistenceContext private EntityManager em;
    
    public List<Note> getAllNoteByUser(String userId){
        TypedQuery<Note> query = em.createQuery(
				"select n from Note n order by n.postDate desc", 
				Note.class);
		return (query.getResultList());
    }
    
    public List<Note> getNotesByUser(String userId){
        TypedQuery<Note> query = em.createNamedQuery(
                "note.findNoteByUserId", Note.class);
                query.setParameter("userId", userId);
		return (query.getResultList());
    }
    
    public void save(Note note){
        em.persist(note);
    }
}
