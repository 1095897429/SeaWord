package com.seaword.cn.mvp.presenter.home;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seaword.cn.base.BaseObjectSubscriber;
import com.seaword.cn.base.BaseSubscriber;
import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.bean.chase.ChaseBangumi;
import com.seaword.cn.bean.chase.RecommendBangumi;
import com.seaword.cn.mvp.contract.home.ChaseBangumiContract;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.network.response.HttpResponse;
import com.seaword.cn.rx.RxUtils;
import com.seaword.cn.utils.JsonUtils;

import org.json.JSONObject;
import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zl on 2018/5/29.
 */

public class ChaseBangumiPresenter  extends RxPresenter<ChaseBangumiContract.View> implements ChaseBangumiContract.Presenter<ChaseBangumiContract.View>{

    RetrofitHelper mRetrofitHelper;

    @Inject
    public ChaseBangumiPresenter(RetrofitHelper mRetrofitHelper){
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getChaseBangumiData() {
        /** 本地 通过JsonParse解析字符串*/
        addSubscribe(Flowable.just(JsonUtils.readJson("user_chase.json"))
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull String s) throws Exception {
                        Gson gson = new Gson();
                        JsonObject object = new JsonParser().parse(s).getAsJsonObject();
                        JsonObject result = object.getAsJsonObject("result");
                        ChaseBangumi chaseBangumi = gson.fromJson(result,ChaseBangumi.class);
                        mView.showChaseBangumi(chaseBangumi);
                        return  Flowable.just(JsonUtils.readJson("recommend_chase.json"));
                    }
                })
                .map(new Function<String, RecommendBangumi>() {
                    @Override
                    public RecommendBangumi apply(@NonNull String s) throws Exception {
                        Gson gson = new Gson();
                        JsonObject object = new JsonParser().parse(s).getAsJsonObject();
                        JsonObject result = object.getAsJsonObject("result");
                        RecommendBangumi recommendBangumi = gson.fromJson(result, RecommendBangumi.class);
                        return recommendBangumi;
                    }
                }).subscribeWith(new BaseSubscriber<RecommendBangumi>(mView) {
                    @Override
                    public void onSuccess(RecommendBangumi recommendBangumi) {
                        mView.showRecommendBangumi(recommendBangumi);
                    }
                }));

        /** 网络数据 有关于https的请求获取不到数据*/
        /*addSubscribe(mRetrofitHelper.getChaseBangumi()
                    .compose(RxUtils.<ChaseBangumi>handleResult())
                    .flatMap(new Function<ChaseBangumi, Publisher<HttpResponse<RecommendBangumi>>>() {
                        @Override
                        public Publisher<HttpResponse<RecommendBangumi>> apply(@NonNull ChaseBangumi chaseBangumi) throws Exception {
                            mView.showChaseBangumi(chaseBangumi);
                            return mRetrofitHelper.getRecommendBangumi();
                        }
                    })
                    .compose(RxUtils.<HttpResponse<RecommendBangumi>>rxSchedulerHelper())
                    .subscribeWith(new BaseObjectSubscriber<RecommendBangumi>(mView) {
                        @Override
                        public void onSuccess(RecommendBangumi recommendBangumi) {
                            mView.showRecommendBangumi(recommendBangumi);
                        }
                    }));*/

    }
}
