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
import javax.persistence.TypedQuery;

@Stateless
public class PodBean {

    @PersistenceContext
    private EntityManager em;

    public void save(Pod pod) {
        em.persist(pod);
    }

    public void update(Pod pod) {
        em.merge(pod);
    }

    public Optional<Pod> find(String podId) {
        return (Optional.ofNullable(em.find(Pod.class, podId)));
    }

    public List<Pod> getAllPod() {
        TypedQuery<Pod> query = em.createQuery(
                "select p from Pod p order by p.delivery_date desc",
                Pod.class);
        return (query.getResultList());
    }

    public List<Pod> getAllNotAckedPod() {
        TypedQuery<Pod> query = em.createQuery(
                "select p from Pod p where (p.ack_id IS NULL) by p.delivery_date desc",
                Pod.class);
        return (query.getResultList());
    }
}
