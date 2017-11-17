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

export function restaurantGetItsMenu() {
    return new Promise(function (resolve, reject) {
        axios.get(`http://localhost:8080/restaurants/${Cookie.get('username')}/food`)
            .then(function (response) {
                let foodList = response.data["foodlist"];
                resolve(
                    foodList.map(rawFood => new FoodModel(rawFood["foodname"], rawFood["price"], rawFood["type"]))
                );
            })
            .catch(function (err) {
                reject(err);
            });
    });
}

export function restaurantCreateFood(foodModel) {
    return new Promise(function (resolve, reject) {
        axios.post(
            `http://localhost:8080/new/restaurants/food/`, {
                res_username: Cookie.get('username'),
                foodname: foodModel.foodname,
                price: foodModel.price,
                type: foodModel.foodType,
                quantity: -1
            })
            .then(function (response) {
                resolve();
            })
            .catch(function (err) {
                reject(err);
            });
    });
}

export function restaurantDeleteFood(foodName) {
    return new Promise(function (resolve, reject) {
        axios.delete(
            `http://localhost:8080/restaurants/${Cookie.get('username')}/food/${foodName}`)
            .then(function (response) {
                resolve();
            })
            .catch(function (err) {
                reject(err);
            });
    });
}
