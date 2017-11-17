import FoodModel from "../../models/FoodModel";
import axios from 'axios';

export async function getAllRestaurants() {
    return await new Promise(function (resolve, reject) {
        axios.get("http://localhost:8080/restaurants")
            .then(function (response) {
                resolve(response.data);
            })
            .catch(function (err) {
                reject(err);
            });
    });
}

export async function getAllFoodOfRestaurant(restaurant) {
    return await new Promise(function (resolve, reject) {
        axios.get(`http://localhost:8080/restaurants/${restaurant}/food`)
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