/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author nyinyizin
 */
@ClientEndpoint
public class NoteClient {
    @OnOpen
    public void open(Session session){
    }
    
    @OnMessage
    public void message(String message){
        
    }
}
