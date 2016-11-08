/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.websocket;

import ca2.ejava.business.NoteBean;
import ca2.ejava.model.Note;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/notesocket/{category}")
@RequestScoped
public class NoteSocket {
    
    @Inject private NoteBean noteBean;
    
    private final Map<String, List<Session>> categories=new HashMap<>();
    
    @OnMessage
    public String onMessage(String note,Session session) throws IOException,InterruptedException{
        return note;
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam("category") String category){
        System.out.println("Client connected"+ session.getId());
        List<Session> allSessions=categories.computeIfAbsent(category, s->new LinkedList<>());
        allSessions.add(session);
      
    }
    
    public List<Note> loadExistingNotes(String category){
 
        if(category.equalsIgnoreCase("ALL")){
            return noteBean.getAllNote();
        }else{
            return noteBean.getNoteByCategory(category);
        }
        
        
    }
    public void broadcastExistingNote(String category){
        List<Note> noteList=loadExistingNotes(category);
        if(!noteList.isEmpty()){
            JsonArrayBuilder jsonBuilder=Json.createArrayBuilder();
             noteList.stream().forEach((note) -> {
                            jsonBuilder.add(Json.createObjectBuilder()
                                .add("title",note.getTitle())
                                .add("dateTime", note.getPostDate().getTime())
                                .add("user", note.getUser().getUserId())
                                .add("category", note.getCategory().getDesc())
                                .add("content", note.getContent())
                                .build()
                        );
            });
             
            categories.get(category).stream().forEach(s->{
            try{
                s.getBasicRemote().sendText(jsonBuilder.build().toString());
            }catch(IOException ex){
                System.out.println(" error in broadcast ");
            }
        }); 
             
        }
    }
    public void broadcast(String category, String note){
        categories.get(category).stream().forEach(s->{
            try{
                s.getBasicRemote().sendText(note);
            }catch(IOException ex){
                System.out.println(" error in broadcast ");
            }
        });
    }
    
    @OnClose
    public void onClose(Session session, @PathParam("category") String category){
        categories.get(category).remove(session);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("---- error ----");
    }
}
