package com.mango.leo.dcom.event.bean;

/**
 * Created by admin on 2018/7/20.
 */

public class PeopleBean {

    /**
     * id : 96
     * tenantId : 42
     * realName : Ali
     * username : null
     * token : null
     * status : null
     * roleName : null
     * phone : null
     * displayPicture : null
     */

    private int id;
    private int tenantId;
    private String realName;
    private Object username;
    private Object token;
    private Object status;
    private Object roleName;
    private Object phone;
    private Object displayPicture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getRoleName() {
        return roleName;
    }

    public void setRoleName(Object roleName) {
        this.roleName = roleName;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getDisplayPicture() {
        return displayPicture;
    }

    public void setDisplayPicture(Object displayPicture) {
        this.displayPicture = displayPicture;
    }
}
