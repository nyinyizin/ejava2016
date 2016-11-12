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
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.ejb.Timer;

/**
 *
 * @author XYZzzzz
 */
@Singleton
@Startup
@LocalBean
public class BatchJobScheduler {
    private static final Logger logger = Logger.getLogger(
                            BatchJobScheduler.class.getName());
    
    @EJB private PodBean podBean;
    @Resource
    private TimerService timerService;
    
    @Schedule(second="30")
    public void execute(Timer timer){
        try{
            ArrayList<Pod> podList = new ArrayList<Pod>();
            podList.addAll(podBean.getAllNotAckedPod());
            for(Pod pod:podList){
                BatchJobSubmission batchJobSubmission = new BatchJobSubmission();
                //batchJobSubmission.setPod(pod);
                //batchJobSubmission.run();
                batchJobSubmission.jobSubmission(pod);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
