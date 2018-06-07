package com.seaword.cn.module.app.video;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.bean.app.video.VideoDetailComment;
import com.seaword.cn.event.Event;
import com.seaword.cn.ijkplayer.definedemo.media.AndroidMediaController;
import com.seaword.cn.ijkplayer.definedemo.media.IjkVideoView;
import com.seaword.cn.module.BaseRegionActivity;
import com.seaword.cn.mvp.contract.app.video.VideoDetailContract;
import com.seaword.cn.mvp.presenter.app.video.VideoDetailPresenter;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import io.reactivex.annotations.Nullable;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by zl on 2018/5/24.
 * 获取数据后通过eventbus传递给下方的Fragment
 */

public class VideoDetailActivity extends BaseRegionActivity<VideoDetailPresenter,Nullable> implements VideoDetailContract.View {

    @BindView(R.id.iv_video_preview)
    ImageView mIvVideoPreview;
    @BindView(R.id.ijk_player2)
    IjkVideoView ijkPlayer;
    @BindView(R.id.hud_view)
    TableLayout mHudView;

    private String mVideoPath;
    private Uri mVideoUri;
    boolean mBackPressed;
    AndroidMediaController mMediaController;

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
        initPlayer();
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //点击返回或不允许后台播放时 释放资源
        if (mBackPressed || !ijkPlayer.isBackgroundPlayEnabled()) {
            ijkPlayer.stopPlayback();
            ijkPlayer.release(true);
            ijkPlayer.stopBackgroundPlay();
        } else {
            ijkPlayer.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    private void initPlayer() {
        /** 加载so文件 -- 初始化播放器 */
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }

        /** 这里使用的是Demo中提供的AndroidMediaController类控制播放相关操作 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  // TODO init UI -- 不觉明历
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        mMediaController = new AndroidMediaController(this, false);
        mMediaController.setSupportActionBar(actionBar);
        ijkPlayer.setMediaController(mMediaController);
        ijkPlayer.setHudView(mHudView);
        /**  设置本地视频文件位置或服务器地址，然后播放*/
        mVideoPath = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        if (mVideoPath != null)
            ijkPlayer.setVideoPath(mVideoPath);
        else if (mVideoUri != null)
            ijkPlayer.setVideoURI(mVideoUri);
        else {
            KLog.e("tag", "Null Data Source\n");
            finish();
            return;
        }

        ijkPlayer.setVideoPath(mVideoPath);
        ijkPlayer.start();
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

        /** 给ijkplayer设置 观察者 */
        ijkPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



}
