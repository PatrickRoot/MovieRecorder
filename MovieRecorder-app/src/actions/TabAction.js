/**
 * Copyright (C) 2017 @sixlab, All Rights Reserved
 * Date: 2017/9/14
 * Time: 21:46
 * @author: Patrick <root@sixlab.cn>
 * @link: https://code.sixlab.cn/
 * @licence: GPLv3
 */
import {TAB_CHANGE} from "../constants/types";

export function changeTab(tabName) {
    return {
        'type': TAB_CHANGE,
        'tabName': tabName,
    }
}