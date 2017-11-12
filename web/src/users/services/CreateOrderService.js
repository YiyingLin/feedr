import FoodModel from "../../models/FoodModel";

export async function getAllRestaurants() {
    return await Promise.resolve(['KFC','McDonald','Pho']);
}

export async function getAllFoodOfRestaurant(restaurant) {
    let foods;
    switch (restaurant) {
        case 'KFC':
            foods = [new FoodModel('Chicken wing',10, 'spicy'), new FoodModel('Hamburger', 13.5, 'sweet')];
            break;
        case 'McDonald':
            foods = [new FoodModel('Beef burger', 20, 'nice'), new FoodModel('Chicken nuggets', 15, 'tasty')];
            break;
        case 'Pho':
            foods = [new FoodModel('Noodles', 16, 'salty'), new FoodModel('Rice', 2, 'plain')];
            break;
    }
    return await Promise.resolve(foods);
}