package com.seaword.cn.network.api;

import com.seaword.cn.bean.app.Splash;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by zl on 2018/5/22.
 */

public interface AppService {
    /** splash界面 */
    @GET("/x/v2/splash?mobi_app=android&build=505000&channel=360&width=1080&height=1920&ver=4344558841496142006")
    Flowable<Splash> getSpalsh();
}
