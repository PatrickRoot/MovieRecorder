import React, {Component} from 'react';

import {Button, Toast} from 'antd-mobile';

export default class extends Component {
    constructor(props) {
        super(props);
    }
    
    clickBtn() {
        Toast.success("You Clicked Button");
    }
    
    render() {
        return <Button onClick={this.clickBtn.bind(this)}>Start</Button>;
    }
}