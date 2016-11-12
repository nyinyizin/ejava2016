/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author nyinyizin
 */
@RequestScoped
@Named
public class DeliveryView {
    // [TODO] Add EJB Pod and Delivery
    
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
    
    public void createDelivery(){
        // [TODO] Save Delivery Entity
        // [TODO] Save Pod Entity
    }
    
    
}
