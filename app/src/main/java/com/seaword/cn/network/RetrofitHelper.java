package com.seaword.cn.network;

import com.seaword.cn.bean.app.Splash;
import com.seaword.cn.network.api.AppService;

import io.reactivex.Flowable;

/**
 * Created by zl on 2018/5/21.
 * 提供不同的网络接口
 */

public class RetrofitHelper {
    private AppService mAppService;

    public RetrofitHelper(AppService mAppService){
        this.mAppService = mAppService;
    }

  /** 提供方法，内部是不同的service对应的方法 */
  public Flowable<Splash> getSplash(){
      return mAppService.getSpalsh();
  }
}
