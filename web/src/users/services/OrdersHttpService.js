import axios from 'axios';
import OrderModel from "../../models/OrderModel";
import FoodModel from "../../models/FoodModel";
const Cookie = require('js-cookie');

export function getPublicOrders() {
    return new Promise(function (resolve, reject) {
        axios.get("http://localhost:8080/publicOrders")
            .then(function (response) {
                let rawOrderList = response.data["orders"];
                let orderList = rawOrderList.map(rawOrder => {
                    let rawFoodList = rawOrder["foodMap"];
                    let foodList = rawFoodList.map(
                        rawFood => ({
                            food: new FoodModel(
                                rawFood["foodname"],
                                rawFood["price"],
                                rawFood["type"]
                            ),
                            quantity: rawFood["quantity"]
                        })
                    );
                    return new OrderModel(
                        rawOrder["order_id"],
                        rawOrder["receiver_name"],
                        rawOrder["delivery_location"],
                        rawOrder["deliver_tip"],
                        rawOrder["deadline"],
                        rawOrder["phone"],
                        rawOrder["restaurant_name"],
                        foodList
                    );
                });
                resolve(orderList);
            })
            .catch(function (err) {
               reject(err);
            });
    });
}

export function getPrivateOrders(isSender) {
    let urlPart = isSender? 'senderPrivateOrders': 'receiverPrivateOrders';
    return new Promise(function (resolve, reject) {
        axios.get(`http://localhost:8080/${urlPart}/${Cookie.get('username')}`)
            .then(function (response) {
                let rawOrderList = response.data["orders"];
                let orderList = rawOrderList.map(rawOrder => {
                    let rawFoodList = rawOrder["foodMap"];
                    let foodList = rawFoodList.map(
                        rawFood => ({
                            food: new FoodModel(
                                rawFood["foodname"],
                                rawFood["price"],
                                rawFood["type"]
                            ),
                            quantity: rawFood["quantity"]
                        })
                    );
                    return new OrderModel(
                        rawOrder["order_id"],
                        rawOrder["receiver_name"],
                        rawOrder["delivery_location"],
                        rawOrder["deliver_tip"],
                        rawOrder["deadline"],
                        rawOrder["phone"],
                        rawOrder["restaurant_name"],
                        foodList,
                        rawOrder["delivered"],
                        rawOrder["canceled"],
                        rawOrder["sender_name"]
                    );
                });
                resolve(orderList);
            })
            .catch(function (err) {
                reject(err);
            });
    });
}

export function cancelOrder(orderId, reason) {
    return new Promise(function (resolve, reject) {
        axios.get(`http://localhost:8080/cancelOrders?orderId=${orderId}&username=${Cookie.get('username')}&reason=${reason}`)
            .then(function (response) {
                resolve();
            })
            .catch(function (err) {
                reject(err);
            });
    });
}

export function createNewOrder(order) {
    return new Promise(function (resolve, reject) {
        axios.post(`http://localhost:8080/createNewOrder`, order)
            .then(function (response) {
                console.log(response);
                resolve();
            })
            .catch(function (err) {
                reject(err);
            });
    });
}