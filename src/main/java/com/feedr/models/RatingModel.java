package com.feedr.models;

public class RatingModel {
    private int order_id;
    private int rate_sender;
    private int rate_rest;
    private String comment_sender;
    private String comment_rest;

    public RatingModel(int order_id, int rate_sender, int rate_rest, String comment_sender,
                       String comment_rest){
        this.order_id = order_id;
        this.rate_sender = rate_sender;
        this.rate_rest = rate_rest;
        this.comment_sender = comment_sender;
        this.comment_rest = comment_rest;
    }

    public int getOrder_id(){
        return order_id;
    }

    public int getRate_sender(){
        return rate_sender;
    }

    public void setRate_sender(int rate_sender){
        this.rate_sender = rate_sender;
    }

    public int getRate_rest(){
        return rate_rest;
    }

    public void setRate_rest(int rate_rest){
        this.rate_rest = rate_rest;
    }

    public String getComment_sender(){
        return comment_sender;
    }

    public void setComment_sender(String comment_sender){
        this.comment_sender = comment_sender;
    }

    public String getComment_rest() {
        return comment_rest;
    }

    public void setComment_rest(String comment_rest){
        this.comment_rest = comment_rest;
    }
}
