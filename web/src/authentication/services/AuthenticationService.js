import UserType from "../../utils/UserTypes";
//const request = require('request');

export function login(username, password) {
    return Promise.resolve({
        username: username,
        userType: UserType.RESTAURANT
    });
}

export function signup(username, password, userType) {
    return Promise.resolve();
}