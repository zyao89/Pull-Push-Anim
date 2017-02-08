package com.zyao89.framework.zcore.extra.swipeback;

/**
 * Created by zyao89 on 2017/2/8.
 */
public interface ISlideBackManager
{
    /**
     * 是否支持滑动返回
     *
     * @return
     */
    boolean supportSlideBack();

    /**
     * 能否滑动返回至当前Activity
     *
     * @return
     */
    boolean canBeSlideBack();
}
