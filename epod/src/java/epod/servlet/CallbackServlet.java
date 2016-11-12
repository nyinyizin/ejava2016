/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.servlet;

import epod.business.PodBean;
import epod.model.Pod;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author nyinyizin
 */
@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {
    
     private final static Logger LOGGER=Logger.getLogger(CallbackServlet.class.getCanonicalName());
     @EJB private PodBean podBean;
     
     protected void doGet(HttpServletRequest req, HttpServletResponse res){
         String podID = req.getParameter("podId");
         String ackID = req.getParameter("ackId");
         
         // [TODO] Save to pod entity
    
         Optional<Pod> pod=podBean.find(podID);
         if(pod.isPresent()){
             Pod updatedPod=pod.get();
             updatedPod.setAckId(ackID);
             podBean.update(updatedPod);
         }

        System.out.println(podID);
         
     }
}
