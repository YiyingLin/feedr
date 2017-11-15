package com.feedr.models;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class OrderModel {

    private int orderID;
    private String sender;
    private String receiver;
    private String restaurant;
    private double orderCost;
    private double deliverTip;
    private Timestamp orderTime;
    private Timestamp deadline;
    private String location;
    private String phone;

    public OrderModel(int orderID, String sender, String receiver, String restaurant,
                      double orderCost, double deliverTip, Timestamp orderTime, Timestamp deadline,
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
                      double orderCost, double deliverTip, Timestamp orderTime, Timestamp deadline,
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

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
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
