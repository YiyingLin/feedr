package com.feedr.models;

public class FoodModel {
    private String restUsername;
    private String foodname;
    private Double price;
    private String type;
    private int quantity;

    public FoodModel(String restUsername, String foodname, Double price, String type){
        this.restUsername = restUsername;
        this.foodname = foodname;
        this.price = price;
        this.type = type;
    }

    public FoodModel(String restUsername, String foodname, Double price, String type, int quantity){
        this.restUsername = restUsername;
        this.foodname = foodname;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRestUsername(){
        return restUsername;
    }

    public void setRestUsername(String restUsername){
        this.restUsername = restUsername;
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
