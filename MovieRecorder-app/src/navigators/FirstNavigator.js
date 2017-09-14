/**
 * Copyright (C) 2017 @sixlab, All Rights Reserved
 * Date: 2017/9/14
 * Time: 22:31
 * @author: Patrick <root@sixlab.cn>
 * @link: https://code.sixlab.cn/
 * @licence: GPLv3
 */
import {
    StackNavigator,
} from 'react-navigation';

import FirstList from '../pages/first/FirstList';
import FirstDetail from "../pages/first/FirstDetail";

export default FirstNavigator = StackNavigator({
    FirstList: {
        screen: FirstList,
    },
    FirstDetail: {
        screen: FirstDetail,
    },
}, {
    initialRouteParams: {
        testParam: function (msg) {
            alert(msg)
        }
    }
});