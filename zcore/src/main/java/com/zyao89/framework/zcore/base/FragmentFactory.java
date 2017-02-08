package com.zyao89.framework.zcore.base;

import android.os.Bundle;

/**
 * Fragment生产线
 * Created by zyao89 on 2017/1/13.
 */
public final class FragmentFactory
{

    public static <F extends BaseFragment> F create(Class<F> fClass)
    {
        return create(fClass, null);
    }

    public static <F extends BaseFragment> F create(Class<F> fClass, Bundle args)
    {
        try
        {
            F newInstance = fClass.newInstance();
            if (args != null)
            {
                newInstance.setArguments(args);
            }
            return newInstance;
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
