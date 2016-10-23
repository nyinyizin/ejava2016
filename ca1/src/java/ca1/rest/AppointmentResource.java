/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.rest;

import ca1.business.AppointmentBean;
import ca1.model.Appointment;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nyinyizin
 */

@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
      @EJB private AppointmentBean appointmentBean;
    
    @GET
    @Path("/{email}")
    public Response getAppointment(@PathParam("email") String email){
        
        List<Appointment> appointments=appointmentBean.findAllAppointment(email);
        JsonArrayBuilder appJsonArrayBuilder=Json.createArrayBuilder();
        for(Appointment app:appointments){
            appJsonArrayBuilder.add(Json.createObjectBuilder()
                    .add("appointmentId",app.getAppointmentId())
                    .add("dateTime", app.getApptDate().getTime())
                    .add("description", app.getDescription())
                    .add("personId", app.getPeople().getPeopleId())
                    .build()
            );
        }
        return (Response.status(Response.Status.CREATED).entity(appJsonArrayBuilder.build()).build());
    }
    
}
