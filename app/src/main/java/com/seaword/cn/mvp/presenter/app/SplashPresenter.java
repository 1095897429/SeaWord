package com.seaword.cn.mvp.presenter.app;

import com.seaword.cn.base.BaseSubscriber;
import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.bean.app.Splash;
import com.seaword.cn.mvp.contract.app.SplashContract;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.rx.RxUtils;
import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.NewThreadScheduler;

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
        BaseSubscriber<Splash> subscriber = mRetrofitHelper.getSplash()
                .compose(RxUtils.<Splash>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<Splash>(mView) {
                    @Override
                    public void onSuccess(Splash splash) {
                       if(splash.getCode() == 0){
                           mView.showSplash(splash);
                       }
                    }
                });
        addSubscribe(subscriber);
    }


    /** 5s 倒计时 */
    @Override
    public void setCountDown() {
        final Long count = 5L;
        Disposable disposable = Flowable.interval(0,1, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .take(count + 1)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.showCountDown(aLong.intValue());
                    }
                });
        addSubscribe(disposable);
    }
}
