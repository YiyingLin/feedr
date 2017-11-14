import React, {Component} from 'react';
import Dialog from 'material-ui/Dialog';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';


const titleText = {
    fontWeight: 'bold'
};

export default class FoodForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            foodname: '',
            price: '',
            foodType: ''
        };
        this.cancelCreateFoodWrapper = this.cancelCreateFoodWrapper.bind(this);
        this.createFoodWrapper = this.createFoodWrapper.bind(this);
        this.setFoodname = this.setFoodname.bind(this);
        this.setPrice = this.setPrice.bind(this);
    }

    setFoodname(event) {
        this.setState({foodname: event.target.value});
    }

    setPrice(event) {
        this.setState({price: parseInt(event.target.value, 10)});
    }

    setFoodType(event) {
        this.setState({foodType: event.target.value});
    }

    cancelCreateFoodWrapper() {
        this.props.cancelCreateFood();
    }

    createFoodWrapper() {
        this.props.createFood();
    }

    render() {
        return (
            <Dialog
                title="Add new food to your menu"
                actions={
                    [
                        <RaisedButton label="Cancel" onClick={this.cancelCreateFoodWrapper}/>,
                        <RaisedButton label="Create" primary={true} onClick={this.createFoodWrapper}/>
                    ]
                }
                modal={true}
                open={this.props.showCreateFood}
            >
                <div>
                    <form>
                        <div>
                            <span style={titleText}>Food name:</span><br/>
                            <TextField
                                hintText="Spicy chicken..."
                                value={this.state.foodname}
                                fullWidth={true}
                                onChange={this.setFoodname}
                            />
                        </div>
                        <div>
                            <span style={titleText}>Food price:</span><br/>
                            <TextField
                                hintText="how much?..."
                                type='number'
                                value={this.state.price}
                                fullWidth={true}
                                onChange={this.setPrice}
                            />
                        </div>
                        <div>
                            <span style={titleText}>Food type:</span><br/>
                            <TextField
                                hintText="non spicy..."
                                value={this.state.foodType}
                                fullWidth={true}
                                onChange={this.setFoodType}
                            />
                        </div>
                    </form>
                </div>
            </Dialog>
        );
    }
}