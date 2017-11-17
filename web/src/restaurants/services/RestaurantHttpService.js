import axios from 'axios';
import OrderModel from "../../models/OrderModel";
import FoodModel from "../../models/FoodModel";
const Cookie = require('js-cookie');

export function getRestaurantOrders() {
    return new Promise(function (resolve, reject) {
        axios.get(`http://localhost:8080/restaurants/${Cookie.get('username')}/orders`)
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