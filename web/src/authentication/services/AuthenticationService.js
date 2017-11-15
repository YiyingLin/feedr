import axios from 'axios';

export function login(username, password) {
    return new Promise(function (resolve, reject) {
        axios.post('http://localhost:8080/signin', {username: username, password: password})
            .then(function (response) {
                resolve(response.data);
            })
            .catch(function (error) {
                reject(error);
            });
    });
}

export function signup(username, password, userType) {
    return Promise.resolve();
}