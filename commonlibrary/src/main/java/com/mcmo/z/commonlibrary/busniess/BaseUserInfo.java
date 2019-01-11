package com.mcmo.z.commonlibrary.busniess;

public class BaseUserInfo {
    private float id;
    private String username;
    private String icon;
    private String email;
    private float type;

    public BaseUserInfo() {
    }

    public BaseUserInfo(float id, String username, String icon, String email, float type) {
        this.id = id;
        this.username = username;
        this.icon = icon;
        this.email = email;
        this.type = type;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BaseUserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", icon='" + icon + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
