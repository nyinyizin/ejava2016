/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.websocket;

import ca2.ejava.business.NoteBean;
import ca2.ejava.model.Note;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
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
    @Inject private NoteSession notesession;
    private final Map<String, List<Session>> categories=new HashMap<>();
    
    @OnMessage
    public void onMessage(String note,Session session,@PathParam("category") String category) throws IOException,InterruptedException{
      notesession.lock(()->{
          notesession.broadcast(category, note);
      });
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam("category") String category){
        System.out.println("Client connected"+ session.getId()+"   "+category);
           notesession.lock(()->{
               notesession.add(category, session);
           });  
      
    }
    
    @OnClose
    public void onClose(Session session, @PathParam("category") String category){
        notesession.lock(()->{
          notesession.remove(category, session);
      });
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("---- error ----");
    }
}
