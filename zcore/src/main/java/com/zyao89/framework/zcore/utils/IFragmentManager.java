package com.zyao89.framework.zcore.utils;

import android.support.v4.app.Fragment;

/**
 * Created by zyao89 on 2017/1/14.
 */

public interface IFragmentManager {
    void addFragment(Fragment fragment);

    Fragment currentFragment();

    void removeLastFragment();

    void removeFragment(Fragment fragment);

    void removeFragment(Class<? extends Fragment> clazz);

    Fragment getFragment(Class<? extends Fragment> clazz);

    boolean containsFragment(Fragment fragment);

    void removeAllFragment();
}
