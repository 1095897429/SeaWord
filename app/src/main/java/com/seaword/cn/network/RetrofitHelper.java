package com.seaword.cn.network;

import com.seaword.cn.bean.app.Splash;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.bean.live.LivePartition;
import com.seaword.cn.bean.live.LiveRecommend;
import com.seaword.cn.bean.recommend.Recommend;
import com.seaword.cn.network.api.AppService;
import com.seaword.cn.network.api.LiveService;
import com.seaword.cn.network.response.HttpResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by zl on 2018/5/21.
 * 先提供不同的网络接口
 */

public class RetrofitHelper {
    private AppService mAppService;
    private LiveService mLiveService;

    public RetrofitHelper(AppService mAppService,LiveService mLiveService){
        this.mAppService = mAppService;
        this.mLiveService = mLiveService;
    }

  /** 提供方法，内部是不同的service对应的方法 */
  public Flowable<Splash> getSplash(){
      return mAppService.getSpalsh();
  }

  public Flowable<HttpResponse<List<Recommend>>> getRecommend(){
      return mAppService.getRecommend();
  }

  /*******************************LiveApi****************************************/
  public Flowable<HttpResponse<LiveRecommend>> getLiveRecommend() {
      return mLiveService.getLiveRecommend();
  }

  public Flowable<HttpResponse<LivePartition>> getLivePartition() {
        return mLiveService.getLivePartition();
    }


}
