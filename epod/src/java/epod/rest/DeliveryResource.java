/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.rest;

import epod.business.DeliveryBean;
import epod.business.PodBean;
import epod.model.Delivery;
import epod.model.Pod;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import epod.utils.TeamID;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
/**
 *
 * @author nyinyizin
 */
@RequestScoped
@Path("/items")
public class DeliveryResource {
   
    @EJB PodBean podBean;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDelivery(){
        // [TODO] Call Bean to get list and return json
        // Get TeamID from TeamID utils
        Response resp;
         List<Pod> deliveryList=podBean.getAllPod();
         System.out.println(deliveryList.size());
                if(!deliveryList.isEmpty()){
                        JsonArrayBuilder deliveryJsonBuilder=Json.createArrayBuilder();
                        deliveryList.stream().forEach((app) -> {
                            deliveryJsonBuilder.add(Json.createObjectBuilder()
                                .add("teamId",TeamID.TEAMID)
                                .add("podId",app.getPodId() )
                                .add("name", app.getDelivery().getName())
                                .add("address", app.getDelivery().getAddress())
                                .add("phone", app.getDelivery().getPhone())
                                .build()
                        );
                    });
                    resp=Response.status(Response.Status.OK).entity(deliveryJsonBuilder.build()).build();
                }else{
                    resp=Response.status(Response.Status.NO_CONTENT).build();
                }
        return resp;
    }
}
