package com.feedr.models;

public class UserRatingModel {
    private String username;
    private int avgRating;

    public UserRatingModel(String username, int avgRating){
        this.username = username;
        this.avgRating = avgRating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }
}
