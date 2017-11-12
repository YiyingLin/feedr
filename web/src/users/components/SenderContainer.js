import React, {Component} from 'react';
import Order from "../../common/components/Order";
import OrderModel from "../../models/OrderModel";
import FoodModel from "../../models/FoodModel";
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import PublicOrderIcon from 'material-ui/svg-icons/places/airport-shuttle';
import MyOrdersIcon from 'material-ui/svg-icons/action/assignment';
import Paper from 'material-ui/Paper';
import RaisedButton from 'material-ui/RaisedButton';

const orderListStyle = {
    marginTop: '20px',
    position: 'relative',
    left: '25%'
};

const mockOrders = [
    new OrderModel(123,'Marlon','2205 lower mall', 10, '8:46pm', 7788595117, 'PG',
        [
            {
                food: new FoodModel('Chicken', 123, 'spicy'),
                quantity: 2
            },
            {
                food: new FoodModel('Rice', 12, 'not spicy'),
                quantity: 1
            }
        ]
    ),
    new OrderModel(13,'John','UBC', 10, '9:46pm', 7788595117, 'Vanier',
        [
            {
                food: new FoodModel('Water', 123, 'spicy'),
                quantity: 4
            },
            {
                food: new FoodModel('Ham', 12, 'not spicy'),
                quantity: 1
            }
        ]
    )
];

const mockPrivateOrders = [
    new OrderModel(123,'Marlon','2205 lower mall', 10, '8:46pm', 7788595117, 'PG',
        [
            {
                food: new FoodModel('Chicken', 123, 'spicy'),
                quantity: 3
            },
            {
                food: new FoodModel('Rice', 12, 'not spicy'),
                quantity: 2
            }
        ],
        true
    ),
    new OrderModel(133,'John','UBC', 10, '9:46pm', 7788595117, 'Vanier',
        [
            {
                food: new FoodModel('Water', 123, 'spicy'),
                quantity: 1
            },
            {
                food: new FoodModel('Ham', 12, 'not spicy'),
                quantity: 3
            }
        ],false, false
    ),
    new OrderModel(213,'Annie','SFU', 10, '9:46pm', 7788595117, 'Totem',
        [
            {
                food: new FoodModel('Noodle', 20, 'spicy'),
                quantity: 2
            },
            {
                food: new FoodModel('Beef', 23, 'not spicy'),
                quantity: 1
            }
        ],false, true
    )
];

export default class SenderContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: mockOrders,
            privateOrders: mockPrivateOrders,
            selectedIndex: 0
        };
        this.selectSection = this.selectSection.bind(this);
        this.handleCancelOrder = this.handleCancelOrder.bind(this);
        this.handleTakeOrder = this.handleTakeOrder.bind(this);
    }
    selectSection(index) {
        this.setState({selectedIndex: index});
    }
    handleCancelOrder(orderId) {
        console.log('want to cancel order: '+orderId)
    }
    handleTakeOrder(orderId) {
        console.log('want to take order: '+orderId)
    }

    render() {
        return (
            <div>
                <Paper zDepth={1}>
                    <BottomNavigation selectedIndex={this.state.selectedIndex}>
                        <BottomNavigationItem
                            label="Public orders"
                            icon={<PublicOrderIcon />}
                            onClick={() => this.selectSection(0)}
                        />
                        <BottomNavigationItem
                            label="My Taken orders"
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
                                <RaisedButton label="Take this order" onClick={() => this.handleTakeOrder(order.orderId)}/>
                            </Order>
                        )}
                    </div>
                }

                {/*My taken orders*/}
                {this.state.selectedIndex===1 &&
                    <div style={orderListStyle}>
                        {this.state.privateOrders.map((order, index) =>
                                <Order key={index} order={order}>
                                    {
                                        !order.canceled && !order.delivered &&
                                        <RaisedButton
                                            label="Cancel this order"
                                            onClick={() => this.handleCancelOrder(order.orderId)}
                                        />
                                    }
                                </Order>
                        )}
                    </div>
                }
            </div>
        );
    }
}
