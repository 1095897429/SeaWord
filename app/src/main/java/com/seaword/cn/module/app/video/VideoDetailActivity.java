package com.seaword.cn.module.app.video;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.KeyEvent;

import com.bumptech.glide.Glide;
import com.seaword.cn.R;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.bean.app.video.VideoDetailComment;
import com.seaword.cn.event.Event;
import com.seaword.cn.module.BaseRegionActivity;
import com.seaword.cn.mvp.contract.app.video.VideoDetailContract;
import com.seaword.cn.mvp.presenter.app.video.VideoDetailPresenter;
import com.zl.playerview.media.IjkPlayerView;
import org.greenrobot.eventbus.EventBus;
import io.reactivex.annotations.Nullable;

/**
 * Created by zl on 2018/5/24.
 * 获取数据后通过eventbus传递给下方的Fragment
 * 弹幕还没有研究 -- 6.11只是将播放出来了给定的数据，Demo中没有播放的资源数据
 * 先显示下方的进度条 -- 完成、错误时隐藏
 */

public class VideoDetailActivity extends BaseRegionActivity<VideoDetailPresenter,Nullable> implements VideoDetailContract.View {

    private IjkPlayerView mPlayerView;
    //TODO 测试
    private static final String VIDEO_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/SD/movie_index.m3u8";
    private static final String VIDEO_HD_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/HD/movie_index.m3u8";
    private static final String IMAGE_URL = "http://vimg2.ws.126.net/image/snapshot/2016/11/I/M/VC62HMUIM.jpg";


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
    protected void initVariables() {
        mPlayerView = (IjkPlayerView) findViewById(R.id.player_view);
        Glide.with(this).load(IMAGE_URL).fitCenter().into(mPlayerView.mPlayerThumb);
        mPlayerView.init()
                .setTitle("这是不是跑马灯TextView，标题要足够长才会跑。-(゜ -゜)つロ 乾杯~")
                .setVideoSource(null, VIDEO_URL, VIDEO_HD_URL, null, null)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        visible(R.id.firstBar);
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
        mFragments.add(CommentFragment.newInstance());
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


    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void complete() {
        gone(R.id.firstBar);
    }

    @Override
    public void showError(String msg) {
        gone(R.id.firstBar);
    }
}
