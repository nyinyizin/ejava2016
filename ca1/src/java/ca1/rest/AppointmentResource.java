/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.rest;

import javax.enterprise.context.RequestScoped;
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
    
    @GET
    @Path("/{email}")
    public Response getAppointment(@PathParam("email") String email){
            
           System.out.println("email >>>>>>>>"+email);
           return (Response.status(Response.Status.CREATED).build());
    }
    
}
