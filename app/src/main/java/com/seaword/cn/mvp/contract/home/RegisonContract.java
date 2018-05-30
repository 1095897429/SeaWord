package com.seaword.cn.mvp.contract.home;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.region.Region;
import com.seaword.cn.bean.region.RegionType;

import java.util.List;

/**
 * Created by zl on 2018/5/30.
 */

public interface RegisonContract {

    interface View extends BaseContract.BaseView{
        void showRegionTypeData(List<RegionType> regionTypes );

        void showRegionData(List<Region> regionList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getRegionData();
    }

}
