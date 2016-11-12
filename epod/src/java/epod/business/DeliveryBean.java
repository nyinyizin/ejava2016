/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.business;

import epod.model.Delivery;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Stateless
public class DeliveryBean {

    @PersistenceContext
    private EntityManager em;

    public void save(Delivery delivery) {
        try{
            em.persist(delivery);
        }catch (ConstraintViolationException e){
             Set<ConstraintViolation<?>> cvs = e.getConstraintViolations();
             String errMsg = "";
              for (ConstraintViolation<?> cv : cvs) {    
                    errMsg = cv.getMessage();
                }
        }
    }

    public void update(Delivery delivery) {
        em.merge(delivery);
    }

    public Optional<Delivery> find(String pkg_id) {
        return (Optional.ofNullable(em.find(Delivery.class, pkg_id)));
    }

    public List<Delivery> getAllDelivery() {
        TypedQuery<Delivery> query = em.createQuery(
                "select d from Delivery d order by d.create_date desc",
                Delivery.class);
        return (query.getResultList());
    }
}
