package com.seaword.cn.mvp.presenter.home;

import com.seaword.cn.base.BaseObjectSubscriber;
import com.seaword.cn.base.BaseSubscriber;
import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.bean.live.LivePartition;
import com.seaword.cn.bean.live.LiveRecommend;
import com.seaword.cn.mvp.contract.home.LiveContract;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.network.response.HttpResponse;
import com.seaword.cn.rx.RxUtils;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zl on 2018/5/25.
 */

public class LivePresenter extends RxPresenter<LiveContract.View> implements LiveContract.Presenter<LiveContract.View>{

    RetrofitHelper mRetrofitHelper;

    @Inject
    public LivePresenter(RetrofitHelper mRetrofitHelper){
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getLiveData() {
        /** 避免嵌套调用，用flatmap，并统一返回结果处理*/
       addSubscribe(mRetrofitHelper.getLivePartition()
               .compose(RxUtils.<LivePartition>handleResult())
               .flatMap(new Function<LivePartition, Publisher<HttpResponse<LiveRecommend>>>() {
                   @Override
                   public Publisher<HttpResponse<LiveRecommend>> apply(@NonNull LivePartition livePartition) throws Exception {
                       mView.showLivePartition(livePartition);
                       return mRetrofitHelper.getLiveRecommend();
                   }
               })
               .compose(RxUtils.<HttpResponse<LiveRecommend>>rxSchedulerHelper())
               .subscribeWith(new BaseObjectSubscriber<LiveRecommend>(mView) {
                   @Override
                   public void onSuccess(LiveRecommend liveRecommend) {
                       mView.showLiveRecommend(liveRecommend);
                   }
               }));
    }
}
