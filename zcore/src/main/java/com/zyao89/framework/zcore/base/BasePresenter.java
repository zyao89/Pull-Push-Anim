package com.zyao89.framework.zcore.base;

/**
 * 中间件基类
 * Created by zyao89 on 2017/1/13.
 */
public abstract class BasePresenter<V>
{
    private V mViewHandler;

    void attachViewHandler(V viewHandler)
    {
        mViewHandler = viewHandler;
    }

    void detachViewHandler()
    {
        mViewHandler = null;
    }

    protected V getViewHandler()
    {
        return mViewHandler;
    }
}
