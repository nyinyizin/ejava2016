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


/**
 *
 * @author nyinyizin
 */
@Stateless
public class NoteBean {
    @PersistenceContext private EntityManager em;
    
    public List<Note> getAllNote(){
        TypedQuery<Note> query=em.createQuery("select n from Note n", Note.class);
        return (query.getResultList());
    }
    
    public List<Note> getNoteByCategory(String category){
        TypedQuery<Note> query=em.createQuery("select n from Note n where n.category=:category",Note.class);
        return query.setParameter("category", category).getResultList();
    }
}
