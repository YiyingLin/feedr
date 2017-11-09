package com.feedr.models;

import java.sql.Timestamp;

public class CancellationModel {
    private int order_id;
    private String username;
    private Timestamp cancelTime; //auto insert in query, when make a cancellation
    private String reason;

    public CancellationModel(int order_id, String username, Timestamp cancelTime, String reason){
        this.order_id = order_id;
        this.username = username;
        this.cancelTime = cancelTime;
        this.reason = reason;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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
