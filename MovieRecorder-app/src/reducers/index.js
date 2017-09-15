/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/9/14 22:22
 * @author: Patrick <root@sixlab.cn>
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