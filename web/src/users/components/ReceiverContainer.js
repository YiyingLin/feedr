import React, {Component} from 'react';
import OrderModel from "../../models/OrderModel";
import FoodModel from "../../models/FoodModel";
import Order from "../../common/components/Order";
import FloatingActionButton from "material-ui/FloatingActionButton";
import ContentAdd from 'material-ui/svg-icons/content/add';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import OrderForm from './OrderForm';

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
        ], true
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
            newTip: 0
        };
        this.openCreateOrder = this.openCreateOrder.bind(this);
        this.cancelCreateOrder = this.cancelCreateOrder.bind(this);
        this.createOrder = this.createOrder.bind(this);
        this.cancelOrder= this.cancelOrder.bind(this);
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
    addTip(tip) {
        console.log('want to add tip: '+tip)
    }
    confirmDelivery(orderId) {
        console.log('want to confirm order: '+orderId)
    }

    render() {
        return (
            <div>
                <h3>My Orders: </h3>
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
                                            onClick={() => {this.addTip(this.state.newTip);this.setState({newTip:0});}}
                                        />
                                        <TextField type="number"
                                                   value={this.state.newTip}
                                                   hintText="add some tips..."
                                                   onChange={(event)=> {this.setState({newTip:event.target.value})}} />
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
            </div>
        );
    }
}
