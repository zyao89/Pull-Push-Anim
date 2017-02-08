package com.zyao89.framework.zcore.extra;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.zyao89.framework.zcore.base.BaseActivity;
import com.zyao89.framework.zcore.base.BaseFragment;
import com.zyao89.framework.zcore.utils.Fragmentation;

/**
 * FragmentActivity
 * Created by zyao89 on 2017/1/20.
 */
public abstract class BaseSupportActivity<P> extends BaseActivity<P> implements IFragmentMethod
{
    private final Fragmentation mFragmentation;

    public BaseSupportActivity()
    {
        super();
        mFragmentation = new Fragmentation();
    }

    Fragmentation getFragmentation()
    {
        return mFragmentation;
    }

    @Override
    public void loadRootFragment(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        mFragmentation.loadRootFragment(getSupportFragmentManager(), contentId, fragment);
    }

    @Override
    public final void removeFragment(@NonNull BaseFragment fragment)
    {
        mFragmentation.removeFragment(getSupportFragmentManager(), fragment);
    }

    @Override
    public final void pushFragment(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        mFragmentation.pushFragment(getSupportFragmentManager(), contentId, fragment);
    }

    @Override
    public void popFragment()
    {
        mFragmentation.popFragment(getSupportFragmentManager());
    }

    @Override
    public void popAllFragment()
    {
        mFragmentation.popAllFragment(getSupportFragmentManager());
    }

    @Override
    public void popToFirstFragment()
    {
        mFragmentation.popToFirstFragment(getSupportFragmentManager());
    }
}
