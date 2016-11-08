/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private GroupPK groupPk;

    public GroupPK getGroupPk() {
        return groupPk;
    }

    public void setGroupPk(GroupPK groupPk) {
        this.groupPk = groupPk;
    }

}
