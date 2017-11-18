import React, { Component } from 'react';
import {Card, CardActions, CardTitle, CardText} from 'material-ui/Card';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import "./Authentication.css";
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import UserType from "../../utils/UserTypes";
import ProfileModel from "../../models/ProfileModel";

const Cookie = require('js-cookie');

class Authentication extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            userType: UserType.USER,
            restaurantName: '',
            location: '',
            phone: ''
        };
        this.getUsername = this.getUsername.bind(this);
        this.getPassword = this.getPassword.bind(this);
        this.getUserType = this.getUserType.bind(this);
        this.getRestaurantName = this.getRestaurantName.bind(this);
        this.getLocation = this.getLocation.bind(this);
        this.getPhone = this.getPhone.bind(this);
    }

    getPhone(event) {
        this.setState({phone: event.target.value});
    }

    getUsername(event) {
        this.setState({username: event.target.value});
    }

    getPassword(event) {
        this.setState({password: event.target.value});
    }

    getUserType(event, index, value) {
        this.setState({userType: value});
    }

    getRestaurantName(event) {
        this.setState({restaurantName: event.target.value});
    }

    getLocation(event) {
        this.setState({location: event.target.value});
    }

    render() {
        return (
            <div id="authentication-component">
                <Card>
                    <CardTitle title={this.props.title} />
                    <CardText className="card-content">
                        <TextField value={this.state.username} hintText="username" onChange={this.getUsername} />
                        <TextField value={this.state.password} type={'password'} hintText="password" onChange={this.getPassword}/>
                    </CardText>
                    {
                        (this.props.showUserTypes) &&
                        <div>
                            <TextField value={this.state.phone} hintText="phone" onChange={this.getPhone} /><br/>
                            <span>Signup as...</span><br/>
                            <SelectField
                                value={this.state.userType}
                                onChange={this.getUserType}
                            >
                                <MenuItem value={UserType.USER} primaryText={UserType.USER} />
                                <MenuItem value={UserType.RESTAURANT} primaryText={UserType.RESTAURANT} />
                            </SelectField>
                        </div>
                    }
                    {
                        (this.state.userType === UserType.RESTAURANT) &&
                        <div>
                            <TextField value={this.state.restaurantName} hintText="Restaurant name" onChange={this.getRestaurantName} />
                            <TextField value={this.state.location} hintText="Location" onChange={this.getLocation} />
                        </div>
                    }
                    <CardActions>
                        <RaisedButton
                            label={this.props.submitName}
                            onClick={() => this.props.handleSubmit(
                                new ProfileModel(
                                    this.state.username,
                                    this.state.phone,
                                    this.state.password,
                                    this.state.userType,
                                    0,
                                    '',
                                    this.state.restaurantName,
                                    0,
                                    this.state.location
                                ))
                            }
                        />
                    </CardActions>
                </Card>
            </div>
        );
    }
}

export default Authentication;
