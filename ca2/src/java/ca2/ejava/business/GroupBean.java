/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.business;

import ca2.ejava.model.Groups;
import ca2.ejava.model.User;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GroupBean {

    @PersistenceContext
    private EntityManager em;

    public void save(Groups group) {
        em.persist(group);
    }

}