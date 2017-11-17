import React, {Component} from 'react';
import Dialog from 'material-ui/Dialog';
import RaisedButton from 'material-ui/RaisedButton';
import Divider from 'material-ui/Divider';
import Slider from 'material-ui/Slider';
import TextField from 'material-ui/TextField';

const titleText = {
    fontWeight: 'bold'
};
const dividerStyle = {
    marginBottom: '20px'
};

const commentContainerStyle = {position: 'relative', top: '-40px'};

export default class RatingForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            senderRating: 0,
            restaurantRating: 0,
            commentSender: '',
            commentRestaurant: ''
        };
        this.rateSender = this.rateSender.bind(this);
        this.rateRestaurant = this.rateRestaurant.bind(this);
        this.commentSender = this.commentSender.bind(this);
        this.commentRestaurant = this.commentRestaurant.bind(this);
        this.clearStates = this.clearStates.bind(this);
        this.cancelRating = this.cancelRating.bind(this);
        this.createRating = this.createRating.bind(this);
    }

    clearStates() {
        this.setState({
            senderRating: 0,
            restaurantRating: 0,
            commentSender: '',
            commentRestaurant: ''
        });
    }

    rateSender(event, value) {
        this.setState({senderRating:value});
    }

    rateRestaurant(event, value) {
        this.setState({restaurantRating:value});
    }

    commentSender(event) {
        this.setState({commentSender: event.target.value});
    }

    commentRestaurant(event) {
        this.setState({commentRestaurant: event.target.value});
    }

    cancelRating() {
        this.props.handleCancelRating();
        this.clearStates();
    }

    createRating() {
        this.props.handleCreateRating({
            senderRating: this.state.senderRating,
            restaurantRating: this.state.restaurantRating,
            commentSender: this.state.commentSender,
            commentRestaurant: this.state.commentRestaurant
        });
        this.clearStates();
    }

    render() {
        return (
            <Dialog
                title="Rate this delivery"
                actions={
                    [
                        <RaisedButton label="Cancel" onClick={this.cancelRating}/>,
                        <RaisedButton label="Create" primary={true} onClick={this.createRating}/>
                    ]
                }
                modal={true}
                open={this.props.showRating}
            >
                <div>
                    <form>
                        <span style={titleText}>Rate sender: </span><span>{this.state.senderRating+' out of 10'}</span>
                        <Slider min={0} max={10} step={1} value={this.state.senderRating} onChange={this.rateSender}/>

                        <div style={commentContainerStyle}>
                            <span style={titleText}>Comment sender:</span><br/>
                            <TextField
                                hintText="I like this sender..."
                                value={this.state.commentSender}
                                fullWidth={true}
                                onChange={this.commentSender}
                            />
                        </div>

                        <Divider style={dividerStyle} />

                        <span style={titleText}>Rate restaurant: </span><span>{this.state.restaurantRating+' out of 10'}</span>
                        <Slider min={0} max={10} step={1} value={this.state.restaurantRating} onChange={this.rateRestaurant}/>

                        <div style={commentContainerStyle}>
                            <span style={titleText}>Comment restaurant:</span><br/>
                            <TextField
                                hintText="I like this restaurant..."
                                value={this.state.commentRestaurant}
                                fullWidth={true}
                                onChange={this.commentRestaurant}
                            />
                        </div>
                    </form>
                </div>
            </Dialog>
        );
    }
}