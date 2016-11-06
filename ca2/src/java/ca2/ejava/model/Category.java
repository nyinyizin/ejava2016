/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.model;

/**
 *
 * @author XYZzzzz
 */
public enum Category {
    SOCIAL("Social"),
    FORSALE("For Sale"),
    JOBS("Jobs"),
    TUITUION("Tuition");
    
    private String desc;
    
    Category(String desc){
        this.desc = desc;
    }
    
    public String getDesc(){
        return desc;
    }
    
}
