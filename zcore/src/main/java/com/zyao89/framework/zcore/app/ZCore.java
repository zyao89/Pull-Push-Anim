package com.zyao89.framework.zcore.app;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Created by zyao89 on 2017/2/8.
 */
public class ZCore
{
    private static ZCore mSingle;

    private ZCore()
    {
    }

    public static ZCore getInstance()
    {
        if (mSingle == null)
        {
            synchronized (ZCore.class)
            {
                if (mSingle == null)
                {
                    mSingle = new ZCore();
                }
            }
        }
        return mSingle;
    }

    public void init(@NonNull Application application)
    {
    }
}
