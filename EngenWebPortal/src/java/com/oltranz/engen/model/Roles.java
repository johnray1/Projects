/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engen.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class Roles {
    
    @JsonProperty("roleId")
    private int roleId;
    
    @JsonProperty("roleName")
    private String roleName;
    
    @JsonProperty("roleTypeName")
    private String roleTypeName;
    
    @JsonProperty("descr")
    private String descr;

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the roleTypeName
     */
    public String getRoleTypeName() {
        return roleTypeName;
    }

    /**
     * @param roleTypeName the roleTypeName to set
     */
    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
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
    
    
    
}
