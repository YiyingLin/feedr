package com.feedr.models;

public class SenderModel extends UserModel{
    private int sender_rating;
    private String location;

    public SenderModel(String username, String phone, int sender_rating,
                       String location) {
        super(username, phone);
        this.sender_rating = sender_rating;
        this.location = location;
    }

    public int getSender_rating(){ return sender_rating;}

    public void setSender_rating(int sender_rating){ this.sender_rating = sender_rating;}

    public String getLocation(){ return location;}

    public void setLocation(String location){ this.location = location;}
}
