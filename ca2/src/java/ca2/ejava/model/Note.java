/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.model;

import java.io.Serializable;
import java.sql.Date;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nyinyizin
 */
@RequestScoped
@Table(name="note")
@Named("note")
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
        private Date postDate;

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
        
}
