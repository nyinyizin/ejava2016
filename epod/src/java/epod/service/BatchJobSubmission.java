/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.service;

import epod.model.Delivery;
import epod.model.Pod;
import epod.utils.TeamID;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import java.io.File;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author XYZzzzz
 */
@Stateless
public class BatchJobSubmission implements Runnable{
    private static final Logger logger = Logger.getLogger(BatchJobSubmission.class.getName());
    private Delivery delivery;
    
    @Override
    public void run() {
        jobSubmission(getDelivery());
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void jobSubmission(Delivery delivery){
        Client client = ClientBuilder.newBuilder()
				.register(MultiPartFeature.class)
				.build();

        MultiPart part = new MultiPart();
    
        FileDataBodyPart imgPart = new FileDataBodyPart("image", 
				new File("D:\\NUS\\EJava\\ca3.png"),
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
        imgPart.setContentDisposition(
				FormDataContentDisposition.name("image")
				.fileName("ca3.png").build());
    
        MultiPart formData = new FormDataMultiPart()
                                .field("teamId",TeamID.TEAMID, MediaType.TEXT_PLAIN_TYPE)
				.field("podId", "abc123", MediaType.TEXT_PLAIN_TYPE)
                                .field("callback","http://10.10.12.53:8080/epod/callback", MediaType.TEXT_PLAIN_TYPE)
				.field("note", "a message", MediaType.TEXT_PLAIN_TYPE)
				.bodyPart(imgPart);
        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        
        WebTarget target = client.target("10.10.0.48:8080/epod/upload");
		      Invocation.Builder inv = target.request();
                      
        Response callResp = inv.post(Entity.entity(inv, MediaType.WILDCARD_TYPE).entity(formData, formData.getMediaType()));           
    } 

    /**
     * @return the delivery
     */
    public Delivery getDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }


}
