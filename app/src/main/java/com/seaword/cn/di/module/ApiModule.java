package com.seaword.cn.di.module;

import android.content.Context;

import com.seaword.cn.network.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * 描述:App模型
 */
@Module
public class ApiModule {

    @Provides
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }

}
