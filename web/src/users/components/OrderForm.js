import React, {Component} from "react";
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import TextField from 'material-ui/TextField';
import Divider from 'material-ui/Divider';
import {getAllFoodOfRestaurant, getAllRestaurants} from '../services/CreateOrderService'
import DatePicker from 'material-ui/DatePicker';
import TimePicker from 'material-ui/TimePicker';
import Dialog from 'material-ui/Dialog';
import RaisedButton from 'material-ui/RaisedButton';
import Chip from 'material-ui/Chip';

const _ = require('lodash');
const moment = require('moment');
const Cookie = require('js-cookie');

const titleTextStyle = {
    fontWeight: 'bold'
};

const sectionStyle = {display:'flex', justifyContent: 'space-around'};

const dividerStyle = {
    marginTop: '10px',
    marginBottom: '10px'
};

const chipStyle = {
    margin: 4,
};

const chipsStyle = {
    display: 'flex',
    flexWrap: 'wrap',
};

export default class OrderForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            restaurants: [],
            selectedRestaurant: 0,
            foodsOnMenu: [],
            selectedFood: 0,
            foodsOrdered: [],//{food: foodModel, quantity: number}
            address: '',
            tip: '',
            totalPrice: 0, //excluding tip
            date: '',
            time: ''
        };
        this.selectRestaurant = this.selectRestaurant.bind(this);
        this.getFoodsMenu = this.getFoodsMenu.bind(this);
        this.selectFood = this.selectFood.bind(this);
        this.enterAddress = this.enterAddress.bind(this);
        this.enterTip = this.enterTip.bind(this);
        this.enterDate = this.enterDate.bind(this);
        this.enterTime = this.enterTime.bind(this);
        this.handleCreateOrder = this.handleCreateOrder.bind(this);
        this.handleCancelCreateOrder = this.handleCancelCreateOrder.bind(this);
        this.clearStates = this.clearStates.bind(this);
        this.unselectFood = this.unselectFood.bind(this);
        this.calculatePriceHelper = this.calculatePriceHelper.bind(this);
        this.updateFoodsOrderedHelper = this.updateFoodsOrderedHelper.bind(this);

        getAllRestaurants().then((restaurants) => this.setState({restaurants:restaurants}));
    }

    //create / cancel
    handleCreateOrder() {
        let order = {
            receiver_name: Cookie.get("username"),
            restaurant_name: this.state.restaurants[this.state.selectedRestaurant],
            order_cost: this.state.totalPrice,//excluding tip
            deliver_tip: this.state.tip,
            deadline: this.state.date+" "+this.state.time+".0",
            delivery_location: this.state.address,
            foodMap: this.state.foodsOrdered.map(f => ({
                quantity: f.quantity,
                res_username: this.state.restaurants[this.state.selectedRestaurant],
                foodname: f.food.foodname,
                price: f.food.price,
                type: f.food.foodType
            }))
        };
        this.props.createOrder(order);
        this.clearStates();
    }
    handleCancelCreateOrder() {
        this.props.cancelCreateOrder();
        this.clearStates();
    }

    clearStates() {
        this.setState(
            {
                selectedRestaurant: 0,
                foodsOnMenu: [],//foodModels
                selectedFood: 0,
                foodsOrdered: [],//{food: foodModel, quantity: number}
                address: '',
                tip: '',
                totalPrice: 0
            }
        );
    }

    getFoodsMenu() {
        getAllFoodOfRestaurant(this.state.restaurants[this.state.selectedRestaurant])
            .then((foods) => {
                this.setState({foodsOnMenu: foods});
            });
    }

    selectRestaurant(event, index, value) {
        this.setState({selectedRestaurant:value}, this.getFoodsMenu);
        this.setState({foodsOrdered: []}); //can only order from one restaurant. so clean previous restaurants orders
    }

    selectFood(event, index, value) {
        this.setState(
            {selectedFood:value},
            () => {
                let foodModel = this.state.foodsOnMenu[this.state.selectedFood];
                let i = _.findIndex(this.state.foodsOrdered, (f) => f.food.foodname===foodModel.foodname);
                if (i === -1) {
                    this.updateFoodsOrderedHelper([...this.state.foodsOrdered, {food: foodModel, quantity: 1}]);
                } else  {
                    let newFoodsOrdered = this.state.foodsOrdered;
                    newFoodsOrdered[i]['quantity'] += 1;
                    this.updateFoodsOrderedHelper(newFoodsOrdered);
                }
            }
        );
    }

    unselectFood(foodname) {
        let newFoodsOrdered = this.state.foodsOrdered;
        _.remove(newFoodsOrdered, f => f.food.foodname === foodname);
        this.updateFoodsOrderedHelper(newFoodsOrdered);
    }

    updateFoodsOrderedHelper(newFoods) {
        this.setState(
            {foodsOrdered: newFoods},
            this.calculatePriceHelper
        );
    }

    calculatePriceHelper() {
        this.setState(
            {
                totalPrice:
                    _.map(this.state.foodsOrdered, function (foodOrdered) {
                        return foodOrdered.quantity * foodOrdered.food.price;
                    }).reduce((x,y) => x+y,0)
            }
        );
    }

    enterAddress(event) { this.setState({address: event.target.value}); }

    enterTip(event) { this.setState({tip: parseInt(event.target.value, 10)}); }

    enterDate(event, value) {
        let datetime = moment(value);
        let formatted = datetime.format("YYYY-MM-D");
        console.log(formatted);
        this.setState({date: formatted});
    }

    enterTime(event, value) {
        let datetime = moment(value);
        let formatted = datetime.format("HH:mm:ss");
        console.log(formatted);
        this.setState({time: formatted});
    }

    restaurantAndFood = () =>
        (<div style={sectionStyle}>
            <span style={{display:'flex', flexDirection:'column'}}>
                <span style={titleTextStyle}>Restaurant to order from: </span>
                {
                    this.state.restaurants.length > 0 &&
                    <DropDownMenu value={this.state.selectedRestaurant}
                                  onChange={this.selectRestaurant}>
                        {this.state.restaurants.map(
                            (res, index) => <MenuItem key={index} value={index} primaryText={res} />
                        )}
                    </DropDownMenu>
                }
            </span>
            {
                this.state.foodsOnMenu.length > 0 &&
                <span style={{display:'flex', flexDirection:'column'}}>
                    <span style={titleTextStyle}>Foods on Menu: </span>
                    <DropDownMenu value={this.state.selectedFood}
                                  onChange={this.selectFood}>
                        {this.state.foodsOnMenu.map(
                            (food, index) => <MenuItem key={index} value={index} primaryText={food.foodname} />)}
                    </DropDownMenu>
                </span>
            }
        </div>);

    foodsOrdered = () => (
        <div style={{display: 'flex', flexDirection: 'column'}}>
            <span>Foods ordered: </span>
            <span style={chipsStyle}>
                {this.state.foodsOrdered.map(foodOrdered => {
                    return (
                        <Chip key={foodOrdered.food.foodname}
                            onRequestDelete={() => this.unselectFood(foodOrdered.food.foodname)}
                            style={chipStyle}
                        >
                            {foodOrdered.food.foodname + ' x '+foodOrdered.quantity}
                        </Chip>
                    );
                })}
            </span>
        </div>
    );

    addressAndTip = () =>
        (<div style={sectionStyle}>
            <TextField value={this.state.address}
                       hintText="your address..." onChange={this.enterAddress}/>
            <TextField type="number"
                       value={this.state.tip}
                       hintText="tip your deliverer..." onChange={this.enterTip}/>
        </div>);

    dateTime = () => (
        <div style={sectionStyle}>
            <DatePicker hintText="Date to deliver..." onChange={this.enterDate} />
            <TimePicker format="24hr" hintText="Time to deliver..." onChange={this.enterTime}/>
        </div>
    );


    render() {
        return (
            <Dialog
                title="Create new Order"
                actions={
                    [
                        <RaisedButton label="Cancel" onClick={this.handleCancelCreateOrder}/>,
                        <RaisedButton label="Create" primary={true} onClick={this.handleCreateOrder}/>
                    ]
                }
                modal={true}
                open={this.props.showCreateOrder}
            >
                <div>
                    <form>
                        {this.restaurantAndFood()}
                        <Divider style={dividerStyle} />
                        {this.foodsOrdered()}
                        <Divider style={dividerStyle} />
                        {this.addressAndTip()}
                        <Divider style={dividerStyle} />
                        {this.dateTime()}
                        <Divider style={dividerStyle} />
                        <div>Price: {this.state.totalPrice}</div>
                    </form>
                </div>
            </Dialog>
        );
    }
}