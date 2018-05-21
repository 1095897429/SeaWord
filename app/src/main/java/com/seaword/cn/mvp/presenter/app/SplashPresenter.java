package com.seaword.cn.mvp.presenter.app;

import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.mvp.contract.app.SplashContract;
import com.seaword.cn.network.RetrofitHelper;

import javax.inject.Inject;

/**
 * Created by zl on 2018/5/21.
 * 1.构造函数上需加@Inject
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter<SplashContract.View>{

    RetrofitHelper mRetrofitHelper;

    @Inject
    public SplashPresenter(RetrofitHelper retrofitHelper){
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getSpalshData() {

    }


    /** 5s 倒计时 */
    @Override
    public void setCountDown() {

    }
}
