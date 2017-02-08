package com.zyao89.framework.zcore.base;

import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.zyao89.framework.zcore.R;
import com.zyao89.framework.zcore.utils.ActivityManager;
import com.zyao89.framework.zcore.utils.TranslucentBarStatusManager;

/**
 * Activity基类
 * Created by zyao89 on 2017/1/13.
 */
public abstract class BaseActivity<P> extends AppCompatActivity implements IBaseLifeCycleCompact<P>, OnFragmentCallback
{
    protected final String TAG = this.getClass().getSimpleName();
    protected P                           mPresenter;
    private   View                        mRootView;
    private   TranslucentBarStatusManager mTranslucentBarStatusManager;

    @Override
    public final void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initTranslucentBarStatus();
        onPrepareCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        @LayoutRes int layoutID = loadLayout();
        if (layoutID > 0)
        {
            mRootView = LayoutInflater.from(this).inflate(layoutID, null, false);
            if (mRootView != null)
            {
                setContentView(mRootView);
                fixFirstViewFitsSystemWindows();
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

    private void fixFirstViewFitsSystemWindows()
    {
        mTranslucentBarStatusManager.initTranslucentBarFitsSystemWindows();
    }

    /**
     * 初始化透明状态栏
     */
    private void initTranslucentBarStatus()
    {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mTranslucentBarStatusManager = new TranslucentBarStatusManager(this);
        mTranslucentBarStatusManager.initTranslucentBarStatus();
    }

    protected void setStatusBarColor(@ColorInt int color)
    {
        mTranslucentBarStatusManager.setStatusBarColor(color);
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
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
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
