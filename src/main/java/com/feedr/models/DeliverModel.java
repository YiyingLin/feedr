package com.feedr.models;
import java.sql.Timestamp;

public class DeliverModel {
    private int order_id;
    private Double final_tip;
    private Double final_total_cost;
    private Timestamp delivered_time;

    public DeliverModel(int order_id, Double final_tip, Double final_total_cost,
                        Timestamp delivered_time){
        this.order_id = order_id;
        this.final_tip = final_tip;
        this.final_total_cost = final_total_cost;
        this.delivered_time =delivered_time;
    }

    public int getOrder_id(){
        return order_id;
    }

    public Double getFinal_tip() {
        return final_tip;
    }

    public void setFinal_tip(Double final_tip) {
        this.final_tip = final_tip;
    }

    public Double getFinal_total_cost() {
        return final_total_cost;
    }

    public void setFinal_total_cost(Double final_total_cost) {
        this.final_total_cost = final_total_cost;
    }

    public Timestamp getDelivered_time() {
        return delivered_time;
    }

    public void setDelivered_time(Timestamp delivered_time) {
        this.delivered_time = delivered_time;
    }
}
