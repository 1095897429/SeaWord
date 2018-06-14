package com.seaword.cn.network.api;

import com.seaword.cn.bean.recommend.AllStationRank;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zl on 2018/6/14.
 * 排行
 */

public interface RankService {

    /**
     * 全站排行
     * @param type
     * @return
     */
    @GET("index/rank/{type}")
    Flowable<AllStationRank> getAllStationRank(@Path("type") String type);

}
