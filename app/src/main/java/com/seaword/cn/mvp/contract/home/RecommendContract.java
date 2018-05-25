package com.seaword.cn.mvp.contract.home;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.app.Splash;
import com.seaword.cn.bean.recommend.Recommend;

import java.util.List;

/**
 * Created by zl on 2018/5/21.
 */

public interface RecommendContract {

    //界面展示逻辑
    interface View extends BaseContract.BaseView{
        void showRecommend(List<Recommend> recommends);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getRecommendData();
    }
}
