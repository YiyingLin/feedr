import React, {Component} from "react";
import Dialog from 'material-ui/Dialog';
import RaisedButton from 'material-ui/RaisedButton';

const titleText = {
    fontWeight: 'bold'
};

export default class MyProfile extends Component {
    render() {
        return (
            <Dialog
                title="Profile"
                actions={
                    [
                        <RaisedButton label="Close" onClick={this.props.handleClose}/>
                    ]
                }
                modal={true}
                open={this.props.show}
            >
                <div>
                    <form>
                        <div>
                            <span style={titleText}>Food name:</span>
                            <span></span>
                        </div>
                        <div>
                            <span style={titleText}>Food price:</span>
                            <span></span>
                            {/*<TextField*/}
                            {/*hintText="how much?..."*/}
                            {/*type='number'*/}
                            {/*value={this.state.price}*/}
                            {/*fullWidth={true}*/}
                            {/*onChange={this.setPrice}*/}
                            {/*/>*/}
                        </div>
                        <div>
                            <span style={titleText}>Food type:</span>
                            <span></span>
                        </div>
                    </form>
                </div>
            </Dialog>
        );
    }
}
