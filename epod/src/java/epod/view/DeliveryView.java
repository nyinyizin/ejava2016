/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.view;

import epod.business.DeliveryBean;
import epod.business.PodBean;
import epod.model.Delivery;
import epod.model.Pod;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author nyinyizin
 */
@RequestScoped
@Named
public class DeliveryView {
    // [TODO] Add EJB Pod and Delivery
    
    @EJB private DeliveryBean deliveryBean;
    @EJB private PodBean podBean;
    
    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfDeliveryCreation;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfDeliveryCreation() {
        return dateOfDeliveryCreation;
    }

    public void setDateOfDeliveryCreation(String dateOfDeliveryCreation) {
        this.dateOfDeliveryCreation = dateOfDeliveryCreation;
    }
    
    public String createDelivery(){
        // [TODO] Save Delivery Entity
        // [TODO] Save Pod Entity
        Delivery delivery=new Delivery();
        delivery.setName(name);
        delivery.setAddress(address);
        delivery.setPhone(phoneNumber);
        java.util.Date today = new java.util.Date();
        Timestamp date = new java.sql.Timestamp(today.getTime());
        delivery.setCreateDate(date);
        System.out.println(delivery.getAddress());
        deliveryBean.save(delivery);

        Pod pod=new Pod();
        pod.setDelivery(delivery);
        podBean.save(pod);
        return ("/faces/index.xhtml");
    }
    
    
}
