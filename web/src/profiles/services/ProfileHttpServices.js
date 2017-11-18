import axios from 'axios';
import ProfileModel from "../../models/ProfileModel";

const Cookie = require('js-cookie');

export function getProfile(username) {
    return new Promise(function (resolve, reject) {
        axios.get(`http://localhost:8080/profile/${username}`)
            .then(function (response) {
                console.log(response);
                resolve(new ProfileModel(
                    response.data.username,
                    response.data.phone,
                    response.data.password,
                    response.data.type,
                    response.data.sender_rating,
                    response.data.sender_location,
                    response.data.res_name,
                    response.data.res_rating,
                    response.data.res_location
                ));
            })
            .catch(function (err) {
                reject(err);
            });
    });
}

export function createProfile(profileModel) {
    return new Promise(function (resolve, reject) {
        axios.post(`http://localhost:8080/createProfile`, profileModel)
            .then(function (response) {
                console.log(response);
                resolve();
            })
            .catch(function (err) {
                reject(err);
            });
    });
}

export function deleteProfile() {
    return new Promise(function (resolve, reject) {
        axios.get(`http://localhost:8080/deleteProfile/${Cookie.get("username")}`)
            .then(function (response) {
                console.log(response);
                resolve();
            })
            .catch(function (err) {
                reject(err);
            });
    });
}