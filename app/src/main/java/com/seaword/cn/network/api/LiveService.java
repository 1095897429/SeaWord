package com.seaword.cn.network.api;

import com.seaword.cn.bean.live.LivePartition;
import com.seaword.cn.bean.live.LiveRecommend;
import com.seaword.cn.network.response.HttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by zl on 2018/5/25.
 * 直播网络接口
 */

public interface LiveService {

    /** 首页推荐直播 */
    @GET("/AppNewIndex/recommend?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639021&sign=9d024a5b09edddd51636d17d860622d2")
    Flowable<HttpResponse<LiveRecommend>> getLiveRecommend();

    /** 直播分区 */
    @GET("/AppNewIndex/common?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639884&sign=74b510ce56ef302742aafad2e20f9899")
    Flowable<HttpResponse<LivePartition>> getLivePartition();
}
