package com.zyao89.framework.v;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.zyao89.framework.R;
import com.zyao89.framework.compact.ITestCompact;
import com.zyao89.framework.p.MainPresenter;
import com.zyao89.framework.zcore.base.BaseFragment;
import com.zyao89.framework.zcore.base.FragmentFactory;


public class Blank22Fragment extends BaseFragment<ITestCompact.IPresenter> implements ITestCompact.IViewHandler
{

    @Override
    public int loadLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    public void initViews() {
        TextView textView = $(R.id.haha);
        textView.setText("HEabcdeeH.222222222...");
        getRootView().setBackgroundColor(Color.YELLOW);
    }

    @Override
    public ITestCompact.IPresenter onCreatePresenter(Bundle savedInstanceState) {
        return new MainPresenter();
    }

    @Override
    public void onExit() {

    }


}
