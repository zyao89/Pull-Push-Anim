package com.zyao89.framework.zcore.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * 状态栏管理类
 * Created by zyao89 on 2017/1/22.
 */
public class TranslucentBarStatusManager
{
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
    private final Activity mActivity;
    private       View     mStatusBarTintView;
    private       int      mStatusBarHeight;

    public TranslucentBarStatusManager(Activity activity)
    {
        mActivity = activity;
        Resources res = mActivity.getResources();
        mStatusBarHeight = getInternalDimensionSize(res, STATUS_BAR_HEIGHT_RES_NAME);
    }

    /**
     * 初始化透明状态栏
     */
    public void initTranslucentBarStatus()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window window = mActivity.getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 初始化透明导航栏
     */
    public void initTranslucentNavigationStatus()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window window = mActivity.getWindow();
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 初始化第一个View的FitsSystemWindows
     */
    public void initTranslucentBarFitsSystemWindows()
    {
        final View view = mActivity.findViewById(Window.ID_ANDROID_CONTENT);
        if (view != null && view instanceof ViewGroup)
        {
            final ViewGroup contentFrameLayout = (ViewGroup) view;
            if (contentFrameLayout.getChildCount() > 0)
            {
                final View parentView = contentFrameLayout.getChildAt(0);
                if (parentView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                {
                    parentView.setFitsSystemWindows(true);
                }
            }
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 颜色
     */
    public void setStatusBarColor(@ColorInt int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            mActivity.getWindow().setStatusBarColor(color);
        }
        else
        {
            setupStatusBarViewColor(color);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight()
    {
        return mStatusBarHeight;
    }

    private void setupStatusBarViewColor(@ColorInt int color)
    {
        Window win = mActivity.getWindow();
        ViewGroup decorViewGroup = (ViewGroup) win.getDecorView();
        if (mStatusBarTintView == null)
        {
            mStatusBarTintView = new View(mActivity);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, mStatusBarHeight);
            params.gravity = Gravity.TOP;
            mStatusBarTintView.setLayoutParams(params);
            decorViewGroup.addView(mStatusBarTintView);
            mStatusBarTintView.setVisibility(View.VISIBLE);
        }
        mStatusBarTintView.setBackgroundColor(color);
    }

    private int getInternalDimensionSize(Resources res, String key)
    {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0)
        {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
