package com.zyao89.framework.zcore.utils;

import android.support.v4.app.Fragment;

import java.util.Stack;

/**
 * Class: ActivityManager
 * Description: Activity管理类
 * Author: Zyao89
 * Time: 2016/7/19 20:07
 */
public class FragmentManager implements IFragmentManager {
    private static final String TAG = "ActivityManager";

    private static volatile IFragmentManager instance;
    private Stack<Fragment> mFragmentStack;

    private FragmentManager() {
        if (mFragmentStack == null) {
            mFragmentStack = new Stack<>();
        }
    }

    public static IFragmentManager getInstance() {
        if (instance == null) {
            synchronized (IActivityManager.class) {
                if (instance == null) {
                    instance = new FragmentManager();
                }
            }
        }
        return instance;
    }

    @Override
    public synchronized void addFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (containsFragment(fragment)) {
            return;
        }
        mFragmentStack.add(fragment);
    }

    @Override
    public synchronized Fragment currentFragment() {
        return mFragmentStack.lastElement();
    }

    @Override
    public synchronized void removeLastFragment() {
        removeFragment(mFragmentStack.lastElement());
    }

    @Override
    public synchronized void removeFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        mFragmentStack.remove(fragment);
    }

    @Override
    public synchronized void removeFragment(Class<? extends Fragment> clazz) {
        if (null == clazz) {
            return;
        }
        for (Fragment fragment : mFragmentStack) {
            if (fragment.getClass().equals(clazz)) {
                removeFragment(fragment);
            }
        }
    }

    @Override
    public synchronized Fragment getFragment(Class<? extends Fragment> clazz) {
        if (null == clazz) {
            return null;
        }
        for (Fragment fragment : mFragmentStack) {
            if (fragment.getClass().equals(clazz)) {
                return fragment;
            }
        }
        return null;
    }

    @Override
    public synchronized boolean containsFragment(Fragment fragment) {
        if (null == fragment) {
            return false;
        }
        return mFragmentStack.contains(fragment);
    }

    @Override
    public synchronized void removeAllFragment() {
        mFragmentStack.clear();
    }
}
