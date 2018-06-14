package com.seaword.cn.mvp.presenter.recommend;

import com.seaword.cn.base.BaseSubscriber;
import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.bean.recommend.AllStationRank;
import com.seaword.cn.mvp.contract.recommend.AllStationRankContract;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.rx.RxUtils;

import javax.inject.Inject;

/**
 * Created by zl on 2018/6/14.
 * 1.继承RxPresenter 是因为父类完成了和View的绑定关系
 * 2.在RetrofitHelper类中新增调用接口,并在它新增调用方法
 */

public class AllStationRankPresenter extends RxPresenter<AllStationRankContract.View> implements AllStationRankContract.Presenter<AllStationRankContract.View> {

    RetrofitHelper mRetrofitHelper;

    @Inject
    public AllStationRankPresenter(RetrofitHelper retrofitHelper){
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getAllStationRankData(String type) {
    BaseSubscriber<AllStationRank> subscriber =  mRetrofitHelper.getAllStationRank(type)
        .compose(RxUtils.<AllStationRank>rxSchedulerHelper())
        .subscribeWith(new BaseSubscriber<AllStationRank>(mView) {
            @Override
            public void onSuccess(AllStationRank allStationRank) {
                mView.showRankData(allStationRank.getRank().getList());
            }
        });

        addSubscribe(subscriber);
    }
}
