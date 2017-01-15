package com.zyao89.framework.v;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.zyao89.framework.compact.ITestCompact;
import com.zyao89.framework.R;
import com.zyao89.framework.p.MainPresenter;
import com.zyao89.framework.zcore.base.BaseActivity;
import com.zyao89.framework.zcore.base.FragmentFactory;

public class MainActivity extends BaseActivity<ITestCompact.IPresenter> implements ITestCompact.IViewHandler
{
    private BlankFragment blankFragment;

    @Override
    public void onPrepareCreate(Bundle savedInstanceState)
    {

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
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                int id = R.id.content;
                transaction.replace(id, blankFragment);
                transaction.addToBackStack(blankFragment.getClass().getSimpleName());
                transaction.commit();
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
