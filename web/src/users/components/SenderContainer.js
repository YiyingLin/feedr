import React, {Component} from 'react';
import Order from "../../common/components/Order";
import OrderModel from "../../models/OrderModel";
import FoodModel from "../../models/FoodModel";
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import SenderIcon from 'material-ui/svg-icons/maps/directions-run';
import ReceiverIcon from 'material-ui/svg-icons/action/accessibility';
import Paper from 'material-ui/Paper';

const orderListStyle = {
    marginTop: '20px',
    position: 'relative',
    left: '25%'
};

export default class SenderContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [
                new OrderModel(123,'Marlon','2205 lower mall', 10, '8:46pm', 7788595117, 'PG',
                    [
                        new FoodModel('Chicken', 123, 'spicy'),
                        new FoodModel('Rice', 12, 'not spicy')
                    ]
                ),
                new OrderModel(13,'John','UBC', 10, '9:46pm', 7788595117, 'Vanier',
                    [
                        new FoodModel('Water', 123, 'spicy'),
                        new FoodModel('Ham', 12, 'not spicy')
                    ]
                )
            ],
            privateOrders: [
                new OrderModel(123,'Marlon','2205 lower mall', 10, '8:46pm', 7788595117, 'PG',
                    [
                        new FoodModel('Chicken', 123, 'spicy'),
                        new FoodModel('Rice', 12, 'not spicy')
                    ],
                    true
                ),
                new OrderModel(133,'John','UBC', 10, '9:46pm', 7788595117, 'Vanier',
                    [
                        new FoodModel('Water', 123, 'spicy'),
                        new FoodModel('Ham', 12, 'not spicy')
                    ],false, false
                ),
                new OrderModel(213,'Annie','SFU', 10, '9:46pm', 7788595117, 'Totem',
                    [
                        new FoodModel('Noodle', 20, 'spicy'),
                        new FoodModel('Beef', 23, 'not spicy')
                    ],false, true
                )
            ],
            selectedIndex: 0
        };
        this.selectSection = this.selectSection.bind(this);
    }
    selectSection(index) {
        this.setState({selectedIndex: index});
    }

    render() {
        return (
            <div>
                <Paper zDepth={1}>
                    <BottomNavigation selectedIndex={this.state.selectedIndex}>
                        <BottomNavigationItem
                            label="Public orders"
                            icon={<SenderIcon />}
                            onClick={() => this.selectSection(0)}
                        />
                        <BottomNavigationItem
                            label="My Taken orders"
                            icon={<ReceiverIcon />}
                            onClick={() => this.selectSection(1)}
                        />
                    </BottomNavigation>
                </Paper>
                {/*Public posting board*/}
                {this.state.selectedIndex===0 &&
                    <div style={orderListStyle}>
                        {this.state.orders
                            .map((o, index) => <Order key={index} order={o}/>)}
                    </div>
                }

                {/*My taken orders*/}
                {this.state.selectedIndex===1 &&
                    <div style={orderListStyle}>
                        {this.state.privateOrders
                            .map((o, index) => <Order key={index} order={o} private={true}/>)}
                    </div>
                }
            </div>
        );
    }
}
