import React, { Component } from 'react';
import './App.css';
import Login from './authentication/components/Login';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import {isLoggedIn} from "./authentication/services/AuthenticationService";
import AdminContainer from "./admins/components/AdminContainer";
import MyProfile from "./common/components/MyProfile";
import RestaurantContainer from "./restaurants/components/RestaurantContainer";
import UserContainer from "./users/components/UserContainer";

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
            <MuiThemeProvider className="App">
                {/*MuiThemeProvider want single react child*/}
                <div className="App">
                    <Login
                        onLoginSuccessful={this.loginSuccessful}
                        onLoginFailure={this.loginFailed}
                    />
                    <AdminContainer />
                    <MyProfile />
                    <RestaurantContainer />
                    <UserContainer />
                </div>
            </MuiThemeProvider>
        );
    }
}

export default App;
