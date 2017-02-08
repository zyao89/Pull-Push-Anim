package com.zyao89.framework.zcore.base;

import android.os.Bundle;
import android.view.View;

/**
 * Activity协议
 * Created by zyao89 on 2017/1/13.
 */
interface IBaseLifeCycleCompact<P>
{

    void onPrepareCreate(Bundle savedInstanceState);

    int loadLayout();

    void initViews();

    P onCreatePresenter(Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);

    void onExit();

    View getRootView();
}
