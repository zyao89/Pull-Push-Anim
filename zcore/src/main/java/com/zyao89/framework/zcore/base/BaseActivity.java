package com.zyao89.framework.zcore.base;

import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.zyao89.framework.zcore.utils.ActivityManager;

/**
 * Activity基类
 * Created by zyao89 on 2017/1/13.
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IViewCompact<P>, IBaseViewHandler, OnFragmentCallback
{
    protected P    mPresenter;
    private   View mRootView;

    @Override
    public final void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onPrepareCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        @LayoutRes int layoutID = loadLayout();
        if (layoutID > 0)
        {
            mRootView = LayoutInflater.from(this).inflate(layoutID, null, false);
            if (mRootView != null)
            {
                setContentView(mRootView);
                initViews();
            }
        }
        if (mPresenter == null)
        {
            mPresenter = onCreatePresenter(savedInstanceState);
        }
        if (mPresenter instanceof BasePresenter)
        {
            ((BasePresenter) mPresenter).attachViewHandler(this);
        }
    }

    @Override
    public void onPrepareCreate(Bundle savedInstanceState)
    {
        // override
    }

    @Override
    public void initData(Bundle savedInstanceState)
    {
        // override
    }

    @Override
    public void onFragmentDataCallback(Message msg)
    {
        // override
    }

    @Override
    protected final void onDestroy()
    {
        super.onDestroy();
        onExit();
        if (mPresenter != null && mPresenter instanceof BasePresenter)
        {
            ((BasePresenter) mPresenter).detachViewHandler();
        }
        ActivityManager.getInstance().removeActivity(this);
    }

    protected <V extends View> V $(@IdRes int viewId)
    {
        return (V) findViewById(viewId);
    }

    @Override
    public View getRootView()
    {
        return mRootView;
    }
}
