import React, {Component} from 'react';
import Order from "../../common/components/Order";
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import PublicOrderIcon from 'material-ui/svg-icons/places/airport-shuttle';
import MyOrdersIcon from 'material-ui/svg-icons/action/assignment';
import Paper from 'material-ui/Paper';
import RaisedButton from 'material-ui/RaisedButton';
import {getPublicOrders, getPrivateOrders, takeOrder} from "../services/OrdersHttpService";
import Dialog from 'material-ui/Dialog';

const Cookie = require('js-cookie');

const orderListStyle = {
    marginTop: '20px',
    position: 'relative',
    left: '25%'
};

export default class SenderContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            privateOrders: [],
            selectedIndex: 0,
            alertControl: false,
            alertMessage: ""
        };
        this.selectSection = this.selectSection.bind(this);
        this.handleCancelOrder = this.handleCancelOrder.bind(this);
        this.handleTakeOrder = this.handleTakeOrder.bind(this);
        this.getPublicOrders = this.getPublicOrders.bind(this);
        this.getPrivateOrders = this.getPrivateOrders.bind(this);
        this.getPublicOrders();
        this.getPrivateOrders();
    }
    selectSection(index) {
        this.setState({selectedIndex: index});
        if (index === 0) {
            this.getPublicOrders();
        }
        if (index === 1) {
            this.getPrivateOrders();
        }
    }
    handleCancelOrder(orderId) {
        console.log('want to cancel order: '+orderId)
        // for simplicity sender cancellation is disallowed
    }
    handleTakeOrder(orderId) {
        console.log('want to take order: '+orderId)
        takeOrder(orderId).then(() => {
            this.getPublicOrders();
            this.getPrivateOrders();
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
    }

    getPublicOrders() {
        getPublicOrders().then(orders => {
            this.setState({orders: orders})
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
    }

    getPrivateOrders() {
        getPrivateOrders(true).then(orders => {
            this.setState({privateOrders: orders})
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
    }

    render() {
        return (
            <div>
                <Paper zDepth={1}>
                    <BottomNavigation selectedIndex={this.state.selectedIndex}>
                        <BottomNavigationItem
                            label="Public Orders"
                            icon={<PublicOrderIcon />}
                            onClick={() => this.selectSection(0)}
                        />
                        <BottomNavigationItem
                            label="My Taken Orders"
                            icon={<MyOrdersIcon />}
                            onClick={() => this.selectSection(1)}
                        />
                    </BottomNavigation>
                </Paper>
                {/*Public posting board*/}
                {this.state.selectedIndex===0 &&
                    <div style={orderListStyle}>
                        {this.state.orders.map((order, index) =>
                            <Order key={index} order={order}>
                                {
                                    order.receiver !== Cookie.get('username') &&
                                    <RaisedButton label="Take this order" onClick={() => this.handleTakeOrder(order.orderId)}/>
                                }
                            </Order>
                        )}
                    </div>
                }

                {/*My taken orders*/}
                {this.state.selectedIndex===1 &&
                    <div style={orderListStyle}>
                        {this.state.privateOrders.map((order, index) =>
                                <Order key={index} order={order}>
                                    {/*for simplicity disallow sender from cancelling orders */}
                                    {/*{*/}
                                        {/*!order.canceled && !order.delivered &&*/}
                                        {/*<RaisedButton*/}
                                            {/*label="Cancel this order"*/}
                                            {/*onClick={() => this.handleCancelOrder(order.orderId)}*/}
                                        {/*/>*/}
                                    {/*}*/}
                                </Order>
                        )}
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
