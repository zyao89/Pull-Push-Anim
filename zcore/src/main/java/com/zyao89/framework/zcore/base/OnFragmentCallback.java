package com.zyao89.framework.zcore.base;

import android.os.Message;

/**
 * Fragment数据回调
 * Created by zyao89 on 2017/1/13.
 */
interface OnFragmentCallback
{
    /**
     * 与Activity交互接口
     * @param msg
     */
    void onFragmentDataCallback(Message msg);
}
