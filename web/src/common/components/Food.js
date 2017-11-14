import React, {Component} from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import Divider from 'material-ui/Divider';
import RaisedButton from 'material-ui/RaisedButton';

const titleText = {
    fontWeight: 'bold'
};

const foodContainerStyle = {
    width: '100%',
    marginBottom: '20px'
};

const actionsStyle = {
    display: 'flex',
    flexDirection: 'column'
};


export default class Food extends Component {
    render() {
        return (
            <div style={foodContainerStyle}>
                <Card>
                    <CardHeader
                        title={'Food: '+this.props.food.foodname}
                    />
                    <Divider />
                    <CardText>
                        <span style={titleText}>price: </span>
                        {this.props.food.price}
                    </CardText>
                    <CardText>
                        <span style={titleText}>Food type: </span>
                        {this.props.food.foodType}
                    </CardText>
                    <CardActions style={actionsStyle}>
                        <RaisedButton
                            label="Delete this food" onClick={this.props.handleDelete}
                        />
                    </CardActions>
                </Card>
            </div>
        );
    }
}