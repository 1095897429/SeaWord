package com.seaword.cn.mvp.contract.app.video;

import com.seaword.cn.base.BaseContract;
import com.seaword.cn.bean.app.video.VideoDetail;

/**
 * Created by zl on 2018/5/24.
 */

public interface VideoDetailContract {

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getVideoDetailData();
    }

    interface View extends BaseContract.BaseView {
        void showVideoDetail();
        void showVideoDetailComment();
    }

}
