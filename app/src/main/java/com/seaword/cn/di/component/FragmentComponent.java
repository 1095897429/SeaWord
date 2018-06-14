package com.seaword.cn.di.component;

import android.app.Activity;

import com.seaword.cn.bean.region.Region;
import com.seaword.cn.di.module.ActivityModule;
import com.seaword.cn.di.module.FragmentModule;
import com.seaword.cn.di.scope.ActivityScope;
import com.seaword.cn.di.scope.FragmentScope;
import com.seaword.cn.module.app.SplashActivity;
import com.seaword.cn.module.home.ChaseBangumiFragment;
import com.seaword.cn.module.home.LiveFragment;
import com.seaword.cn.module.home.RecommendFragment;
import com.seaword.cn.module.home.RegionFragment;
import com.seaword.cn.module.recommend.AllStationRankFragment;

import dagger.Component;

/**
 * Created by zl on 2018/5/21.
 * TODO 这个Scope还需什么理解一下
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();//这不写导致编译时说没有用到module，很尴尬 
    void inject(RecommendFragment recommendFragment);
    void inject(LiveFragment liveFragment);
    void inject(ChaseBangumiFragment chaseBangumiFragment);
    void inject(RegionFragment regionFragment);
    void inject(AllStationRankFragment allStationRankFragment);
}
