package com.seaword.cn.di.component;

import android.content.Context;

import com.seaword.cn.di.module.ActivityModule;
import com.seaword.cn.di.module.ApiModule;
import com.seaword.cn.di.module.AppModule;
import com.seaword.cn.module.app.SplashActivity;
import com.seaword.cn.network.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zl on 2018/5/21.
 */

@Component(modules = {AppModule.class,ApiModule.class})
public interface AppComponent {
    Context getContext();
    RetrofitHelper getRetrofitHelper();//这个方法是为了让子类用的，demo中activityComponent需要RetrofitHelper类
}
