package com.zyao89.framework;

import android.app.Application;
import android.content.Context;

import com.zyao89.framework.zcore.app.ZCore;
import com.zyao89.framework.zrouter.ZRouter;

/**
 * Created by zyao89 on 2017/2/8.
 */
public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        ZRouter.getInstance().init(this);
        ZCore.getInstance().init(this);
    }
}
