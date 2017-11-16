import React, { Component } from 'react';
import Authentication from './Authentication';
import RaisedButton from 'material-ui/RaisedButton';
import {login, signup} from "../services/AuthenticationService";
import Dialog from 'material-ui/Dialog';

export default class AuthenticationContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loginMode: true,
            alertControl: false,
            alertMessage: ""
        };
        this.changeMode = this.changeMode.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }

    handleLogin(username, password) {
        login(username, password)
            .then((data) => {
                this.props.loginSuccess(data.username, data.userType)
            })
            .catch((err) => {
                console.log(err);
                this.setState({alertControl:true});
                this.setState({alertMessage:JSON.stringify(err)});
            });
    }

    handleSignup(username, password, userType) {
        console.log(username, password, userType);
        signup(username, password, userType)
            .then((result) => {

            }).catch((err) => {
                console.log(err);
                this.setState({alertControl:true});
                this.setState({alertMessage:err});
            });
    }

    changeMode() {
        this.setState({loginMode: !this.state.loginMode});
    }

    message = () =>
        this.state.loginMode?
        "Don't have an account?...Sign up!":
        "Already have an account? Login!";

    render() {
        return (
            <div>
                {
                    this.state.loginMode?
                        (<Authentication
                            title={"Login to Feedr!"}
                            submitName={"Login"}
                            handleSubmit={this.handleLogin} />):
                        (<Authentication
                            title={"Signup for a feedr account"}
                            submitName={"Signup"}
                            showUserTypes={true}
                            handleSubmit={this.handleSignup} />)
                }
                <RaisedButton label={this.message()} onClick={this.changeMode}/>
                <Dialog
                    actions={
                        <RaisedButton
                            label="OK"
                            primary={true}
                            onClick={() => this.setState({alertControl:false})}
                        />
                    }
                    modal={false}
                    open={this.state.alertControl}
                    onRequestClose={() => this.setState({alertControl:false})}
                >
                    {this.state.alertMessage}
                </Dialog>
            </div>
        );
    }
}