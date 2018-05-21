package com.seaword.cn;

import android.app.Activity;
import android.app.Application;

import com.seaword.cn.di.component.AppComponent;
import com.seaword.cn.di.component.DaggerAppComponent;
import com.seaword.cn.di.module.ApiModule;
import com.seaword.cn.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zl on 2018/5/21.
 * 1.单例
 * 2.管理activity -- HashSet --- （2）博客2
 */

public class MyApplication extends Application {

    private static  MyApplication myApplication;
    private Set<Activity> allActivities;
    private AppComponent mAppComponent;

    /** 获取Application */
    public static MyApplication getInstance(){
        return myApplication;
    }

    /** 获取AppComponent */
    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initComponent();
    }

    /** 初始化网络模块组件 */
    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .apiModule(new ApiModule())
                        .build();
    }

    /** 增加Activity */
    public void addActivity(Activity activity){
        if(allActivities == null){
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    /** 移除Activity */
    public void removeActivity(Activity activity){
        if(allActivities != null){
            allActivities.remove(activity);
        }
    }

    /** 退出应用 */
    public void exitApp(){
        if(allActivities != null){
            for (Activity act : allActivities) {
                act.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
