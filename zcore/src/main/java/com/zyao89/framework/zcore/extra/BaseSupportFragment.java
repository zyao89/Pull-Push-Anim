package com.zyao89.framework.zcore.extra;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.zyao89.framework.zcore.base.BaseFragment;
import com.zyao89.framework.zcore.utils.Fragmentation;

/**
 * 增强Fragment
 * Created by zyao89 on 2017/1/20.
 */
public abstract class BaseSupportFragment<P> extends BaseFragment<P> implements IFragmentMethod
{
    private Fragmentation mFragmentation;

    @Override
    protected void onAttached(Context context)
    {
        super.onAttached(context);
        if (mActivity != null && mActivity instanceof BaseSupportActivity)
        {
            mFragmentation = ((BaseSupportActivity) mActivity).getFragmentation();
        }
        if (mFragmentation == null)
        {
            mFragmentation = new Fragmentation();
        }
    }

    @Override
    public void loadRootFragment(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        mFragmentation.loadRootFragment(getFragmentManager(), contentId, fragment);
    }

    @Override
    public final void removeFragment(@NonNull BaseFragment fragment)
    {
        mFragmentation.removeFragment(getFragmentManager(), fragment);
    }

    @Override
    public final void pushFragment(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        mFragmentation.pushFragment(getFragmentManager(), contentId, fragment);
    }

    @Override
    public void popFragment()
    {
        mFragmentation.popFragment(getFragmentManager());
    }

    @Override
    public void popAllFragment()
    {
        mFragmentation.popAllFragment(getFragmentManager());
    }

    @Override
    public void popToFirstFragment()
    {
        mFragmentation.popToFirstFragment(getFragmentManager());
    }

    protected final void pushChildFragments(@IdRes int contentId, @NonNull BaseFragment fragment)
    {
        mFragmentation.pushFragment(getChildFragmentManager(), contentId, fragment);
    }

    protected final void popChildFragment(@NonNull BaseFragment fragment)
    {
        mFragmentation.removeFragment(getChildFragmentManager(), fragment);
    }

    protected final void popChildAll()
    {
        mFragmentation.popAllFragment(getChildFragmentManager());
    }

    protected final void popChildToFirst()
    {
        mFragmentation.popToFirstFragment(getChildFragmentManager());
    }
}
