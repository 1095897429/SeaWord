package com.seaword.cn.mvp.contract.app;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.app.Splash;

/**
 * Created by zl on 2018/5/21.
 */

public interface SplashContract  {
    //界面展示逻辑
    interface View extends BaseContract.BaseView{
        void showCountDown(int count);//倒计时
        void showSplash(Splash splash);//展示图片
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getSpalshData();
        void setCountDown();
    }
}
