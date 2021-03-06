import React, {Component} from 'react';
import Order from "../../common/components/Order";
import FloatingActionButton from "material-ui/FloatingActionButton";
import ContentAdd from 'material-ui/svg-icons/content/add';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import OrderForm from './OrderForm';
import RatingForm from "./RatingForm";
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import MyOrdersIcon from 'material-ui/svg-icons/action/assignment';
import Paper from 'material-ui/Paper';
import {getPrivateOrders, cancelOrder, createNewOrder, confirmOwnDelivery} from "../services/OrdersHttpService";
import Dialog from 'material-ui/Dialog';

const orderListStyle = {
    marginTop: '20px',
    position: 'relative',
    left: '25%'
};

const addOrderStyle = {
    position: 'fixed',
    right: '10%',
    bottom: '10%'
};

const addTipStyle = {
    marginRight: '15px'
};



export default class ReceiverContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            showCreateOrder: false,
            showRating: false,
            newTip: 0,
            orderOnFocus: '',
            alertControl: false,
            alertMessage: ""
        };
        this.openCreateOrder = this.openCreateOrder.bind(this);
        this.cancelCreateOrder = this.cancelCreateOrder.bind(this);
        this.createOrder = this.createOrder.bind(this);
        this.cancelOrder= this.cancelOrder.bind(this);
        this.createRating = this.createRating.bind(this);
        this.cancelRating = this.cancelRating.bind(this);
        this.enterTip = this.enterTip.bind(this);
        this.addTip = this.addTip.bind(this);
        this.getPrivateOrders = this.getPrivateOrders.bind(this);
        this.doConfirmDelivery = this.doConfirmDelivery.bind(this);
        this.getPrivateOrders();
    }

    static isPendingOrder(order) {
        return !order.canceled && !order.delivered;
    }

    //create order methods
    openCreateOrder() {
        this.setState({showCreateOrder: true});
    }

    cancelCreateOrder() {
        this.setState({showCreateOrder: false});
    }

    createOrder(order) {
        console.log('want to create order: ');
        console.log(order);
        this.setState({showCreateOrder: false});
        createNewOrder(order).then(() => {
            this.getPrivateOrders();
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
    }

    //manipulate existing orders methods
    cancelOrder(orderId) {
        console.log("want to cancel: "+orderId);
        cancelOrder(orderId, "").then(() => {
            this.getPrivateOrders();
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
    }
    addTip(orderId) {
        this.setState({orderOnFocus: orderId});
    }
    enterTip(event) {
        this.setState({newTip:parseInt(event.target.value,10)})
    }
    confirmDelivery(orderId) {
        this.setState(
            {orderOnFocus: orderId},
            () => {this.setState({showRating: true});}
        );
    }

    doConfirmDelivery(rating) {
        let deliveryRating = {
            senderRating: rating.senderRating,
            restaurantRating: rating.restaurantRating,
            commentSender: rating.commentSender,
            commentRestaurant: rating.commentRestaurant,
            orderId: this.state.orderOnFocus
        };
        console.log("want to confirm delivery:");
        console.log(deliveryRating);
        confirmOwnDelivery(deliveryRating).then(() => {
            this.getPrivateOrders(false);
        }).catch((err) => {
            this.setState({alertControl:true});
            this.setState({alertMessage:JSON.stringify(err)});
        });
        this.setState({showRating: false});
    }

    //rating methods
    createRating(rating) {
        this.setState({showRating: false});
    }
    cancelRating() {
        this.setState({showRating: false});
    }

    getPrivateOrders() {
        getPrivateOrders(false).then(orders => {
            this.setState({orders: orders})
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
                    <BottomNavigation selectedIndex={0}>
                        <BottomNavigationItem label="My Orders"
                                              icon={<MyOrdersIcon />}
                                              onClick={() => this.getPrivateOrders()}
                        />
                    </BottomNavigation>
                </Paper>
                <div style={orderListStyle}>
                    {this.state.orders.map((order, index) =>
                            <Order key={index} order={order} private={true}
                                   isReceiver={true}
                                   handleAddTips={this.addTip}
                                   handleConfirmDelivery={()=>this.confirmDelivery(order.orderId)}
                                   handleCancelOrder={()=>this.cancelOrder(order.orderId)}>
                                {
                                    ReceiverContainer.isPendingOrder(order) &&
                                    <RaisedButton
                                        label="Cancel this order"
                                        onClick={() => this.cancelOrder(order.orderId)}
                                    />
                                }
                                {/*{*/}
                                    {/*ReceiverContainer.isPendingOrder(order) &&*/}
                                    {/*<span>*/}
                                        {/*<RaisedButton*/}
                                            {/*style={addTipStyle}*/}
                                            {/*label="Add tips"*/}
                                            {/*onClick={() => this.addTip(order.orderId)}*/}
                                        {/*/>*/}
                                        {/*<TextField type="number"*/}
                                                   {/*onClick={()=>this.setState({orderOnFocus: order.orderId, newTip:0})}*/}
                                                   {/*value={*/}
                                                       {/*this.state.orderOnFocus===order.orderId?*/}
                                                           {/*this.state.newTip: 0*/}
                                                   {/*}*/}
                                                   {/*hintText="add some tips..."*/}
                                                   {/*onChange={this.enterTip} />*/}
                                    {/*</span>*/}
                                {/*}*/}
                                {
                                    ReceiverContainer.isPendingOrder(order) && order.sender &&
                                    <RaisedButton
                                        label="Confirm delivery"
                                        onClick={() => this.confirmDelivery(order.orderId)}
                                    />
                                }
                            </Order>
                    )}
                </div>
                {
                    this.props.onFocus &&
                    <FloatingActionButton style={addOrderStyle} onClick={this.openCreateOrder}>
                        <ContentAdd />
                    </FloatingActionButton>
                }
                <OrderForm
                    showCreateOrder={this.state.showCreateOrder}
                    cancelCreateOrder={this.cancelCreateOrder}
                    createOrder={this.createOrder}
                />
                <RatingForm
                    showRating={this.state.showRating}
                    handleCancelRating={this.cancelRating}
                    handleCreateRating={this.doConfirmDelivery}
                />
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
