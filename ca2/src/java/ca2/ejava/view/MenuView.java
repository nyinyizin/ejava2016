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
import java.io.IOException;
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

/**
 *
 * @author XYZzzzz
 */
@RequestScoped
@Named("menuView")
public class MenuView {
    
    @EJB private MenuBean menuBean;
    @EJB private UserBean userBean;
    
    private String userId;
    private String title;
    private String content;
    private Category category;
    private Note note;
    private List<Note> notelist = new LinkedList<>();
    
    public void init(){
        System.out.println("userId ="+userId);
        notelist = menuBean.getNotesByUser(userId);
    }
    
    public Category[] getCategories(){
        return Category.values();
    }
    
    public String createNote(){
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
        notelist = menuBean.getNotesByUser(userId);
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage successful = new FacesMessage("Note created successfully!");
        context.addMessage("menuForm=errorCreate", successful);
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
