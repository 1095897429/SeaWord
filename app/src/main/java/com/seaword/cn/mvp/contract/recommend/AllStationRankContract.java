package com.seaword.cn.mvp.contract.recommend;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.recommend.AllStationRank;

import java.util.List;

/**
 * Created by zl on 2018/6/14.
 */

public interface AllStationRankContract  {


    interface View extends BaseContract.BaseView{
        void showRankData(List<AllStationRank.RankBean.ListBean> regionRank);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getAllStationRankData(String type);//通过类型获取数据
    }
}
