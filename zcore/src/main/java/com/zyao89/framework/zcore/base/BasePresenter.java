package com.zyao89.framework.zcore.base;

/**
 * Created by zyao89 on 2017/1/13.
 */

public abstract class BasePresenter<V extends IBaseViewHandler> implements IBasePresenter {

    private V mViewHandler;

    void attachViewHandler(V viewHandler) {
        mViewHandler = viewHandler;
    }

    void detachViewHandler() {
        mViewHandler = null;
    }
}
