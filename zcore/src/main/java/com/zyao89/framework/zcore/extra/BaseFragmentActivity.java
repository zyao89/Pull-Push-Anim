package com.zyao89.framework.zcore.extra;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.zyao89.framework.zcore.R;
import com.zyao89.framework.zcore.base.BaseActivity;
import com.zyao89.framework.zcore.base.BaseFragment;
import com.zyao89.framework.zcore.base.IBasePresenter;

/**
 * FragmentActivity
 * Created by zyao89 on 2017/1/20.
 */
public abstract class BaseFragmentActivity<P extends IBasePresenter> extends BaseActivity<P> implements IFragmentMethod
{
    @Override
    public final void pushFragments(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.move_right_in_activity, R.anim.move_left_out_activity, R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        transaction.replace(contentId, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    @Override
    public final void popFragment(@NonNull BaseFragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }
}
