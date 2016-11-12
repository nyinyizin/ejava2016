/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import epod.utils.TeamID;
/**
 *
 * @author nyinyizin
 */
@RequestScoped
@Path("/items")
public class DeliveryResource {
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDelivery(){
        // [TODO] Call Bean to get list and return json
        // [TODO] Get TeamID from TeamID utils
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
