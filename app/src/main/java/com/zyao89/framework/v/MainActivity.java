package com.zyao89.framework.v;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.zyao89.framework.compact.ITestCompact;
import com.zyao89.framework.R;
import com.zyao89.framework.p.MainPresenter;
import com.zyao89.framework.zcore.base.BaseActivity;
import com.zyao89.framework.zcore.base.FragmentFactory;
import com.zyao89.framework.zcore.extra.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity<ITestCompact.IPresenter> implements ITestCompact.IViewHandler
{
    private BlankFragment blankFragment;

    @Override
    public void onPrepareCreate(Bundle savedInstanceState)
    {
        setStatusBarColor(Color.YELLOW);
    }

    @Override
    public int loadLayout()
    {
        return R.layout.activity_main;
    }

    @Override
    public void initViews()
    {
        blankFragment = FragmentFactory.create(BlankFragment.class);
        TextView textView = $(R.id.haha);
        textView.setText("HEabcdeeH....");
        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("11111");
                pushFragments(R.id.content, blankFragment);
            }
        });
    }

    @Override
    public ITestCompact.IPresenter onCreatePresenter(Bundle savedInstanceState)
    {
        return new MainPresenter();
    }

    @Override
    public void onExit()
    {

    }
}
