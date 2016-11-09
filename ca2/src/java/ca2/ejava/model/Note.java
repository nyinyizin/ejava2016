/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nyinyizin
 */
@NamedQuery(name="note.findNoteByUserId",
       query="Select n from Note n join n.user as u where u.userId=:userId order by n.postDate desc")

@Table(name="note")
@Entity
public class Note implements Serializable{
        private static long serialVersionUID = 1L;
        @Id
        @NotNull
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "noteid")
        private int noteId;
        
        @NotNull
        @Column(name= "title")
        private String title;
        
        @NotNull
        @Column(name="category")
        @Enumerated(EnumType.STRING)
        private Category category;
        
        @NotNull
        @Column(name="content")
        private String content;
        
        @NotNull
        @Column(name="postdate")
        @Temporal(TemporalType.TIMESTAMP)
        private Date postDate;
        
        @NotNull
        @ManyToOne @JoinColumn(name="userid", referencedColumnName="userid")
        private User user;
        

    /**
     * @return the noteId
     */
    public int getNoteId() {
        return noteId;
    }

    /**
     * @param noteId the noteId to set
     */
    public void setNoteId(int noteId) {
        this.noteId = noteId;
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
     * @return the postDate
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * @param postDate the postDate to set
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
        
}
