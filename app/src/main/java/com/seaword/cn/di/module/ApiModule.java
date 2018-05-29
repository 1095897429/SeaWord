package com.seaword.cn.di.module;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.seaword.cn.di.qualifier.AppUrl;
import com.seaword.cn.di.qualifier.BangumiUrl;
import com.seaword.cn.di.qualifier.LiveUrl;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.network.api.AppService;
import com.seaword.cn.network.api.BangumiService;
import com.seaword.cn.network.api.LiveService;
import com.seaword.cn.network.helper.OkHttpHelper;
import com.seaword.cn.network.support.ApiConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述:App模型
 * 1.发现需要提供的RetrofitHelper需要参数，那么必须先提供它的参数注入 -- 参数的形成需要Retrofit -- Retrofit需要OkHttpClient + Url
 * 2.提供不同的Retrofit的限定域
 */
@Module
public class ApiModule {

    public Retrofit createRetrofit( OkHttpClient client,String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    RetrofitHelper provideRetrofitHelper(AppService appService, LiveService liveService, BangumiService bangumiService) {
        return new RetrofitHelper(appService,liveService,bangumiService);
    }

    /** 直接提供 */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        return OkHttpHelper.getInstance().getOkHttpClient();
    }

    @Singleton
    @Provides
    @AppUrl
    Retrofit provideAppRetrofit(OkHttpClient client){
        return createRetrofit(client, ApiConstants.APP_BASE_URL);
    }

    @Singleton
    @Provides
    AppService provideAppService(@AppUrl Retrofit retrofit){
        return retrofit.create(AppService.class);
    }

    /** live的retrofit */
    @Provides
    @LiveUrl
    Retrofit provideLiveRetrofit(OkHttpClient client){
        return createRetrofit(client,ApiConstants.LIVE_BASE_URL);
    }

    @Provides
    LiveService provideLiveService(@LiveUrl Retrofit retrofit){
        return retrofit.create(LiveService.class);
    }

    /** Bangumi 的retrofit */
    @Provides
    @BangumiUrl
    Retrofit provideBangumiRetrofit(OkHttpClient client){
        return createRetrofit(client,ApiConstants.BANGUMI_BASE_URL);
    }

    @Provides
    BangumiService provideBangumiService(@BangumiUrl Retrofit retrofit){
        return retrofit.create(BangumiService.class);
    }
}
