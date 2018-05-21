package com.seaword.cn.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zl on 2018/5/21.
 * Activity模型 -- Module 主要是为了提供那些没有构造函数的类的依赖 -- 给我的感觉就是如果在P中没用到，就白写了！
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @Provides
    public Activity provideActivity(){
        return mActivity;
    }

}
