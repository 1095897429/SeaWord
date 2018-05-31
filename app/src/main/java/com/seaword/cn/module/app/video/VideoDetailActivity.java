package com.seaword.cn.module.app.video;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.bean.app.video.VideoDetailComment;
import com.seaword.cn.event.Event;
import com.seaword.cn.module.BaseRegionActivity;
import com.seaword.cn.mvp.contract.app.video.VideoDetailContract;
import com.seaword.cn.mvp.presenter.app.video.VideoDetailPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import io.reactivex.annotations.Nullable;

/**
 * Created by zl on 2018/5/24.
 * 获取数据后通过eventbus传递给下方的Fragment
 */

public class VideoDetailActivity extends BaseRegionActivity<VideoDetailPresenter,Nullable> implements VideoDetailContract.View {

    @BindView(R.id.iv_video_preview)
    ImageView mIvVideoPreview;

    private VideoDetail.DataBean mVideoDetail;
    private VideoDetailComment.DataBean mVideoDetailComment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_detail1;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initDatas() {
        mPresenter.getVideoDetailData();
    }

    /** 复写父类方法*/
    @Override
    protected void initTitle() {
        mTitles.add("简介");
        mTitles.add("评论(" + mVideoDetailComment.page.acount + ")");
    }

    @Override
    protected void initFragment() {
        mFragments.add(SummaryFragment.newInstance());
        mFragments.add(SummaryFragment.newInstance());
    }


    @Override
    public void showVideoDetail(VideoDetail.DataBean videoDetail) {
        mVideoDetail = videoDetail;
    }

    @Override
    public void showVideoDetailComment(VideoDetailComment.DataBean videoDetailComment) {
        mVideoDetailComment = videoDetailComment;
        finishTask();
    }

    @Override
    protected void finishTask() {
        //设置图片
        Glide.with(mContext)
                .load(mVideoDetail.getPic())
                .centerCrop()
                .placeholder(R.mipmap.bili_default_image_tv)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(mIvVideoPreview);
        super.finishTask();
    }


    @Override
    protected void initEvent() {
        Event.VideoDetailEvent videoDetailEvent = new Event.VideoDetailEvent();
        videoDetailEvent.videoDetail = mVideoDetail;
        EventBus.getDefault().post(videoDetailEvent);

        Event.VideoDetailCommentEvent videoDetailCommentEvent = new Event.VideoDetailCommentEvent();
        videoDetailCommentEvent.videoDetailComment = mVideoDetailComment;
        EventBus.getDefault().post(videoDetailCommentEvent);
    }



}
