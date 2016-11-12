/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.service;

import epod.business.DeliveryBean;
import epod.business.PodBean;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import epod.model.Pod;

/**
 *
 * @author XYZzzzz
 */
@Stateless
public class BatchJobScheduler {
    private static final Logger logger = Logger.getLogger(
                            BatchJobScheduler.class.getName());
    
    @EJB private PodBean podBean; 
    
    @Schedule(second="30")
    public void startBatchJob(){
        try{
            ArrayList<Pod> podList = new ArrayList<Pod>();
            podList.addAll(podBean.getAllNotAckedPod());
            for(Pod pod:podList){
                BatchJobSubmission batchJobSubmission = new BatchJobSubmission();
                batchJobSubmission.setPod(pod);
                batchJobSubmission.run();
                //batchJobSubmission.jobSubmission(Pod);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
