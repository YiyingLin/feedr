import React, {Component} from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import RaisedButton from 'material-ui/RaisedButton';
import Chip from 'material-ui/Chip';
import DoneIcon from 'material-ui/svg-icons/action/done';
import NotDoneIcon from 'material-ui/svg-icons/content/clear';

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

export default class Order extends Component {
    render() {
        return (
            <div style={orderStyle}>
                <Card>
                    <CardHeader
                        title={'Order ID: ' + this.props.order.orderId}
                        subtitle={this.props.order.receiver}
                    />
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
                        {this.props.order.foods
                            .map((f, index) => <Chip key={index}>{f.foodname}</Chip>)}
                    </CardText>
                    {
                        this.props.private &&
                        <CardText>
                            {
                                !this.props.order.canceled &&
                                <span style={titleText}>Delivered?:
                                    {this.props.order.delivered? <DoneIcon />: <NotDoneIcon />}
                                </span>}
                            {
                                !this.props.order.delivered &&
                                <span style={titleText}>Canceled?:
                                    {this.props.order.canceled? <DoneIcon />: <NotDoneIcon />}
                                </span>}
                        </CardText>
                    }
                    {
                        !this.props.private &&
                        <CardActions>
                            <RaisedButton
                                label="Take this order"
                                onClick={() => this.props.handleTakeOrder(this.props.order.orderId)}
                            />
                        </CardActions>
                    }
                    {
                        this.props.private && !this.props.order.canceled && !this.props.order.delivered &&
                        <CardActions>
                            <RaisedButton
                                label="Cancel this order"
                                onClick={() => this.props.handleCancelOrder(this.props.order.orderId)}
                            />
                        </CardActions>
                    }
                </Card>
            </div>
        );
    }
}