/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.service;

import epod.business.DeliveryBean;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import epod.model.Delivery;

/**
 *
 * @author XYZzzzz
 */
@Stateless
public class BatchJobScheduler {
    private static final Logger logger = Logger.getLogger(
                            BatchJobScheduler.class.getName());
    
    @EJB private DeliveryBean deliveryBean; 
    
    @Schedule(second="30")
    public void startBatchJob(){
        try{
            ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
            deliveryList.addAll(deliveryBean.getAllDelivery());
            for(Delivery delivery:deliveryList){
                BatchJobSubmission batchJobSubmission = new BatchJobSubmission();
                batchJobSubmission.setDelivery(delivery);
                batchJobSubmission.run();
                //batchJobSubmission.jobSubmission(delivery);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
