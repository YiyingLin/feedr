import React, { Component } from 'react';
import './App.css';
import Login from './authentication/Login';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import {isLoggedIn} from "./authentication/AuthenticationService";

class App extends Component {
    loginSuccessful(username, userType) {
        console.log(username, userType);
    }

    loginFailed(error) {
        console.log("Login failed: ");
        console.log(error);
    }

    render() {
        return (
            <MuiThemeProvider>
              <div className="App">
                <Login
                    onLoginSuccessful={this.loginSuccessful}
                    onLoginFailure={this.loginFailed}
                />
              </div>
            </MuiThemeProvider>
        );
    }
}

export default App;
