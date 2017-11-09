package com.feedr.models;

public class RestaurantModel extends UserModel{

    private String restaurant_name;
    private int restaurant_rating;
    private String location;

    public RestaurantModel(String username, String phone, String restaurant_name,
                           int restaurant_rating, String location) {
        super(username, phone);
        this.restaurant_name = restaurant_name;
        this.restaurant_rating = restaurant_rating;
        this.location = location;
    }

    public String getRes_name(){ return restaurant_name;}

    public void setRes_name(String restaurant_name){ this.restaurant_name = restaurant_name;}

    public int getRes_rating(){ return restaurant_rating;}

    public void setRes_rating(int restaurant_rating){ this.restaurant_rating = restaurant_rating;}

    public String getLocation(){ return location;}

    public void setLocation(String location){ this.location = location;}
}
