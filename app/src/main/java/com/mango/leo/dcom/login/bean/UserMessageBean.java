package com.mango.leo.dcom.login.bean;

/**
 * Created by admin on 2018/6/25.
 */

public class UserMessageBean {

    /**
     * id : 96
     * tenantId : 42
     * realName : ??
     * username : admin@mango
     * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjk2LCJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1Mjk4OTMyNDI1ODMsImV4cCI6MTUzMDQ5ODA0Mn0.aFaq4EWndaf0wEFggGH0EV3a9b9XQapx4pM2jgp_VhDWzWYBp5h-UWG7qvuNAhgpftJoWvxsvRStsHxwOFeQGA
     * status : null
     * roleName : admin
     * phone :
     * displayPicture : null
     */

    private int id;
    private int tenantId;
    private String realName;
    private String username;
    private String token;
    private Object status;
    private String roleName;
    private String phone;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getDisplayPicture() {
        return displayPicture;
    }

    public void setDisplayPicture(Object displayPicture) {
        this.displayPicture = displayPicture;
    }
}
