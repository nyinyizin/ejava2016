/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.view;


import ca2.ejava.business.UserBean;
import ca2.ejava.model.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;

    
    
@ViewScoped
@Named
public class RegisterView implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB private UserBean userBean;
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String registerUser() {

        User newUser = new User();
        newUser.setUserId(username);
        newUser.setPassword(password);
        
        try {
            userBean.save(newUser);
        } catch (PersistenceException ex) {
            FacesMessage msg = new FacesMessage("Registration failed");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return ("/faces/register.xhtml");
        }

        return ("/faces/login.xhtml");
    }
}