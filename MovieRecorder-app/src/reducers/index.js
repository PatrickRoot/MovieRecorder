/**
 * Copyright (C) 2017 @sixlab, All Rights Reserved
 * Date: 2017/9/14
 * Time: 22:22
 * @author: Patrick <root@sixlab.cn>
 * @link: https://code.sixlab.cn/
 * @licence: GPLv3
 */
import {combineReducers} from 'redux';

import UserReducer from './UserReducer';
import TabReducer from './TabReducer';
import NavFirstReducer from './NavFirstReducer';

export default combineReducers({
    UserStore: UserReducer,
    TabStore: TabReducer,
    NavFirstStore: NavFirstReducer,
});