package com.seaword.cn.mvp.presenter.home;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seaword.cn.base.BaseListSubscriber;
import com.seaword.cn.base.BaseSubscriber;
import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.bean.region.Region;
import com.seaword.cn.bean.region.RegionType;
import com.seaword.cn.mvp.contract.home.RegisonContract;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.network.response.HttpResponse;
import com.seaword.cn.rx.RxUtils;
import com.seaword.cn.utils.JsonUtils;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zl on 2018/5/30.
 */

public class RegisonPresenter extends RxPresenter<RegisonContract.View> implements RegisonContract.Presenter<RegisonContract.View> {
    RetrofitHelper mRetrofitHelper;

    @Inject
    public RegisonPresenter(RetrofitHelper mRetrofitHelper){
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getRegionData() {
        /** 网络*/
       /*addSubscribe(Flowable.just(JsonUtils.readJson("regiontype.json"))
                .flatMap(new Function<String, Publisher<HttpResponse<List<Region>>>>() {
                    @Override
                    public Publisher<HttpResponse<List<Region>>> apply(@NonNull String s) throws Exception {
                        Gson gson = new Gson();
                        JsonObject jsonObject =  new JsonParser().parse(s).getAsJsonObject();
                        JsonArray array = jsonObject.getAsJsonArray("data");
                        List<RegionType> regionTypes = new ArrayList<>();
                        for (JsonElement jsonElement : array) {
                            regionTypes.add(gson.fromJson(jsonElement, RegionType.class));
                        }
                        mView.showRegionTypeData(regionTypes);
                        return mRetrofitHelper.getRegion();
                    }
                })
                .compose(RxUtils.<HttpResponse<List<Region>>>rxSchedulerHelper())
                .subscribeWith(new BaseListSubscriber<Region>(mView) {
                    @Override
                    public void onSuccess(List<Region> t) {
                        mView.showRegionData(t);
                    }
                }));*/

        /** 本地 -- 新增了topic ,activity数据 */
        addSubscribe(Flowable.just(JsonUtils.readJson("regiontype.json"))
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull String s) throws Exception {
                        Gson gson = new Gson();
                        JsonObject jsonObject =  new JsonParser().parse(s).getAsJsonObject();
                        JsonArray array = jsonObject.getAsJsonArray("data");
                        List<RegionType> regionTypes = new ArrayList<>();
                        for (JsonElement jsonElement : array) {
                            regionTypes.add(gson.fromJson(jsonElement, RegionType.class));
                        }
                        mView.showRegionTypeData(regionTypes);
                        return Flowable.just(JsonUtils.readJson("region.json"));
                    }
                })
                .compose(RxUtils.<String>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        JsonObject jsonObject =  new JsonParser().parse(s).getAsJsonObject();
                        JsonArray array = jsonObject.getAsJsonArray("data");
                        List<Region> regions = new ArrayList<>();
                        for (JsonElement jsonElement : array) {
                            regions.add(gson.fromJson(jsonElement, Region.class));
                        }
                        mView.showRegionData(regions);
                    }
                }));
    }
}
