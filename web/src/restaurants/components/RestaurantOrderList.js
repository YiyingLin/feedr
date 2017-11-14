import React, {Component} from 'react';
import Order from "../../common/components/Order";
import RaisedButton from 'material-ui/RaisedButton';

const orderListStyle = {
    marginTop: '20px',
    position: 'relative',
    left: '25%'
};


export default class RestaurantOrderList extends Component {

    constructor(props) {
        super(props);
        this.handleTakeOrder = this.handleTakeOrder.bind(this);
    }

    handleTakeOrder(orderId) {
        console.log('want to take order: '+orderId)
    }

    render() {
        return (
            <div>
                <div style={orderListStyle}>
                    {this.props.orders.map((order, index) =>
                        <Order key={index} order={order}>
                            <RaisedButton label="Take this order" onClick={() => this.handleTakeOrder(order.orderId)}/>
                        </Order>
                    )}
                </div>
            </div>
        );
    }
}