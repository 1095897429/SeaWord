package com.seaword.cn.network.api;

import com.seaword.cn.bean.app.Splash;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.bean.app.video.VideoDetailComment;
import com.seaword.cn.bean.recommend.Recommend;
import com.seaword.cn.bean.region.Region;
import com.seaword.cn.network.response.HttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by zl on 2018/5/22.
 */

public interface AppService {
    /** splash界面 */
    @GET("/x/v2/splash?mobi_app=android&build=505000&channel=360&width=1080&height=1920&ver=4344558841496142006")
    Flowable<Splash> getSpalsh();

    /** 首页推荐 */
    @GET("/x/feed/index?access_key=5c2ea06a566f3dd8850f5750b8d0a650&appkey=1d8b6e7d45233436&build=505000&idx=0&login_event=2&mobi_app=android&network=wifi&open_event=cold&platform=android&pull=true&style=2&ts=1495519813&sign=510278867e908ab3de31a7bd3701a55c")
    Flowable<HttpResponse<List<Recommend>>> getRecommend();

    /** 首页分区 -- 数据返回的是[] */
    @GET("/x/v2/show/index?access_key=fcbe0b2d947971fd3cc2b9e759d63097&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&ts=1495780436&sign=93ebfdf6018d866239977af373d45dba")
    Flowable<HttpResponse<List<Region>>> getRegion();


    /** 视频简介 */
    @GET("/x/v2/view?access_key=18b6350cf0e8fb0cacb6cf323fc2feba&aid=3852397&appkey=1d8b6e7d45233436&build=505000&from=3&mobi_app=android&plat=0&platform=android&trackid=14859045423998458858&ts=1498645383&sign=94a3c4d143f44f023558ea52a33be403")
    Flowable<VideoDetail> getVideoDetail();



}
