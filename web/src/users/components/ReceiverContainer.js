import React, {Component} from 'react';
import OrderModel from "../../models/OrderModel";
import FoodModel from "../../models/FoodModel";
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

const mockOrders = [
    new OrderModel(32,'Me','2205 lower mall', 10, '8:46pm', 7788595117, 'PG',
        [
            {
                food: new FoodModel('Chicken', 123, 'spicy'),
                quantity: 1
            },
            {
                food: new FoodModel('Rice', 12, 'not spicy'),
                quantity: 3
            }
        ], true, false, 'Snow'
    ),
    new OrderModel(44,'Me','UBC', 10, '10:46pm', 7788595117, 'Vanier',
        [
            {
                food: new FoodModel('Water', 123, 'spicy'),
                quantity: 2
            },
            {
                food: new FoodModel('Ham', 12, 'not spicy'),
                quantity: 1
            }
        ], false, true
    ),
    new OrderModel(55,'Me','UBC', 10, '11:46pm', 1234566789, 'KFC',
        [
            {
                food: new FoodModel('Water', 123, 'spicy'),
                quantity: 3
            },
            {
                food: new FoodModel('Ham', 12, 'not spicy'),
                quantity: 1
            }
        ]
    ),
    new OrderModel(65,'Me','DT', 12, '12:46pm', 1234566789, 'Raisu',
        [
            {
                food: new FoodModel('Sushi', 22, 'spicy'),
                quantity: 3
            },
            {
                food: new FoodModel('Sashima', 33, 'not spicy'),
                quantity: 1
            }
        ]
    )
];

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
            orders: mockOrders,
            showCreateOrder: false,
            showRating: false,
            newTip: 0,
            orderOnFocus: ''
        };
        this.openCreateOrder = this.openCreateOrder.bind(this);
        this.cancelCreateOrder = this.cancelCreateOrder.bind(this);
        this.createOrder = this.createOrder.bind(this);
        this.cancelOrder= this.cancelOrder.bind(this);
        this.createRating = this.createRating.bind(this);
        this.cancelRating = this.cancelRating.bind(this);
        this.enterTip = this.enterTip.bind(this);
        this.addTip = this.addTip.bind(this);
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
        console.log('want to create order: '+ order);
        this.setState({showCreateOrder: false});
    }

    //manipulate existing orders methods
    cancelOrder(orderId) {
        console.log('want to cancel order: '+orderId)
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

    //rating methods
    createRating(rating) {
        this.setState({showRating: false});
    }
    cancelRating() {
        this.setState({showRating: false});
    }

    render() {
        return (
            <div>
                <Paper zDepth={1}>
                    {/*Receiver only has one panel*/}
                    <BottomNavigation selectedIndex={0}>
                        <BottomNavigationItem label="My Orders" icon={<MyOrdersIcon />}/>
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
                                {
                                    ReceiverContainer.isPendingOrder(order) &&
                                    <span>
                                        <RaisedButton
                                            style={addTipStyle}
                                            label="Add tips"
                                            onClick={() => this.addTip(order.orderId)}
                                        />
                                        <TextField type="number"
                                                   onClick={()=>this.setState({orderOnFocus: order.orderId, newTip:0})}
                                                   value={
                                                       this.state.orderOnFocus===order.orderId?
                                                           this.state.newTip: 0
                                                   }
                                                   hintText="add some tips..."
                                                   onChange={this.enterTip} />
                                    </span>
                                }
                                {
                                    ReceiverContainer.isPendingOrder(order) &&
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
                    handleCreateRating={this.createRating}
                />
            </div>
        );
    }
}
