package com.feedr.models;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class OrderModel {

        private int orderID;
        private String senderName;
        private String receiverName;
        private String restaurantName;
        private double orderCost;
        private double deliverTip;
        private Timestamp orderTime;
        private Timestamp deadline;
        private String deliveryLocation;

        public OrderModel(int orderID, String senderName, String receiverName, String restaurantName,
                          double orderCost, double deliverTip, Timestamp orderTime, Timestamp deadline, String deliveryLocation){
            this.orderID = orderID;
            this.senderName = senderName;
            this.receiverName = receiverName;
            this.restaurantName = restaurantName;
            this.orderCost = orderCost;
            this.deliverTip = deliverTip;
            this.orderTime = orderTime;
            this.deadline = deadline;
            this.deliveryLocation = deliveryLocation;
        }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }




}
