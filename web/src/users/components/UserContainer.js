import React, {Component} from "react";
import SenderContainer from './SenderContainer';
import ReceiverContainer from './ReceiverContainer';
import {Tabs, Tab} from 'material-ui/Tabs';

export default class UserContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tabIndex: 0,
        };
        this.handleTabChange = this.handleTabChange.bind(this);
    }

    handleTabChange(index) {
        this.setState({tabIndex: index});
    }

    render() {
        return (
            <div>
                <Tabs
                    value={this.state.tabIndex}
                    onChange={this.handleTabChange}
                >
                    <Tab label="Sender" value={0}>
                        <SenderContainer />
                    </Tab>
                    <Tab label="Receiver" value={1}>
                        <ReceiverContainer onFocus={this.state.tabIndex===1} />
                    </Tab>
                </Tabs>
            </div>
        );
    }
}
