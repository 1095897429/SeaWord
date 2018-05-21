package com.seaword.cn.base;

/**
 * Created by zl on 2018/5/21.
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;


    /** 在BaseActivity中调用此方法 */
    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
