package com.feedr.models;

public class UserModel {
    private String username;
    private String phone;
    private String type;


    public UserModel(String username, String phone,String type){
        this.username = username;
        this.phone = phone;
        this.type = type;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
