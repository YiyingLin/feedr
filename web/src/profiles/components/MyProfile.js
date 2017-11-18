import React, {Component} from "react";
import Dialog from 'material-ui/Dialog';
import RaisedButton from 'material-ui/RaisedButton';
import UserTypes from "../../utils/UserTypes";
import {deleteProfile} from "../services/ProfileHttpServices";
const Cookie = require('js-cookie');

const titleText = {
    fontWeight: 'bold'
};

export default class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.deleteOwnProfile = this.deleteOwnProfile.bind(this);
    }

    deleteOwnProfile() {
        deleteProfile()
            .then(function () {
                Cookie.remove("username");
                Cookie.remove("userType");
                window.location.reload();
            })
            .catch(function (err) {
                alert(JSON.parse(err))
            });
    }

    render() {
        return (
            <Dialog
                title="Profile"
                actions={
                    [
                        <RaisedButton secondary={true} label="Delete my profile" onClick={this.deleteOwnProfile}/>,
                        <RaisedButton label="Close" onClick={this.props.handleClose}/>
                    ]
                }
                modal={true}
                open={this.props.show}
            >
                <div>
                    <div>
                        <span style={titleText}>Username:</span>
                        <span>{this.props.profile.username}</span>
                    </div>
                    <div>
                        <span style={titleText}>Phone: </span>
                        <span>{this.props.profile.phone}</span>
                    </div>
                    {
                        Cookie.get("userType") === UserTypes.RESTAURANT ?
                            <div>
                                {/*<div>*/}
                                    {/*<span style={titleText}>Restaurant name</span>*/}
                                    {/*<span>{this.props.profile.res_name}</span>*/}
                                {/*</div>*/}
                                <div>
                                    <span style={titleText}>Restaurant rating: </span>
                                    <span>{this.props.profile.res_rating}</span>
                                </div>
                                <div>
                                    <span style={titleText}>Restaurant location: </span>
                                    <span>{this.props.profile.res_location}</span>
                                </div>
                            </div> :
                            <div>
                                <div>
                                    <span style={titleText}>Sender rating: </span>
                                    <span>{this.props.profile.sender_rating}</span>
                                </div>
                                <div>
                                    <span style={titleText}>Sender location: </span>
                                    <span>{this.props.profile.sender_location}</span>
                                </div>
                            </div>
                    }
                </div>
            </Dialog>
        );
    }
}
