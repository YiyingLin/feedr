package com.feedr.models;

public class CountSenderModel {
    private int count;
    private int sender_rating;

    public CountSenderModel(int count, int sender_rating){
        this.count = count;
        this.sender_rating = sender_rating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSender_rating() {
        return sender_rating;
    }

    public void setSender_rating(int sender_rating) {
        this.sender_rating = sender_rating;
    }
}
