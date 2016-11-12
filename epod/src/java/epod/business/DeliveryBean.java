/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.business;

import epod.model.Delivery;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class DeliveryBean {

    @PersistenceContext
    private EntityManager em;

    public void save(Delivery delivery) {
        em.persist(delivery);
    }

    public Optional<Delivery> find(String pkg_id) {
        return (Optional.ofNullable(em.find(Delivery.class, pkg_id)));
    }
}
