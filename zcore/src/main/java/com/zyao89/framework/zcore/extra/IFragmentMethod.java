package com.zyao89.framework.zcore.extra;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.zyao89.framework.zcore.base.BaseFragment;

/**
 * Created by zyao89 on 2017/1/20.
 */
public interface IFragmentMethod
{
    void pushFragments(@IdRes int contentId, @NonNull BaseFragment fragment);

    void popFragment(@NonNull BaseFragment fragment);
}
