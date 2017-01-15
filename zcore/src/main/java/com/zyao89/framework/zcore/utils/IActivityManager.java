package com.zyao89.framework.zcore.utils;

import android.app.Activity;

/**
 * Interface: IActivityManager
 * Description: activity收集工具，用于退出应用
 * Author: Zyao89
 * Time: 2016/7/19 20:03
 */
public interface IActivityManager
{
    void addActivity (Activity activity);

    Activity currentActivity();

    void removeLastActivity();

    void removeActivity (Activity activity);

    void removeActivity (Class<? extends Activity> clazz);

    Activity getActivity (Class<? extends Activity> clazz);

    boolean containsActivity (Activity activity);

    void finishAllActivity();
}
