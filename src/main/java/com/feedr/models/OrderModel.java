package com.feedr.models;

public class OrderModel {

    private int orderID;
    private String sender;
    private String receiver;
    private String restaurant;
    private double orderCost;
    private double deliverTip;
    private String orderTime; // just use the date formatted string in java
    private String deadline; // just use the date formatted string in java
    private String location;
    private String phone;

    public OrderModel(int orderID, String sender, String receiver, String restaurant,
                      double orderCost, double deliverTip, String orderTime, String deadline,
                      String location, String phone){
        this.orderID = orderID;
        this.sender = sender;
        this.receiver = receiver;
        this.restaurant = restaurant;
        this.orderCost = orderCost;
        this.deliverTip = deliverTip;
        this.orderTime = orderTime;
        this.deadline = deadline;
        this.location = location;
        this.phone = phone;
    }

    // Constructor without sender
    public OrderModel(int orderID, String receiver, String restaurant,
                      double orderCost, double deliverTip, String orderTime, String deadline,
                      String location, String phone){
        this.orderID = orderID;
        this.receiver = receiver;
        this.restaurant = restaurant;
        this.orderCost = orderCost;
        this.deliverTip = deliverTip;
        this.orderTime = orderTime;
        this.deadline = deadline;
        this.location = location;
        this.phone = phone;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }

    public double getDeliverTip() {
        return deliverTip;
    }

    public void setDeliverTip(double deliverTip) {
        this.deliverTip = deliverTip;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
