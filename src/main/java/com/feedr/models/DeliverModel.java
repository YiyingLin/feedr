package com.feedr.models;
import java.sql.Timestamp;

public class DeliverModel {
    private int orderId;
    private Double finalTip;
    private Double finalTotalCost;
    private Timestamp deliveredTime;

    public DeliverModel(int orderId, Double finalTip, Double finalTotalCost,
                        Timestamp deliveredTime){
        this.orderId = orderId;
        this.finalTip = finalTip;
        this.finalTotalCost = finalTotalCost;
        this.deliveredTime =deliveredTime;
    }

    public int getOrderId(){
        return orderId;
    }

    public Double getFinalTip() {
        return finalTip;
    }

    public void setFinalTip(Double finalTip) {
        this.finalTip = finalTip;
    }

    public Double getFinalTotalCost() {
        return finalTotalCost;
    }

    public void setFinalTotalCost(Double finalTotalCost) {
        this.finalTotalCost = finalTotalCost;
    }

    public Timestamp getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(Timestamp deliveredTime) {
        this.deliveredTime = deliveredTime;
    }
}
