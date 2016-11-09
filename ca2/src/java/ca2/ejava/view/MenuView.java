/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.view;

import ca2.ejava.business.MenuBean;
import ca2.ejava.business.UserBean;
import ca2.ejava.model.Category;
import ca2.ejava.model.Note;
import ca2.ejava.model.User;
import ca2.ejava.websocket.NoteClient;
import ca2.ejava.websocket.NoteSession;
import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.json.Json;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author XYZzzzz
 */
@RequestScoped
@Named("menuView")
public class MenuView {
    
    @EJB private MenuBean menuBean;
    @EJB private UserBean userBean;
    
    private String userId="xykek";
    private String title;
    private String content;
    private Category category;
    private Note note;
    private List<Note> notelist = new LinkedList<>();
    
    @PostConstruct
    public void init(){
        this.findAllNotes();
    }
    
    public Category[] getCategories(){
        return Category.values();
    }
    
    public String createNote() throws DeploymentException, IOException{
        Optional<User> user = userBean.find(userId);
        if(!user.isPresent()){
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage error = new FacesMessage("Invalid User");
            context.addMessage("menuForm=errorCreate", error);
            return null;
        }
        note = new Note();
        note.setUser(user.get());
        note.setCategory(category);
        note.setContent(content);
        note.setTitle(title);
        java.util.Date today = new java.util.Date();
        Timestamp date = new java.sql.Timestamp(today.getTime());
        note.setPostDate(date);
        menuBean.save(note);
        notelist = menuBean.getAllNoteByUser(userId);
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage successful = new FacesMessage("Note created successfully!");
        context.addMessage("menuForm=errorCreate", successful);
        
        String jsonNote=Json.createObjectBuilder()
				.add("title",note.getTitle())
                                .add("dateTime", note.getPostDate().getTime())
                                .add("user", note.getUser().getUserId())
                                .add("category", note.getCategory().getDesc())
                                .add("content", note.getContent()).build()
				.toString();
        final WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
        Session session=webSocketContainer.connectToServer(NoteClient.class, URI.create("ws://localhost:8080/ca2/notesocket/"+category));
        session.getBasicRemote().sendText(jsonNote);
        session.close();
        session=webSocketContainer.connectToServer(NoteClient.class, URI.create("ws://localhost:8080/ca2/notesocket/ALL"));
        session.getBasicRemote().sendText(jsonNote);
        session.close();
        return null;
    }
    
    public void findAllNotes(){
        notelist = menuBean.getAllNoteByUser(userId);
    }
    

    /**
     * @return the notelist
     */
    public List<Note> getNotelist() {
        return notelist;
    }

    /**
     * @param notelist the notelist to set
     */
    public void setNotelist(List<Note> notelist) {
        this.notelist = notelist;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the note
     */
    public Note getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(Note note) {
        this.note = note;
    }
    
    
    
}
