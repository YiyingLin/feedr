export default class OrderModel {
    constructor(orderId, receiver,address,tips,orderTime,phone,restaurant,foods, delivered, canceled) {
        this.orderId = orderId;
        this.receiver = receiver;
        this.address = address;
        this.tips = tips;
        this.orderTime = orderTime;
        this.phone = phone;
        this.restaurant = restaurant;
        this.foods = foods;
        this.delivered = delivered;
        this.canceled = canceled;
    }
}