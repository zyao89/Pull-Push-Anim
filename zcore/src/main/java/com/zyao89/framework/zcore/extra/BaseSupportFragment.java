package com.zyao89.framework.zcore.extra;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.zyao89.framework.zcore.R;
import com.zyao89.framework.zcore.base.BaseFragment;
import com.zyao89.framework.zcore.base.IBasePresenter;

/**
 * 增强Fragment
 * Created by zyao89 on 2017/1/20.
 */
public abstract class BaseSupportFragment<P extends IBasePresenter> extends BaseFragment<P> implements IFragmentMethod
{
    @Override
    public final void pushFragments(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.move_right_in_activity, R.anim.move_left_out_activity, R.anim.move_left_in_activity, R.anim.move_right_out_activity);
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(contentId, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    @Override
    public final void popFragment(@NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

    protected void pushChildFragments(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.move_right_in_activity, R.anim.move_left_out_activity, R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        transaction.replace(contentId, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    protected final void popChildFragment(@NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }
}
