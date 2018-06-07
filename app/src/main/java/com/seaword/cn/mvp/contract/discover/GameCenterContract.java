package com.seaword.cn.mvp.contract.discover;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.discover.GameCenter;

/**
 * Created by zl on 2018/6/7.
 */

public interface GameCenterContract {
    interface View extends BaseContract.BaseView{
        void showGameCenterData(GameCenter gameCenter);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getGameCenterData();
    }
}
