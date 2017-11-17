import React, {Component} from 'react';
import Order from "../../common/components/Order";

const orderListStyle = {
    marginTop: '20px',
    position: 'relative',
    left: '25%'
};


export default class RestaurantOrderList extends Component {
    render() {
        return (
            <div>
                <div style={orderListStyle}>
                    {this.props.orders.map((order, index) =>
                        <Order key={index} order={order} />
                    )}
                </div>
            </div>
        );
    }
}