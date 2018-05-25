package com.seaword.cn.module.app.video;

import com.seaword.cn.R;
import com.seaword.cn.base.BaseAcivity;
import com.seaword.cn.mvp.contract.app.video.VideoDetailContract;
import com.seaword.cn.mvp.presenter.app.video.VideoDetailPresenter;

/**
 * Created by zl on 2018/5/24.
 */

public class VideoDetailActivity extends BaseAcivity<VideoDetailPresenter> implements VideoDetailContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_detail1;
    }

    @Override
    protected void initInject() {

    }


    @Override
    public void showVideoDetail() {

    }

    @Override
    public void showVideoDetailComment() {

    }
}
