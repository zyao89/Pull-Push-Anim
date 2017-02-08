package com.zyao89.framework;

import android.app.Application;
import android.content.Context;

import com.zyao89.framework.zcore.app.ZCore;

/**
 * Created by zyao89 on 2017/2/8.
 */
public class App extends Application
{
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        ZCore.getInstance().init(this);
    }
}
