/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.ejava.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class GroupPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "userid")
    private String userId;

    @NotNull
    @Column(name = "groupid")
    private String groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return (false);
        }
        if (!(o instanceof GroupPK)) {
            return (false);
        }
        GroupPK that = (GroupPK) o;
        return (that.userId.equals(userId) && that.groupId.equals(groupId));
    }

    @Override
    public int hashCode() {
        return ((userId + groupId).hashCode());
    }
}
