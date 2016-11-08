/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
    @Column(name = "userid")
    private String userId;

    @Column(name = "password")
    @Basic(optional = false)
    private String password;

    @OneToMany(mappedBy = "users")
    private ArrayList<Note> notelist;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Groups groups;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    /**
     * @return the notelist
     */
    public ArrayList<Note> getNotelist() {
        return notelist;
    }

}
