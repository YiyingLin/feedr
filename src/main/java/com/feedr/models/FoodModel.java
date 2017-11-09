package com.feedr.models;

public class FoodModel {
    private String res_username;
    private String foodname;
    private Double price;
    private String type;

    public FoodModel(String res_username, String foodname, Double price, String type){
        this.res_username = res_username;
        this.foodname = foodname;
        this.price = price;
        this.type = type;
    }

    public String getRes_username(){
        return res_username;
    }

    public void setRes_username(String res_username){
        this.res_username = res_username;
    }

    public String getFoodname(){
        return foodname;
    }

    public void setFoodname(String foodname){
        this.foodname = foodname;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
