import React, { Component } from 'react';
import {Card, CardActions, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import "./Login.css";
import {login} from "../services/AuthenticationService";

class Login extends Component {
    constructor(props) {
        super(props);
        this.getUsername = this.getUsername.bind(this);
        this.getPassword = this.getPassword.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
        this.state = {
            username: "",
            password: ""
        };
    }

    getUsername(event) {
        this.setState({username: event.target.value});
    }

    getPassword(event) {
        this.setState({password: event.target.value});
    }

    handleLogin() {
        let self = this;
        login(this.state.username, this.state.password)
            .then(function (result) {
                self.props.onLoginSuccessful(result.username, result.userType);
            }).catch(function (err) {
                self.props.onLoginFailure(err);
            });
    }

    render() {
        return (
            <div id="login-component">
                <Card>
                    <CardTitle title="Login to Feedr" />
                    <CardText className="card-content">
                        <TextField hintText="username" onChange={this.getUsername} />
                        <TextField hintText="password" onChange={this.getPassword}/>
                    </CardText>
                    <CardActions>
                        <FlatButton label="Login" onClick={this.handleLogin} />
                        <FlatButton label="Sign up" />
                    </CardActions>
                </Card>
            </div>
        );
    }
}

export default Login;
