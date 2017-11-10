package com.feedr.models;

public class SenderModel extends UserModel{
    private int senderRating;
    private String location;

    public SenderModel(String username, String phone, int senderRating,
                       String location) {
        super(username, phone);
        this.senderRating = senderRating;
        this.location = location;
    }

    public int getSenderRating(){ return senderRating;}

    public void setSenderRating(int senderRating){ this.senderRating = senderRating;}

    public String getLocation(){ return location;}

    public void setLocation(String location){ this.location = location;}
}
