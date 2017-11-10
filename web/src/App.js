import React, { Component } from 'react';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AdminContainer from "./admins/components/AdminContainer";
import RestaurantContainer from "./restaurants/components/RestaurantContainer";
import UserContainer from "./users/components/UserContainer";
import AuthenticationContainer from "./authentication/components/AuthenticationContainer";
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import ActionHome from 'material-ui/svg-icons/action/home';
import IconButton from 'material-ui/IconButton';
import UserTypes from "./utils/UserTypes";

const Cookie = require('js-cookie');

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userType: Cookie.get('userType'),
            username: Cookie.get('username')
        };
        this.loginSuccess = this.loginSuccess.bind(this);
        this.updateUserType = this.updateUserType.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    loginSuccess(username, userType) {
        Cookie.set('userType', userType);
        Cookie.set('username', username);
        this.setState({username: username});
        this.setState({userType: userType});
    }

    handleLogout() {
        Cookie.remove('userType');
        Cookie.remove('username');
        this.setState({username: null});
        this.setState({userType: null});
    }


    updateUserType = (userType) => {
        this.state.setState({userType: userType});
    };

    render() {
        const appBar =
            <AppBar title={'Feedr'}
                    iconElementLeft={<IconButton><ActionHome /></IconButton>}
                    iconElementRight={
                        <span>
                            <span>{'Hello, '+this.state.username+'   '}</span>
                            <RaisedButton label="My Profile" primary={true} />
                            <span>{'   '}</span>
                            <RaisedButton label="logout" onClick={this.handleLogout}/>
                        </span>
                    }/>;

        return (
            <MuiThemeProvider className="App">
                {/*MuiThemeProvider want single react child*/}
                <div className="App">
                    {this.state.username && true?
                        <div>
                            {appBar}
                            {this.state.userType===UserTypes.ADMIN && <AdminContainer />}
                            {this.state.userType===UserTypes.RESTAURANT && <RestaurantContainer />}
                            {this.state.userType===UserTypes.USER && <UserContainer />}
                        </div>:
                        <AuthenticationContainer loginSuccess={this.loginSuccess} />
                    }
                </div>
            </MuiThemeProvider>
        );
    }
}

export default App;
