package com.zyao89.framework.zcore.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyao89.framework.zcore.utils.FragmentManager;

/**
 * Fragment基类
 * Created by zyao89 on 2017/1/13.
 */
public abstract class BaseFragment<P> extends Fragment implements IBaseLifeCycleCompact<P>
{
    protected Activity           mActivity;
    protected P                  mPresenter;
    private   OnFragmentCallback mOnFragmentCallback;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onPrepareCreate(savedInstanceState);
        FragmentManager.getInstance().addFragment(this);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        @LayoutRes int layoutID = loadLayout();
        if (layoutID > 0)
        {
            return inflater.inflate(layoutID, container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if (view != null)
        {
            initViews();
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
    public final void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
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
    public final void onDestroyView()
    {
        super.onDestroyView();
        if (mPresenter != null && mPresenter instanceof BasePresenter)
        {
            ((BasePresenter) mPresenter).detachViewHandler();
        }
        onExit();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        FragmentManager.getInstance().removeFragment(this);
    }

    @Override
    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mActivity = activity;
        if (activity instanceof OnFragmentCallback)
        {
            mOnFragmentCallback = (OnFragmentCallback) activity;
        }
        else
        {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        onAttached(activity);
    }

    @Override
    public final void onAttach(Context context)
    {
        super.onAttach(context);
    }

    protected void onAttached(Context context)
    {

    }

    @Override
    public final void onDetach()
    {
        super.onDetach();
        mOnFragmentCallback = null;
        onDetached();
    }

    protected void onDetached()
    {

    }

    /**
     * 回调回给根信息
     *
     * @param msg
     */
    public void setCallbackRootMessage(Message msg)
    {
        if (mOnFragmentCallback != null)
        {
            mOnFragmentCallback.onFragmentDataCallback(msg);
        }
    }

    protected <V extends View> V $(@IdRes int viewId)
    {
        View view = getView();
        if (view == null)
        {
            return null;
        }
        return (V) getView().findViewById(viewId);
    }


    @Override
    public View getRootView()
    {
        return getView();
    }
}
