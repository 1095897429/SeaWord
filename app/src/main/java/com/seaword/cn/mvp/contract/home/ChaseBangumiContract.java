package com.seaword.cn.mvp.contract.home;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.chase.ChaseBangumi;
import com.seaword.cn.bean.chase.RecommendBangumi;

/**
 * Created by zl on 2018/5/29.
 */

public interface ChaseBangumiContract {

    interface View extends BaseContract.BaseView{
        void showChaseBangumi(ChaseBangumi chaseBangumi);//展示我的追番
        void showRecommendBangumi(RecommendBangumi recommendBangumi);//展示推荐番剧
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getChaseBangumiData();
    }
}
