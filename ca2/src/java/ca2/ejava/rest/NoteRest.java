/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.rest;

import ca2.ejava.business.NoteBean;
import ca2.ejava.model.Note;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author zinnn
 */
@RequestScoped
@Path("/note")
public class NoteRest {
    @EJB
    private NoteBean noteBean;
    
    @GET
    @Path("/{category}")
    @Produces("appliation/json")
    public Response getNote(@PathParam("category")String category){
        Response resp;
         System.out.println("before rest"+ category);
        if(category.equalsIgnoreCase("ALL")){
                  System.out.println("condition rest");
            List<Note> nodeList=noteBean.getAllNote();
             if(!nodeList.isEmpty()){
                        JsonArrayBuilder appJsonArrayBuilder=Json.createArrayBuilder();
                        nodeList.stream().forEach((note) -> {
                            appJsonArrayBuilder.add(Json.createObjectBuilder()
                                .add("title",note.getTitle())
                                .add("dateTime",note.getPostDate().getTime())
                                .add("user", note.getUser().getUserId())
                                .add("category",note.getCategory().getDesc())
                                .add("content",note.getContent())
                                .build()
                        );
                    });
            resp=Response.status(Response.Status.OK).entity(appJsonArrayBuilder.build().toString()).build();
            return resp;
        }
        }else{
             List<Note> nodeList=noteBean.getNoteByCategory(category);
             System.out.println(nodeList.size());
             if(!nodeList.isEmpty()){
                        JsonArrayBuilder appJsonArrayBuilder=Json.createArrayBuilder();
                        nodeList.stream().forEach((note) -> {
                            appJsonArrayBuilder.add(Json.createObjectBuilder()
                                .add("title",note.getTitle())
                                .add("dateTime",note.getPostDate().getTime())
                                .add("user", note.getUser().getUserId())
                                .add("category",note.getCategory().getDesc())
                                .add("content",note.getContent())
                                .build()
                        );
                    });
            resp=Response.status(Response.Status.OK).entity(appJsonArrayBuilder.build().toString()).build();
            return resp;
        }
             
    }
         resp=Response.status(Response.Status.NO_CONTENT).build();
         return resp;
    }
}
