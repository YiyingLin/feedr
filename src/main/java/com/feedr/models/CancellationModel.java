package com.feedr.models;

import java.sql.Timestamp;

public class CancellationModel {
    private int orderId;
    private String username;
    private Timestamp cancelTime; //auto insert in query, when make a cancellation
    private String reason;

    public CancellationModel(int orderId, String username, Timestamp cancelTime, String reason){
        this.orderId = orderId;
        this.username = username;
        this.cancelTime = cancelTime;
        this.reason = reason;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Timestamp cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
