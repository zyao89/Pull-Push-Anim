package com.zyao89.framework.zcore.extra.swipeback;

import android.view.MotionEvent;

import com.zyao89.framework.zcore.extra.BaseSupportActivity;


/**
 * 可滑动的Activity
 *
 * @param <P>
 */
public abstract class SwipeBackActivity<P> extends BaseSupportActivity<P> implements ISlideBackManager
{
    private SwipeBackHelper mSwipeBackHelper;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if (mSwipeBackHelper == null)
        {
            mSwipeBackHelper = new SwipeBackHelper(this);
        }
        return mSwipeBackHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean supportSlideBack()
    {
        return true;
    }

    @Override
    public boolean canBeSlideBack()
    {
        return true;
    }

    @Override
    public void finish()
    {
        if (mSwipeBackHelper != null)
        {
            mSwipeBackHelper.finishSwipeImmediately();
            mSwipeBackHelper = null;
        }
        super.finish();
    }
}
