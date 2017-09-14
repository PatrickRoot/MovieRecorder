/**
 * Copyright (C) 2017 @sixlab, All Rights Reserved
 * Date: 2017/9/14
 * Time: 21:40
 * @author: Patrick <root@sixlab.cn>
 * @link: https://code.sixlab.cn/
 * @licence: GPLv3
 */
import React from 'react';
import {
    Provider,
} from 'react-redux';
import {
    ActivityIndicator,
} from 'antd-mobile';

import configureStore from './src/store/index';
import {checkLogin} from "./src/actions/UserAction";

import LoginScreen from "./src/LoginScreen";
import ContentScreen from './src/ContentScreen';

globalStore = null;

export default class App extends React.Component {
    constructor(props) {
        super(props);
        let that = this;
        globalStore = configureStore(() => {
            that.setState({isLoading: false})
        });

        this.state = {
            isLoading: true
        }
    }

    componentDidMount() {
        globalStore.dispatch(checkLogin());
    }

    render() {
        if (this.state.isLoading || globalStore.getState().UserStore.isChecking ) {
            return (
                <View style={styles.container}>
                    <ActivityIndicator
                        text="Loading..."
                        size="large" />
                </View>
            );
        }
        return (
            <Provider store={globalStore}>
                {
                    globalStore.getState().UserStore.isLoggedIn ?
                        <ContentScreen/>
                        : <LoginScreen/>
                }
            </Provider>
        );
    }
}