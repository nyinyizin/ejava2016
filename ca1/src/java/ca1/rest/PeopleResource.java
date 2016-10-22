/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.rest;

import ca1.business.PeopleBean;
import ca1.model.People;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/people")
public class PeopleResource {
    
    @EJB private PeopleBean peopleBean;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPeople(MultivaluedMap<String,String> formData){
        String name= formData.getFirst("name");
        String email= formData.getFirst("email");
        
        People people=new People();
        people.setEmail(email);
        people.setName(name);
        
        peopleBean.save(people);
   
        
        JsonObject json = Json.createObjectBuilder()
                .add("name", people.getEmail())
                .add("email", people.getName())
                .build();

        System.out.println(String.format(">> name: %s, email: %s", name,email));

        return (Response.status(Response.Status.CREATED)
                .entity(json)
                .build());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPeople(@QueryParam("email") String email){
            
           System.out.println("email >>>>>>>>"+email);
           return (Response.status(Response.Status.CREATED).build());
    }
}
