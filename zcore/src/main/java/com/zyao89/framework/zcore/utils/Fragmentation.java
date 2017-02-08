package com.zyao89.framework.zcore.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zyao89.framework.zcore.R;
import com.zyao89.framework.zcore.base.BaseFragment;

/**
 * Fragment跳转操作
 * Created by zyao89 on 2017/2/7.
 */
public final class Fragmentation
{
    public void loadRootFragment(@NonNull FragmentManager fragmentManager, @IdRes int contentId, @NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(contentId, fragment);
        transaction.commitAllowingStateLoss();
    }

    public void pushFragment(@NonNull FragmentManager fragmentManager, @IdRes int contentId, @NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.move_right_in_activity, R.anim.move_left_out_activity, R.anim.move_left_in_activity, R.anim.move_right_out_activity);
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(contentId, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    public void removeFragment(@NonNull FragmentManager fragmentManager, @NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

    public void popFragment(@NonNull FragmentManager fragmentManager)
    {
        fragmentManager.popBackStack();
    }

    public void popAllFragment(@NonNull FragmentManager fragmentManager)
    {
        while (fragmentManager.popBackStackImmediate()) { ; }
    }

    public void popToFirstFragment(@NonNull FragmentManager fragmentManager)
    {
        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 1; i < count; i++)
        {
            popFragment(fragmentManager);
        }
    }
}
