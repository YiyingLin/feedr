import React, { Component } from 'react';
import {Card, CardActions, CardTitle, CardText} from 'material-ui/Card';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import "./Authentication.css";
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import UserType from "../../utils/UserTypes";


class Authentication extends Component {
    constructor(props) {
        super(props);
        this.getUsername = this.getUsername.bind(this);
        this.getPassword = this.getPassword.bind(this);
        this.getUserType = this.getUserType.bind(this);
        this.state = {
            username: "",
            password: "",
            userType: UserType.USER
        };
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

    render() {
        return (
            <div id="authentication-component">
                <Card>
                    <CardTitle title={this.props.title} />
                    <CardText className="card-content">
                        <TextField hintText="username" onChange={this.getUsername} />
                        <TextField type={'password'} hintText="password" onChange={this.getPassword}/>
                    </CardText>
                    {
                        (this.props.showUserTypes) &&
                        <div>
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
                    <CardActions>
                        <RaisedButton
                            label={this.props.submitName}
                            onClick={() => this.props.handleSubmit(
                                this.state.username,
                                this.state.password,
                                this.state.userType)
                            }
                        />
                    </CardActions>
                </Card>
            </div>
        );
    }
}

export default Authentication;
