package com.feedr.models;

public class RatingModel {
    private int orderId;
    private int rateSender;
    private int rateRest;
    private String commentSender;
    private String commentRest;

    public RatingModel(int orderId, int rateSender, int rateRest, String commentSender,
                       String commentRest){
        this.orderId = orderId;
        this.rateSender = rateSender;
        this.rateRest = rateRest;
        this.commentSender = commentSender;
        this.commentRest = commentRest;
    }

    public int getOrderId(){
        return orderId;
    }

    public int getRateSender(){
        return rateSender;
    }

    public void setRateSender(int rateSender){
        this.rateSender = rateSender;
    }

    public int getRateRest(){
        return rateRest;
    }

    public void setRateRest(int rateRest){
        this.rateRest = rateRest;
    }

    public String getCommentSender(){
        return commentSender;
    }

    public void setCommentSender(String commentSender){
        this.commentSender = commentSender;
    }

    public String getCommentRest() {
        return commentRest;
    }

    public void setCommentRest(String commentRest){
        this.commentRest = commentRest;
    }
}
