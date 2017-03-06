package com.zyao89.framework.v;

import android.os.Bundle;

import com.zyao89.framework.R;
import com.zyao89.framework.zcore.extra.swipeback.SwipeBackActivity;

public class Main2Activity extends SwipeBackActivity
{

    @Override
    public int loadLayout()
    {
        return R.layout.activity_main2;
    }

    @Override
    public void initViews()
    {

    }

    @Override
    public Object onCreatePresenter(Bundle savedInstanceState)
    {
        return null;
    }

    @Override
    public void onExit()
    {

    }
}
