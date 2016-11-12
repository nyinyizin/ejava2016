/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.business;

import epod.model.Pod;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PodBean {

    @PersistenceContext
    private EntityManager em;

    public void save(Pod pod) {
        em.persist(pod);
    }

    public Optional<Pod> find(String podId) {
        return (Optional.ofNullable(em.find(Pod.class, podId)));
    }
}
