package com.seaword.cn.mvp.contract.home;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.live.LivePartition;
import com.seaword.cn.bean.live.LiveRecommend;

/**
 * Created by zl on 2018/5/25.
 */

public interface LiveContract {

    interface View extends BaseContract.BaseView{
        void showLivePartition(LivePartition livePartition);//直播分区部分
        void showLiveRecommend(LiveRecommend liveRecommend);//直播推荐部分
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void  getLiveData();
    }

}
