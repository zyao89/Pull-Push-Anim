package com.zyao89.framework.zcore.extra;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.zyao89.framework.zcore.base.BaseFragment;

/**
 * Created by zyao89 on 2017/1/20.
 */
public interface IFragmentMethod
{
    /**
     * 加载根（主）页面
     *
     * @param contentId
     * @param fragment
     */
    void loadRootFragment(@IdRes int contentId, @NonNull BaseFragment fragment);

    /**
     * 移除
     *
     * @param fragment
     */
    void removeFragment(@NonNull BaseFragment fragment);

    /**
     * 入栈
     *
     * @param contentId
     * @param fragment
     */
    void pushFragment(@IdRes int contentId, @NonNull BaseFragment fragment);

    /**
     * 出栈
     */
    void popFragment();

    /**
     * 全部出栈
     */
    void popAllFragment();

    /**
     * 出栈到第一个
     */
    void popToFirstFragment();
}
