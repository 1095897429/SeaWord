package com.seaword.cn.di.component;

import android.app.Activity;

import com.seaword.cn.di.module.ActivityModule;
import com.seaword.cn.di.scope.ActivityScope;
import com.seaword.cn.module.app.SplashActivity;
import com.seaword.cn.module.app.video.VideoDetailActivity;

import dagger.Component;

/**
 * Created by zl on 2018/5/21.
 * TODO 定义ActivityScope
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    //下面都是具体的注入到哪个界面中
    void inject(SplashActivity splashActivity);
    void inject(VideoDetailActivity videoDetailActivity);
}
