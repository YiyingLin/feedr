import React, {Component} from "react";
import SenderContainer from './SenderContainer';
import ReceiverContainer from './ReceiverContainer';
import {Tabs, Tab} from 'material-ui/Tabs';

export default class UserContainer extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Tabs>
                    <Tab label="Sender" >
                        <SenderContainer />
                    </Tab>
                    <Tab label="Receiver" >
                        <ReceiverContainer />
                    </Tab>
                </Tabs>
            </div>
        );
    }
}
