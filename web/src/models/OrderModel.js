export default class OrderModel {
    constructor(orderId, receiver,address,tips,orderTime,
                phone,restaurant,foodsMap, delivered, canceled, sender,
                cancelReason) {
        this.orderId = orderId;
        this.receiver = receiver;
        this.address = address;
        this.tips = tips;
        this.orderTime = orderTime;
        this.phone = phone;
        this.restaurant = restaurant;
        this.foodsMap = foodsMap;
        this.delivered = delivered;
        this.canceled = canceled;
        this.sender = sender;
        this.cancelReason = cancelReason;
    }
}