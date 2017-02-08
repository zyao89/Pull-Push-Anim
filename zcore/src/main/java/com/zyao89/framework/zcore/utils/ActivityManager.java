package com.zyao89.framework.zcore.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Class: ActivityManager
 * Description: Activity管理类
 * Author: Zyao89
 * Time: 2016/7/19 20:07
 */
public class ActivityManager implements IActivityManager
{
    private static final String TAG = "ActivityManager";
    private static volatile IActivityManager instance;
    private                 Stack<Activity>  mActivityStack;

    private ActivityManager()
    {
        if (mActivityStack == null)
        {
            mActivityStack = new Stack<>();
        }
    }

    public static IActivityManager getInstance()
    {
        if (instance == null)
        {
            synchronized (IActivityManager.class)
            {
                if (instance == null)
                {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    @Override
    public synchronized void addActivity(Activity activity)
    {
        if (activity == null)
        {
            return;
        }
        if (containsActivity(activity))
        {
            return;
        }
        mActivityStack.add(activity);
    }

    @Override
    public synchronized Activity currentActivity()
    {
        return mActivityStack.lastElement();
    }

    @Override
    public synchronized void removeLastActivity()
    {
        removeActivity(mActivityStack.lastElement());
    }

    @Override
    public synchronized void removeActivity(Activity activity)
    {
        if (activity == null)
        {
            return;
        }
        mActivityStack.remove(activity);
    }

    @Override
    public synchronized void removeActivity(Class<? extends Activity> clazz)
    {
        if (null == clazz)
        {
            return;
        }
        for (Activity activity : mActivityStack)
        {
            if (activity.getClass().equals(clazz))
            {
                removeActivity(activity);
            }
        }
    }

    @Override
    public synchronized Activity getActivity(Class<? extends Activity> clazz)
    {
        if (null == clazz)
        {
            return null;
        }
        for (Activity activity : mActivityStack)
        {
            if (activity.getClass().equals(clazz))
            {
                return activity;
            }
        }
        return null;
    }

    @Override
    public synchronized boolean containsActivity(Activity activity)
    {
        if (null == activity)
        {
            return false;
        }
        return mActivityStack.contains(activity);
    }

    @Override
    public synchronized void finishAllActivity()
    {
        for (int i = 0, size = mActivityStack.size(); i < size; i++)
        {
            if (null != mActivityStack.get(i))
            {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    @Override
    public synchronized Activity getLatestActivity()
    {
        int count = mActivityStack.size();
        if (count == 0)
        {
            return null;
        }
        return mActivityStack.get(count - 1);
    }

    @Override
    public synchronized Activity getPreviousActivity()
    {
        int count = mActivityStack.size();
        if (count < 2)
        {
            return null;
        }
        return mActivityStack.get(count - 2);
    }
}
