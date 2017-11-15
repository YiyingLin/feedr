package com.feedr.models;

public class RestaurantModel extends UserModel{

    private String restName;
    private int restRating;
    private String location;

    public RestaurantModel(String username, String phone, String restName,
                           int restRating, String location) {
        super(username, phone, "RESTAURANT");
        this.restName = restName;
        this.restRating = restRating;
        this.location = location;
    }

    public String getRestName(){ return restName;}

    public void setRestName(String restName){ this.restName = restName;}

    public int getRestRating(){ return restRating;}

    public void setRestRating(int restRating){ this.restRating = restRating;}

    public String getLocation(){ return location;}

    public void setLocation(String location){ this.location = location;}
}
