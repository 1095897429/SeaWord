package com.seaword.cn.mvp.presenter.recommend;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seaword.cn.base.BaseContract;
import com.seaword.cn.base.BaseListSubscriber;
import com.seaword.cn.base.BaseSubscriber;
import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.bean.recommend.Recommend;
import com.seaword.cn.mvp.contract.app.RecommendContract;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.network.response.HttpResponse;
import com.seaword.cn.rx.RxUtils;
import com.seaword.cn.utils.JsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zl on 2018/5/23.
 * 1.实现具体的P -- 要继承很多方法
 * 2.继承它的父类 -- 这样绑定的方法就在父类完成了
 * 3.T 具体的View
 * 4.注解方式生成此P -- 需要参数RetrofitHelper -- 每个P都需要网络请求，这样需要网络管理类
 */

public class RecommendPresenter extends RxPresenter<RecommendContract.View> implements RecommendContract.Presenter<RecommendContract.View>{

    protected RetrofitHelper mRetrofitHelper;

    @Inject
    public RecommendPresenter(RetrofitHelper mRetrofitHelper){
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getRecommendData() {
        /** 2018.5.24 网络测试 -- 已没有tag字段*/
        /*BaseListSubscriber<Recommend> subscriber = mRetrofitHelper.getRecommend()
                .compose(RxUtils.<HttpResponse<List<Recommend>>>rxSchedulerHelper())
                .subscribeWith(new BaseListSubscriber<Recommend>(mView) {//这个Recomend是需要返回的类型
                    @Override
                    public void onSuccess(List<Recommend> recommends) {
                        mView.showRecommend(recommends);
                    }
                });
        addSubscribe(subscriber);*/


        /** 本地测试 */
      BaseSubscriber<List<Recommend>>  subscriber =  Flowable.just(JsonUtils.readJson("recommend.json"))
                .map(new Function<String, List<Recommend>>() {
                    @Override
                    public List<Recommend> apply(@NonNull String s) throws Exception {
                        //TODO gson解析字符串 -- 获得解析器，解析数据获取到根元素
                        Gson gson = new Gson();
                        JsonObject object = new JsonParser().parse(s).getAsJsonObject();
                        JsonArray array = object.getAsJsonArray("data");
                        List<Recommend> recommends = new ArrayList<>();
                        for (JsonElement jsonElement: array) {
                            recommends.add(gson.fromJson(jsonElement,Recommend.class));
                        }
                        return recommends;
                    }
                })
                .compose(RxUtils.<List<Recommend>>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<List<Recommend>>(mView) {
                    @Override
                    public void onSuccess(List<Recommend> recommends) {
                        mView.showRecommend(recommends);
                    }
                });
        addSubscribe(subscriber);

    }
}
