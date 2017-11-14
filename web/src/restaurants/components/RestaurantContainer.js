import React, {Component} from "react";
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import MyOrdersIcon from 'material-ui/svg-icons/action/assignment';
import ManageMenuIcon from 'material-ui/svg-icons/action/dns';
import Paper from 'material-ui/Paper';
import RestaurantOrderList from './RestaurantOrderList';
import ManageFood from './ManageFood';
import OrderModel from "../../models/OrderModel";
import FoodModel from "../../models/FoodModel";

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

export default class RestaurantContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedIndex: 0
        }
    }

    selectSection(index) {
        this.setState({selectedIndex: index});
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
                        <RestaurantOrderList orders={mockOrders} />
                    </div>
                }
                {
                    this.state.selectedIndex===1 &&
                    <div>
                        <ManageFood />
                    </div>
                }
            </div>
        );
    }
}
