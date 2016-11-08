/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.view;

import ca2.ejava.business.GroupBean;
import ca2.ejava.business.UserBean;
import ca2.ejava.model.GroupPK;
import ca2.ejava.model.Groups;
import ca2.ejava.model.User;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;

@RequestScoped
@Named("registerView")
public class RegisterView {

    @EJB
    private UserBean userBean;
    @EJB
    private GroupBean groupBean;

    private String username;
    private String password;
    private String rePassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String registerUser() {

        if (!password.equals(rePassword)) {
            
            FacesMessage msg = new FacesMessage("Passwors not matching!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return ("/faces/register.xhtml");
            
        } else {

            User newUser = new User();
            newUser.setUserId(username);
            newUser.setPassword(password);
            
            Groups newGroup = new Groups();
            GroupPK newPK = new GroupPK();
            newPK.setGroupId("authenticated");
            newPK.setUserId(username);
            newGroup.setGroupPk(newPK);
            

            try {
                userBean.save(newUser);
                groupBean.save(newGroup);
            } catch (PersistenceException ex) {
                FacesMessage msg = new FacesMessage("Registration failed");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return ("/faces/register.xhtml");
            } catch (EJBException e){
                FacesMessage msg = new FacesMessage("Registration failed");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return ("/faces/register.xhtml");
            }

            return ("/faces/login.xhtml");
        }
    }
}
