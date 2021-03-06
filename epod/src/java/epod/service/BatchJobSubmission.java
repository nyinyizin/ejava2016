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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
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
    private Pod pod;
    
    @Override
    public void run() {
        try {
            jobSubmission(this.getPod());
        } catch (IOException ex) {
            Logger.getLogger(BatchJobSubmission.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void jobSubmission(Pod pod) throws FileNotFoundException, IOException{
        Client client = ClientBuilder.newBuilder()
				.register(MultiPartFeature.class)
				.build();

        MultiPart part = new MultiPart();
        File file=new File("a.png");
        FileOutputStream fileOuputStream =
                  new FileOutputStream(file);
	    fileOuputStream.write(pod.getImage());
            fileOuputStream.flush();
	    fileOuputStream.close();
        FileDataBodyPart imgPart = new FileDataBodyPart("image", file,
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
        imgPart.setContentDisposition(
				FormDataContentDisposition.name("image")
				.fileName("ca3.png").build());
        
        MultiPart formData = new FormDataMultiPart()
                                .field("teamId",TeamID.TEAMID, MediaType.TEXT_PLAIN_TYPE)
				.field("podId", String.valueOf(pod.getPodId()), MediaType.TEXT_PLAIN_TYPE)
                                .field("callback","http://10.10.24.210:8080/epod/callback", MediaType.TEXT_PLAIN_TYPE)
				.field("note",pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
				.bodyPart(imgPart);
        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        
        WebTarget target = client.target("http://10.10.0.48:8080/epod/upload");
		      Invocation.Builder inv = target.request();
                      
        Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));           
    } 

    /**
     * @return the pod
     */
    public Pod getPod() {
        return pod;
    }

    /**
     * @param pod the delivery to set
     */
    public void setPod(Pod pod) {
        this.pod = pod;
    }


}
