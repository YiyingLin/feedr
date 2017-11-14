import React, {Component} from 'react';
import FoodModel from "../../models/FoodModel";
import Food from "../../common/components/Food";
import FloatingActionButton from "material-ui/FloatingActionButton";
import ContentAdd from 'material-ui/svg-icons/content/add';
import FoodForm from "./FoodForm";

const manageFoodContainer = {
    display: 'flex',
    justifyContent: 'center',
    position: 'relative'
};

const foodListStyle = {
    marginTop: '20px',
    width: '50%',
    display: 'flex',
    flexDirection: 'column'
};

const addFoodStyle = {
    position: 'fixed',
    right: '10%',
    bottom: '10%'
};

const mockmenu = [
    new FoodModel('Chicken', 123, 'spicy'),
    new FoodModel('Rice', 12, 'not spicy'),
    new FoodModel('Water', 123, 'spicy'),
    new FoodModel('Ham', 12, 'not spicy')
];

export default class ManageFood extends Component {
    constructor(props) {
        super(props);
        this.state = {
            menu: mockmenu,
            showCreateFood: false
        };
        this.openCreateFood = this.openCreateFood.bind(this);
        this.cancelCreateFood = this.cancelCreateFood.bind(this);
        this.createFood = this.createFood.bind(this);
    }

    openCreateFood() {
        this.setState({showCreateFood: true});
    }

    cancelCreateFood() {
        this.setState({showCreateFood: false});
    }

    createFood() {
        this.setState({showCreateFood: false});
    }

    deleteFood(foodname) {
        console.log(`want to delete food ${foodname}`);
    }


    render() {
        return (
            <div style={manageFoodContainer}>
                <div style={foodListStyle}>
                    {
                        this.state.menu.map(foodModel =>
                            <Food key={foodModel.foodname}
                                  food={foodModel}
                                  handleDelete={()=>this.deleteFood(foodModel.foodname)}
                            />)
                    }
                </div>
                <FloatingActionButton style={addFoodStyle} onClick={this.openCreateFood}>
                    <ContentAdd />
                </FloatingActionButton>
                <FoodForm showCreateFood={this.state.showCreateFood}
                          cancelCreateFood={this.cancelCreateFood}
                          createFood={this.createFood}
                />
            </div>
        );
    }
}