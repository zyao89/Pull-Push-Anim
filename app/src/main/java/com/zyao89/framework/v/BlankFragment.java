package com.zyao89.framework.v;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.zyao89.framework.compact.ITestCompact;
import com.zyao89.framework.R;
import com.zyao89.framework.p.MainPresenter;
import com.zyao89.framework.zcore.base.BaseFragment;
import com.zyao89.framework.zcore.base.FragmentFactory;


public class BlankFragment extends BaseFragment<ITestCompact.IPresenter> {

    @Override
    public void onPrepareCreate(Bundle savedInstanceState)
    {
        super.onPrepareCreate(savedInstanceState);
        if (getArguments() != null)
        {
            Bundle arguments = getArguments();
            String haha = arguments.getString("haha");
            System.out.println(haha);
        }
    }

    @Override
    public int loadLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    public void initViews() {
        final Blank22Fragment blankFragment = FragmentFactory.create(Blank22Fragment.class);
        TextView textView = $(R.id.haha);
        textView.setText("HEabcdeeH...");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("11111");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.content, blankFragment);
                transaction.addToBackStack(blankFragment.getClass().getSimpleName());
                transaction.commit();
            }
        });

    }

    @Override
    public ITestCompact.IPresenter onCreatePresenter(Bundle savedInstanceState) {
        return new MainPresenter();
    }

    @Override
    public void onExit() {

    }
}
