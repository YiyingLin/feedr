export default class ProfileModel {
    // this is exactly mapped to Profile.proto
    constructor(username, phone, password, type, sender_rating, sender_location, res_name, res_rating, res_location) {
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.type = type;
        this.sender_rating = sender_rating;
        this.sender_location = sender_location;
        this.res_name = res_name;
        this.res_rating = res_rating;
        this.res_location = res_location;
    }
}