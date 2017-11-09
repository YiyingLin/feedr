package com.feedr.models;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class OrderModel {

        private int order_id;
        private String sender_name;
        private String receiver_name;
        private String restaurant_name;
        private double order_cost;
        private double deliver_tip;
        private Timestamp order_time;
        private Timestamp deadline;
        private String delivery_location;

        public OrderModel(int order_id, String sender_name, String receiver_name, String restaurant_name,
                          double order_cost, double deliver_tip, Timestamp order_time, Timestamp deadline, String delivery_location){
            this.order_id = order_id;
            this.sender_name = sender_name;
            this.receiver_name = receiver_name;
            this.restaurant_name = restaurant_name;
            this.order_cost = order_cost;
            this.deliver_tip = deliver_tip;
            this.order_time = order_time;
            this.deadline = deadline;
            this.delivery_location = delivery_location;
        }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public double getOrder_cost() {
        return order_cost;
    }

    public void setOrder_cost(double order_cost) {
        this.order_cost = order_cost;
    }

    public double getDeliver_tip() {
        return deliver_tip;
    }

    public void setDeliver_tip(double deliver_tip) {
        this.deliver_tip = deliver_tip;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getDelivery_location() {
        return delivery_location;
    }

    public void setDelivery_location(String delivery_location) {
        this.delivery_location = delivery_location;
    }




}
