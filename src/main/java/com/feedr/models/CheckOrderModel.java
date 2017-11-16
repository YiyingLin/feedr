package com.feedr.models;

import java.sql.Timestamp;

public class CheckOrderModel extends OrderModel{

    private boolean isCancelled;
    private boolean isDelivered;
    private String reason;

    public CheckOrderModel(int orderID, String sender, String receiver, String restaurant, double orderCost,
                           double deliverTip, String orderTime, String deadline, String location,
                           String phone, boolean isCancelled, boolean isDelivered) {
        super(orderID, sender, receiver, restaurant, orderCost, deliverTip, orderTime, deadline, location, phone);
        this.isCancelled = isCancelled;
        this.isDelivered = isDelivered;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getReason(){
        return this.reason;
    }

    public void setReason(String reason){
        this.reason = reason;
    }
}
