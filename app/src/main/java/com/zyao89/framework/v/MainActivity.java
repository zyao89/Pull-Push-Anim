package com.zyao89.framework.v;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zyao89.framework.compact.ITestCompact;
import com.zyao89.framework.R;
import com.zyao89.framework.p.MainPresenter;
import com.zyao89.framework.router.IRouterUri;
import com.zyao89.framework.zcore.base.FragmentFactory;
import com.zyao89.framework.zcore.extra.swipeback.SwipeBackActivity;
import com.zyao89.framework.zrouter.ZRouter;

public class MainActivity extends SwipeBackActivity<ITestCompact.IPresenter> implements ITestCompact.IViewHandler
{
    private BlankFragment   blankFragment;
    private Blank22Fragment blank22Fragment;
    private boolean flag = false;

    @Override
    public void onPrepareCreate(Bundle savedInstanceState)
    {
        setStatusBarColor(Color.RED);
    }

    @Override
    public int loadLayout()
    {
        return R.layout.activity_main;
    }

    @Override
    public void initViews()
    {
        blank22Fragment = FragmentFactory.create(Blank22Fragment.class);
        loadRootFragment(R.id.content, blank22Fragment);

        blankFragment = FragmentFactory.create(BlankFragment.class);
        TextView btn1 = $(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("11111");
                pushFragment(R.id.content, (flag = !flag) ? blankFragment : blank22Fragment);
            }
        });
        TextView btn2 = $(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("22222");
                popFragment();
            }
        });
        TextView btn3 = $(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("33333");
                popToFirstFragment();
            }
        });
        TextView btn4 = $(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("44444");
                popAllFragment();
            }
        });
        TextView btn5 = $(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("55555");
//                removeFragment(blankFragment);
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
                IRouterUri routerUri = ZRouter.getInstance().create(IRouterUri.class);
                String haha = routerUri.jumpToGoodsDetail("1001", "haha");
                System.out.println("RRRR:  " + haha);
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
