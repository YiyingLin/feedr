import React, {Component} from "react";
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import MyOrdersIcon from 'material-ui/svg-icons/action/assignment';
import ManageMenuIcon from 'material-ui/svg-icons/action/dns';
import Paper from 'material-ui/Paper';
import RestaurantOrderList from './RestaurantOrderList';
import ManageFood from './ManageFood';
import {getRestaurantOrders} from '../services/RestaurantHttpService';
import Dialog from 'material-ui/Dialog';
import RaisedButton from 'material-ui/RaisedButton';
import {restaurantGetItsMenu} from "../services/RestaurantHttpService";

export default class RestaurantContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedIndex: 0,
            orders: [],
            menu: [],
            alertControl: false,
            alertMessage: ""
        };
        this.getRestaurantOrder = this.getRestaurantOrder.bind(this);
        this.getOwnMenu = this.getOwnMenu.bind(this);
        this.getRestaurantOrder();
        this.getOwnMenu();
    }

    selectSection(index) {
        this.setState({selectedIndex: index});
        if (index === 0) {
            this.getRestaurantOrder();
        }
        if (index === 1) {
            this.getOwnMenu();
        }
    }

    getRestaurantOrder() {
        getRestaurantOrders().then(orders => {
            this.setState({orders: orders})
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
    }

    getOwnMenu() {
        restaurantGetItsMenu().then(menu => {
            this.setState({menu: menu})
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
    }

    render() {
        return (
            <div>
                <Paper zDepth={1}>
                    {/*Receiver only has one panel*/}
                    <BottomNavigation selectedIndex={this.state.selectedIndex}>
                        <BottomNavigationItem label="My Orders"
                                              icon={<MyOrdersIcon />}
                                              onClick={() => this.selectSection(0)}
                        />
                        <BottomNavigationItem label="Manage Menu"
                                              icon={<ManageMenuIcon />}
                                              onClick={() => this.selectSection(1)}
                        />
                    </BottomNavigation>
                </Paper>
                {
                    this.state.selectedIndex===0 &&
                    <div>
                        <RestaurantOrderList orders={this.state.orders} />
                    </div>
                }
                {
                    this.state.selectedIndex===1 &&
                    <div>
                        <ManageFood menu={this.state.menu} />
                    </div>
                }
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
