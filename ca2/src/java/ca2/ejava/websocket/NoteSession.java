/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

/**
 *
 * @author nyinyizin
 */
@ApplicationScoped
public class NoteSession {
    private final Lock lock=new ReentrantLock();
    private final Map<String, List<Session>> categories=new HashMap<>();
    
    public void add(String category, Session session){
        List<Session> allSessions=categories.computeIfAbsent(category, s->new LinkedList<>());
        allSessions.add(session);
    }
     public void broadcast(String category, String note){

   
        categories.get(category).stream().forEach(s->{
            try{
                 System.out.println("in the broadcast "+note+" "+s.getId());
                s.getBasicRemote().sendText(note);
            }catch(IOException ex){
                System.out.println(" error in broadcast ");
            }
        });
    }
     
     public void remove(String category, Session session){
         categories.get(category).remove(session);
          System.out.println("---- removed ----");
     }
    public void lock(Runnable block){
        lock.lock();
        try{
            block.run();
        }finally{
            lock.unlock();
        }
    }
}
