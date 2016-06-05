/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.model;


import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class RoleDetailsModel {
    
    @JsonProperty("roleId")
    private Integer roleId;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("permission")
    private String permissions;
    
    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("typeId")
    private Integer typeId;
    
    @JsonProperty("descr")
    private String descr;
    
    @JsonProperty("users")
    private List<User> users;

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the permissions
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the typeId
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
}
