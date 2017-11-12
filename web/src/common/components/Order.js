import React, {Component} from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import Chip from 'material-ui/Chip';
import DoneIcon from 'material-ui/svg-icons/action/done';
import Divider from 'material-ui/Divider';

const foodStyle = {
  display: 'flex'
};
const orderStyle = {
    width: '50%',
    marginBottom: '20px'
};

const titleText = {
    fontWeight: 'bold'
};

const actionsStyle = {
    display: 'flex',
    flexDirection: 'column'
};

export default class Order extends Component {
    render() {
        return (
            <div style={orderStyle}>
                <Card>
                    <CardHeader
                        title={'Order ID: ' + this.props.order.orderId}
                        subtitle={this.props.order.receiver}
                    />
                    <Divider />
                    <CardText>
                        <span style={titleText}>Address: </span>{this.props.order.address}
                        <span>   </span>
                        <span style={titleText}>Phone: </span>
                        {this.props.order.phone}
                    </CardText>
                    <CardText>
                        <span style={titleText}>Tips: </span>{'$'+this.props.order.tips}
                        <span>   </span>
                        <span style={titleText}>When: </span>{this.props.order.orderTime}
                    </CardText>
                    <CardText>
                        <span style={titleText}>Restaurant: </span>{this.props.order.restaurant}
                    </CardText>
                    <CardText style={foodStyle}>
                        <span style={titleText}>Foods: </span>
                        {this.props.order.foodsMap
                            .map((f, index) =>
                                <span key={index} style={{display: 'flex'}}>
                                    <Chip>{f.food.foodname+'  x  '+f.quantity}</Chip>
                                </span>)
                        }
                    </CardText>
                    <CardText>
                        {this.props.order.delivered && <span style={titleText}>Delivered?:<DoneIcon /></span>}
                        {this.props.order.canceled && <span style={titleText}>Canceled?:<DoneIcon /></span>}
                    </CardText>
                    <CardActions style={actionsStyle}>
                        {this.props.children}
                    </CardActions>
                </Card>
            </div>
        );
    }
}