/*
 * Copyright (C) 2015 Bilibili
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zl.playerview.media;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;


import com.zl.playerview.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;


/***
 * 修改原有的IjkVideoView类，新增如下：
 * 1.删除InfoHudViewHolder类
 * 2.新增类中有本类的引用
 *
 */

public class IjkVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    private String TAG = "IjkVideoView";
    // 播放地址通过客户端可设置
    private Uri mUri;
    //播放器的一些基本配置
    private Map<String, String> mHeaders;

    // all possible internal states
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;

    // mCurrentState is a VideoView object's current state.
    // mTargetState is the state that a method caller intends to reach.
    // For instance, regardless the VideoView object's current state,
    // calling pause() intends to bring the object to a target state of STATE_PAUSED.
    private int mCurrentState = STATE_IDLE;
    private int mTargetState = STATE_IDLE;

    // 所有的东西我们需要播放和显示视频
    private IRenderView.ISurfaceHolder mSurfaceHolder = null;

    /**视频宽度*/
    private int mVideoWidth;
    /**视频高度*/
    private int mVideoHeight;
    /**窗口宽度*/
    private int mSurfaceWidth;
    /**窗口高度*/
    private int mSurfaceHeight;
    /**视频旋转角度*/
    private int mVideoRotationDegree;
    /**媒体播放器*/
    private IMediaPlayer mMediaPlayer = null;
    /**媒体控制器*/
    private IMediaController mMediaController;
    /**播放完成监听*/
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    /**播放准备监听*/
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    /**播放缓冲监听*/
    private int mCurrentBufferPercentage;
    /**播放错误监听*/
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    /**播放其他信息监听*/
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    /** recording the seek position while preparing 记录寻求位置而做准备 */
    private int mSeekWhenPrepared;
    /**是否可以暂停*/
    private boolean mCanPause = true;
    private boolean mCanSeekBack = true;
    private boolean mCanSeekForward = true;

    /** Subtitle rendering widget overlaid on top of the video. */
    // private RenderingWidget mSubtitleWidget;

    /**
     * Listener for changes to subtitle data, used to redraw when needed.
     */
    // private RenderingWidget.OnChangedListener mSubtitlesChangedListener;

    private Context mAppContext;
    private Settings mSettings;
    private IRenderView mRenderView;
    private int mVideoSarNum;
    private int mVideoSarDen;

    private long mSeekStartTime = 0;
    private long mSeekEndTime = 0;

    private TextView subtitleDisplay;

    public IjkVideoView(Context context) {
        super(context);
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVideoView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IjkVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initVideoView(context);
    }

    // REMOVED: onMeasure
    // REMOVED: onInitializeAccessibilityEvent
    // REMOVED: onInitializeAccessibilityNodeInfo
    // REMOVED: resolveAdjustedSize

    private void initVideoView(Context context) {
        mAppContext = context.getApplicationContext();
        mSettings = new Settings(mAppContext);

        //看下是否启动后台播放
        initBackground();
        //用什么东西去渲染画面
        initRenders();

        mVideoWidth = 0;
        mVideoHeight = 0;
        // REMOVED: getHolder().addCallback(mSHCallback);
        // REMOVED: getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        // REMOVED: mPendingSubtitleTracks = new Vector<Pair<InputStream, MediaFormat>>();
        mCurrentState = STATE_IDLE;
        mTargetState = STATE_IDLE;

        subtitleDisplay = new TextView(context);
        subtitleDisplay.setTextSize(24);
        subtitleDisplay.setGravity(Gravity.CENTER);
        LayoutParams layoutParams_txt = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM);
        addView(subtitleDisplay, layoutParams_txt);
    }

    /** 设置渲染器 */
    public void setRenderView(IRenderView renderView) {
        //移除之前的渲染器
        if (mRenderView != null) {
            if (mMediaPlayer != null)
                mMediaPlayer.setDisplay(null);

            View renderUIView = mRenderView.getView();
            mRenderView.removeRenderCallback(mSHCallback);
            mRenderView = null;
            removeView(renderUIView);
        }

        if (renderView == null)
            return;

        /** 第一次将SurfaceRenderView 赋值给 IRenderView */
        mRenderView = renderView;
        renderView.setAspectRatio(mCurrentAspectRatio);
        if (mVideoWidth > 0 && mVideoHeight > 0)
            renderView.setVideoSize(mVideoWidth, mVideoHeight);
        if (mVideoSarNum > 0 && mVideoSarDen > 0)
            renderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);

        View renderUIView = mRenderView.getView();
        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        renderUIView.setLayoutParams(lp);
        addView(renderUIView);

        mRenderView.addRenderCallback(mSHCallback);
        mRenderView.setVideoRotation(mVideoRotationDegree);
    }

    /** 旋转渲染器 */
    public void setRender(int render) {
        switch (render) {
            case RENDER_NONE:
                setRenderView(null);
                break;
            case RENDER_TEXTURE_VIEW: {
                TextureRenderView renderView = new TextureRenderView(getContext());
                if (mMediaPlayer != null) {
                    renderView.getSurfaceHolder().bindToMediaPlayer(mMediaPlayer);
                    renderView.setVideoSize(mMediaPlayer.getVideoWidth(), mMediaPlayer.getVideoHeight());
                    renderView.setVideoSampleAspectRatio(mMediaPlayer.getVideoSarNum(), mMediaPlayer.getVideoSarDen());
                    renderView.setAspectRatio(mCurrentAspectRatio);
                }
                setRenderView(renderView);
                break;
            }
            case RENDER_SURFACE_VIEW: {
                SurfaceRenderView renderView = new SurfaceRenderView(getContext());
                setRenderView(renderView);
                break;
            }
            default:
                Log.e(TAG, String.format(Locale.getDefault(), "invalid render %d\n", render));
                break;
        }
    }

    /**
     * Sets video path.
     * @param path the path of the video.
     */
    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path));
    }

    /**
     * Sets video URI.
     * @param uri the URI of the video.
     */
    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    /**
     * Sets video URI using specific headers.
     * @param uri     the URI of the video.
     * @param headers the headers for the URI request.
     *                Note that the cross domain redirection is allowed by default, but that can be
     *                changed with key/value pairs through the headers parameter with
     *                "android-allow-cross-domain-redirect" as the key and "0" or "1" as the value
     *                to disallow or allow cross domain redirection.
     */
    private void setVideoURI(Uri uri, Map<String, String> headers) {
        mUri = uri;
        mHeaders = headers;
        mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    // REMOVED: addSubtitleSource
    // REMOVED: mPendingSubtitleTracks
    /** 停止视频 */
    public void stopPlayback() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mCurrentState = STATE_IDLE;
            mTargetState = STATE_IDLE;
            AudioManager am = (AudioManager) mAppContext.getSystemService(Context.AUDIO_SERVICE);
            am.abandonAudioFocus(null);
        }
    }

    /** 打开视频 */
    @TargetApi(Build.VERSION_CODES.M)
    private void openVideo() {
        if (mUri == null || mSurfaceHolder == null) {
            // not ready for playback just yet, will try again later
            return;
        }
        // we shouldn't clear the target state, because somebody might have called start() previously
        release(false);

        AudioManager am = (AudioManager) mAppContext.getSystemService(Context.AUDIO_SERVICE);
        am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        try {
            mMediaPlayer = createPlayer(mSettings.getPlayer());

            // TODO: create SubtitleController in MediaPlayer, but we need
            // a context for the subtitle renderers
            final Context context = getContext();
            // REMOVED: SubtitleController

            // REMOVED: mAudioSession
            mMediaPlayer.setOnPreparedListener(mPreparedListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mSizeChangedListener);
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnInfoListener(mInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
            mMediaPlayer.setOnSeekCompleteListener(mSeekCompleteListener);
            mMediaPlayer.setOnTimedTextListener(mOnTimedTextListener);
            mCurrentBufferPercentage = 0;
            String scheme = mUri.getScheme();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    mSettings.getUsingMediaDataSource() &&
                    (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase("file"))) {
                IMediaDataSource dataSource = new FileMediaDataSource(new File(mUri.toString()));
                mMediaPlayer.setDataSource(dataSource);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                mMediaPlayer.setDataSource(mAppContext, mUri, mHeaders);
            } else {
                mMediaPlayer.setDataSource(mUri.toString());
            }
            bindSurfaceHolder(mMediaPlayer, mSurfaceHolder);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.prepareAsync();

            // REMOVED: mPendingSubtitleTracks

            // we don't set the target state here either, but preserve the
            // target state that was there before.
            mCurrentState = STATE_PREPARING;
            attachMediaController();
        } catch (IOException ex) {
            Log.w(TAG, "Unable to open content: " + mUri, ex);
            mCurrentState = STATE_ERROR;
            mTargetState = STATE_ERROR;
            mErrorListener.onError(mMediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        } catch (IllegalArgumentException ex) {
            Log.w(TAG, "Unable to open content: " + mUri, ex);
            mCurrentState = STATE_ERROR;
            mTargetState = STATE_ERROR;
            mErrorListener.onError(mMediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        } finally {
            // REMOVED: mPendingSubtitleTracks.clear();
        }
    }

    public void setMediaController(IMediaController controller) {
        if (mMediaController != null) {
            mMediaController.hide();
        }
        mMediaController = controller;
        attachMediaController();
    }

    private void attachMediaController() {
        if (mMediaPlayer != null && mMediaController != null) {
            mMediaController.setMediaPlayer(this);
            View anchorView = this.getParent() instanceof View ?
                    (View) this.getParent() : this;
            mMediaController.setAnchorView(anchorView);
            mMediaController.setEnabled(isInPlaybackState());
        }
    }

    IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener =
            new IMediaPlayer.OnVideoSizeChangedListener() {
                public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                    mVideoWidth = mp.getVideoWidth();
                    mVideoHeight = mp.getVideoHeight();
                    mVideoSarNum = mp.getVideoSarNum();
                    mVideoSarDen = mp.getVideoSarDen();
                    if (mVideoWidth != 0 && mVideoHeight != 0) {
                        if (mRenderView != null) {
                            mRenderView.setVideoSize(mVideoWidth, mVideoHeight);
                            mRenderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);
                        }
                        // REMOVED: getHolder().setFixedSize(mVideoWidth, mVideoHeight);
                        requestLayout();
                    }
                }
            };

    IMediaPlayer.OnPreparedListener mPreparedListener = new IMediaPlayer.OnPreparedListener() {
        public void onPrepared(IMediaPlayer mp) {
            mCurrentState = STATE_PREPARED;

            // Get the capabilities of the player for this stream
            // REMOVED: Metadata

            mMediaPlayer.seekTo(0);
            ((IjkMediaPlayer) mMediaPlayer).setSpeed(1);

            if (mOnPreparedListener != null) {
                mOnPreparedListener.onPrepared(mMediaPlayer);
            }
            if (mMediaController != null) {
                mMediaController.setEnabled(true);
            }
            mVideoWidth = mp.getVideoWidth();
            mVideoHeight = mp.getVideoHeight();

            int seekToPosition = mSeekWhenPrepared;  // mSeekWhenPrepared may be changed after seekTo() call
            if (seekToPosition != 0) {
                seekTo(seekToPosition);
            }
            if (mVideoWidth != 0 && mVideoHeight != 0) {
                //Log.i("@@@@", "video size: " + mVideoWidth +"/"+ mVideoHeight);
                // REMOVED: getHolder().setFixedSize(mVideoWidth, mVideoHeight);
                if (mRenderView != null) {
                    mRenderView.setVideoSize(mVideoWidth, mVideoHeight);
                    mRenderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);
                    if (!mRenderView.shouldWaitForResize() || mSurfaceWidth == mVideoWidth && mSurfaceHeight == mVideoHeight) {
                        // We didn't actually change the size (it was already at the size
                        // we need), so we won't get a "surface changed" callback, so
                        // start the video here instead of in the callback.
                        if (mTargetState == STATE_PLAYING) {
                            start();
                            if (mMediaController != null) {
                                mMediaController.show();
                            }
                        } else if (!isPlaying() &&
                                (seekToPosition != 0 || getCurrentPosition() > 0)) {
                            if (mMediaController != null) {
                                // Show the media controls when we're paused into a video and make 'em stick.
                                mMediaController.show(0);
                            }
                        }
                    }
                }
            } else {
                // We don't know the video size yet, but should start anyway.
                // The video size might be reported to us later.
                if (mTargetState == STATE_PLAYING) {
                    start();
                }
            }
        }
    };

    private IMediaPlayer.OnCompletionListener mCompletionListener =
            new IMediaPlayer.OnCompletionListener() {
                public void onCompletion(IMediaPlayer mp) {
                    mCurrentState = STATE_PLAYBACK_COMPLETED;
                    mTargetState = STATE_PLAYBACK_COMPLETED;
                    if (mMediaController != null) {
                        mMediaController.hide();
                    }
                    if (mOnCompletionListener != null) {
                        mOnCompletionListener.onCompletion(mMediaPlayer);
                    }
                }
            };

    private IMediaPlayer.OnInfoListener mInfoListener =
            new IMediaPlayer.OnInfoListener() {
                public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                    if (mOnInfoListener != null) {
                        mOnInfoListener.onInfo(mp, arg1, arg2);
                    }
                    switch (arg1) {
                        case IMediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                            Log.d(TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                            Log.d(TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                            Log.d(TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                            Log.d(TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                            Log.d(TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        case IMediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                            Log.d(TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                            Log.d(TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                            Log.d(TAG, "MEDIA_INFO_METADATA_UPDATE:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE:
                            Log.d(TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT:
                            Log.d(TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                            mVideoRotationDegree = arg2;
                            Log.d(TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                            if (mRenderView != null)
                                mRenderView.setVideoRotation(arg2);
                            break;
                        case IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                            Log.d(TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            break;
                    }
                    return true;
                }
            };

    private IMediaPlayer.OnErrorListener mErrorListener =
            new IMediaPlayer.OnErrorListener() {
                public boolean onError(IMediaPlayer mp, int framework_err, int impl_err) {
                    Log.d(TAG, "Error: " + framework_err + "," + impl_err);
                    mCurrentState = STATE_ERROR;
                    mTargetState = STATE_ERROR;
                    if (mMediaController != null) {
                        mMediaController.hide();
                    }

                    /* If an error handler has been supplied, use it and finish. */
                    if (mOnErrorListener != null) {
                        if (mOnErrorListener.onError(mMediaPlayer, framework_err, impl_err)) {
                            return true;
                        }
                    }

                    /* Otherwise, pop up an error dialog so the user knows that
                     * something bad has happened. Only try and pop up the dialog
                     * if we're attached to a window. When we're going away and no
                     * longer have a window, don't bother showing the user an error.
                     */
                    if (getWindowToken() != null) {
                        Resources r = mAppContext.getResources();
                        int messageId;

                        if (framework_err == MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK) {
                            messageId = R.string.VideoView_error_text_invalid_progressive_playback;
                        } else {
                            messageId = R.string.VideoView_error_text_unknown;
                        }

                        new AlertDialog.Builder(getContext())
                                .setMessage(messageId)
                                .setPositiveButton(R.string.VideoView_error_button,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                            /* If we get here, there is no onError listener, so
                                             * at least inform them that the video is over.
                                             */
                                                if (mOnCompletionListener != null) {
                                                    mOnCompletionListener.onCompletion(mMediaPlayer);
                                                }
                                            }
                                        })
                                .setCancelable(false)
                                .show();
                    }
                    return true;
                }
            };

    private IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener =
            new IMediaPlayer.OnBufferingUpdateListener() {
                public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                    mCurrentBufferPercentage = percent;
                }
            };

    private IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer mp) {
            mSeekEndTime = System.currentTimeMillis();
        }
    };

    private IMediaPlayer.OnTimedTextListener mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() {
        @Override
        public void onTimedText(IMediaPlayer mp, IjkTimedText text) {
            if (text != null) {
                subtitleDisplay.setText(text.getText());
            }
        }
    };

    /**
     * Register a callback to be invoked when the media file
     * is loaded and ready to go.
     *
     * @param l The callback that will be run
     */
    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener l) {
        mOnPreparedListener = l;
    }

    /**
     * Register a callback to be invoked when the end of a media file
     * has been reached during playback.
     *
     * @param l The callback that will be run
     */
    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener l) {
        mOnCompletionListener = l;
    }

    /**
     * Register a callback to be invoked when an error occurs
     * during playback or setup.  If no listener is specified,
     * or if the listener returned false, VideoView will inform
     * the user of any errors.
     *
     * @param l The callback that will be run
     */
    public void setOnErrorListener(IMediaPlayer.OnErrorListener l) {
        mOnErrorListener = l;
    }

    /**
     * Register a callback to be invoked when an informational event
     * occurs during playback or setup.
     *
     * @param l The callback that will be run
     */
    public void setOnInfoListener(IMediaPlayer.OnInfoListener l) {
        mOnInfoListener = l;
    }

    // REMOVED: mSHCallback
    private void bindSurfaceHolder(IMediaPlayer mp, IRenderView.ISurfaceHolder holder) {
        if (mp == null)
            return;

        if (holder == null) {
            mp.setDisplay(null);
            return;
        }

        holder.bindToMediaPlayer(mp);
    }

    /** surface的生命周期状态 */
    IRenderView.IRenderCallback mSHCallback = new IRenderView.IRenderCallback() {
        @Override
        public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder holder, int format, int w, int h) {
            if (holder.getRenderView() != mRenderView) {
                Log.e(TAG, "onSurfaceChanged: unmatched render callback\n");
                return;
            }

            mSurfaceWidth = w;
            mSurfaceHeight = h;
            boolean isValidState = (mTargetState == STATE_PLAYING);
            boolean hasValidSize = !mRenderView.shouldWaitForResize() || (mVideoWidth == w && mVideoHeight == h);
            if (mMediaPlayer != null && isValidState && hasValidSize) {
                if (mSeekWhenPrepared != 0) {
                    seekTo(mSeekWhenPrepared);
                }
                start();
            }
        }

        @Override
        public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder holder, int width, int height) {
            if (holder.getRenderView() != mRenderView) {
                Log.e(TAG, "onSurfaceCreated: unmatched render callback\n");
                return;
            }

            mSurfaceHolder = holder;
            if (mMediaPlayer != null)
                bindSurfaceHolder(mMediaPlayer, holder);
            else
                openVideo();
        }

        @Override
        public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder holder) {
            if (holder.getRenderView() != mRenderView) {
                Log.e(TAG, "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }

            // after we return from this we can't use the surface any more
            mSurfaceHolder = null;
            // REMOVED: if (mMediaController != null) mMediaController.hide();
            // REMOVED: release(true);
            releaseWithoutStop();
        }
    };

    public void releaseWithoutStop() {
        if (mMediaPlayer != null)
            mMediaPlayer.setDisplay(null);
    }

    /*
     * release the media player in any state
     */
    public void release(boolean cleartargetstate) {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            // REMOVED: mPendingSubtitleTracks.clear();
            mCurrentState = STATE_IDLE;
            if (cleartargetstate) {
                mTargetState = STATE_IDLE;
            }
            AudioManager am = (AudioManager) mAppContext.getSystemService(Context.AUDIO_SERVICE);
            am.abandonAudioFocus(null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isInPlaybackState() && mMediaController != null) {
            toggleMediaControlsVisiblity();
        }
        return false;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        if (isInPlaybackState() && mMediaController != null) {
            toggleMediaControlsVisiblity();
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isKeyCodeSupported = keyCode != KeyEvent.KEYCODE_BACK &&
                keyCode != KeyEvent.KEYCODE_VOLUME_UP &&
                keyCode != KeyEvent.KEYCODE_VOLUME_DOWN &&
                keyCode != KeyEvent.KEYCODE_VOLUME_MUTE &&
                keyCode != KeyEvent.KEYCODE_MENU &&
                keyCode != KeyEvent.KEYCODE_CALL &&
                keyCode != KeyEvent.KEYCODE_ENDCALL;
        if (isInPlaybackState() && isKeyCodeSupported && mMediaController != null) {
            if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE) {
                if (mMediaPlayer.isPlaying()) {
                    pause();
                    mMediaController.show();
                } else {
                    start();
                    mMediaController.hide();
                }
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
                if (!mMediaPlayer.isPlaying()) {
                    start();
                    mMediaController.hide();
                }
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                    || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
                if (mMediaPlayer.isPlaying()) {
                    pause();
                    mMediaController.show();
                }
                return true;
            } else {
                toggleMediaControlsVisiblity();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void toggleMediaControlsVisiblity() {
        if (mMediaController.isShowing()) {
            mMediaController.hide();
        } else {
            mMediaController.show();
        }
    }

    @Override
    public void start() {
        if (isInPlaybackState()) {
            mMediaPlayer.start();
            mCurrentState = STATE_PLAYING;
        }
        mTargetState = STATE_PLAYING;
    }

    @Override
    public void pause() {
        if (isInPlaybackState()) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mCurrentState = STATE_PAUSED;
            }
        }
        mTargetState = STATE_PAUSED;
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        openVideo();
    }

    // 视频总的时长
    @Override
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) mMediaPlayer.getDuration();
        }

        return -1;
    }

    /**
     * 获取当前进度
     * @return  进度
     */
    @Override
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override
    public void seekTo(int msec) {
        if (isInPlaybackState()) {
            mSeekStartTime = System.currentTimeMillis();
            mMediaPlayer.seekTo(msec);
            mSeekWhenPrepared = 0;
        } else {
            mSeekWhenPrepared = msec;
        }
    }

    @Override
    public boolean isPlaying() {
        return isInPlaybackState() && mMediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        if (mMediaPlayer != null) {
            return mCurrentBufferPercentage;
        }
        return 0;
    }

    private boolean isInPlaybackState() {
        return (mMediaPlayer != null &&
                mCurrentState != STATE_ERROR &&
                mCurrentState != STATE_IDLE &&
                mCurrentState != STATE_PREPARING);
    }

    @Override
    public boolean canPause() {
        return mCanPause;
    }

    @Override
    public boolean canSeekBackward() {
        return mCanSeekBack;
    }

    @Override
    public boolean canSeekForward() {
        return mCanSeekForward;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    // REMOVED: getAudioSessionId();
    // REMOVED: onAttachedToWindow();
    // REMOVED: onDetachedFromWindow();
    // REMOVED: onLayout();
    // REMOVED: draw();
    // REMOVED: measureAndLayoutSubtitleWidget();
    // REMOVED: setSubtitleWidget();
    // REMOVED: getSubtitleLooper();

    //-------------------------
    // Extend: Render
    //-------------------------
    public static final int RENDER_NONE = 0;
    public static final int RENDER_SURFACE_VIEW = 1;
    public static final int RENDER_TEXTURE_VIEW = 2;

    private List<Integer> mAllRenders = new ArrayList<Integer>();
    private int mCurrentRenderIndex = 0;
    private int mCurrentRender = RENDER_TEXTURE_VIEW;//RENDER_NONE


    /**  -------------------------- 增加下面方法 ------------------------*/
    public IRenderView getmRenderView() {
        return mRenderView;
    }
    public int getmVideoWidth() {
        return mVideoWidth;
    }
    public int getmVideoHeight() {
        return mVideoHeight;
    }

    /** 初始化渲染器 */
    private void initRenders() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setRender(RENDER_TEXTURE_VIEW);
        } else {
            setRender(RENDER_SURFACE_VIEW);
        }
    }

    public int toggleRender() {
        mCurrentRenderIndex++;
        mCurrentRenderIndex %= mAllRenders.size();

        mCurrentRender = mAllRenders.get(mCurrentRenderIndex);
        setRender(mCurrentRender);
        return mCurrentRender;
    }

    @NonNull
    public static String getRenderText(Context context, int render) {
        String text;
        switch (render) {
            case RENDER_NONE:
                text = context.getString(R.string.VideoView_render_none);
                break;
            case RENDER_SURFACE_VIEW:
                text = context.getString(R.string.VideoView_render_surface_view);
                break;
            case RENDER_TEXTURE_VIEW:
                text = context.getString(R.string.VideoView_render_texture_view);
                break;
            default:
                text = context.getString(R.string.N_A);
                break;
        }
        return text;
    }

    //-------------------------
    // Extend: Player
    //-------------------------
    public int togglePlayer() {
        if (mMediaPlayer != null)
            mMediaPlayer.release();

        if (mRenderView != null)
            mRenderView.getView().invalidate();
        openVideo();
        return mSettings.getPlayer();
    }

    @NonNull
    public static String getPlayerText(Context context, int player) {
        String text;
        switch (player) {
            case Settings.PV_PLAYER__AndroidMediaPlayer:
                text = context.getString(R.string.VideoView_player_AndroidMediaPlayer);
                break;
            case Settings.PV_PLAYER__IjkMediaPlayer:
                text = context.getString(R.string.VideoView_player_IjkMediaPlayer);
                break;
            case Settings.PV_PLAYER__IjkExoMediaPlayer:
                text = context.getString(R.string.VideoView_player_IjkExoMediaPlayer);
                break;
            default:
                text = context.getString(R.string.N_A);
                break;
        }
        return text;
    }

    public IMediaPlayer createPlayer(int playerType) {
        IMediaPlayer mediaPlayer = null;

        switch (playerType) {
            case Settings.PV_PLAYER__IjkExoMediaPlayer: {
                IjkExoMediaPlayer IjkExoMediaPlayer = new IjkExoMediaPlayer(mAppContext);
                mediaPlayer = IjkExoMediaPlayer;
            }
            break;
            case Settings.PV_PLAYER__AndroidMediaPlayer: {
                AndroidMediaPlayer androidMediaPlayer = new AndroidMediaPlayer();
                mediaPlayer = androidMediaPlayer;
            }
            break;
            case Settings.PV_PLAYER__IjkMediaPlayer:
            default: {
                IjkMediaPlayer ijkMediaPlayer = null;
                if (mUri != null) {
                    ijkMediaPlayer = new IjkMediaPlayer();
                    ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);

                    if (mSettings.getUsingMediaCodec()) {
                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
                        if (mSettings.getUsingMediaCodecAutoRotate()) {
                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
                        } else {
                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 0);
                        }
                        if (mSettings.getMediaCodecHandleResolutionChange()) {
                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
                        } else {
                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 0);
                        }
                    } else {
                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
                    }

                    if (mSettings.getUsingOpenSLES()) {
                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 1);
                    } else {
                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
                    }

                    String pixelFormat = mSettings.getPixelFormat();
                    if (TextUtils.isEmpty(pixelFormat)) {
                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
                    } else {
                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", pixelFormat);
                    }
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);

                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
                }
                mediaPlayer = ijkMediaPlayer;
            }
            break;
        }

        if (mSettings.getEnableDetachedSurfaceTextureView()) {
            mediaPlayer = new TextureMediaPlayer(mediaPlayer);
        }

        return mediaPlayer;
    }

    //-------------------------
    // Extend: Background
    //-------------------------

    private boolean mEnableBackgroundPlay = false;

    /*** 初始化背景 / 启动后台播放 */
    private void initBackground() {
//        mEnableBackgroundPlay = mSettings.getEnableBackgroundPlay();
//        if (mEnableBackgroundPlay) {
//            MediaPlayerService.intentToStart(getContext());
//            mMediaPlayer = MediaPlayerService.getMediaPlayer();
//        }
    }

    public boolean isBackgroundPlayEnabled() {
        return mEnableBackgroundPlay;
    }

    public void enterBackground() {
//        MediaPlayerService.setMediaPlayer(mMediaPlayer);
    }

    public void stopBackgroundPlay() {
//        MediaPlayerService.setMediaPlayer(null);
    }

    //-------------------------
    // Extend: Background
    //-------------------------

    private String buildResolution(int width, int height, int sarNum, int sarDen) {
        StringBuilder sb = new StringBuilder();
        sb.append(width);
        sb.append(" x ");
        sb.append(height);

        if (sarNum > 1 || sarDen > 1) {
            sb.append("[");
            sb.append(sarNum);
            sb.append(":");
            sb.append(sarDen);
            sb.append("]");
        }

        return sb.toString();
    }

    private String buildTimeMilli(long duration) {
        long total_seconds = duration / 1000;
        long hours = total_seconds / 3600;
        long minutes = (total_seconds % 3600) / 60;
        long seconds = total_seconds % 60;
        if (duration <= 0) {
            return "--:--";
        }
        if (hours >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds);
        } else if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }
    }

    private String buildTrackType(int type) {
        Context context = getContext();
        switch (type) {
            case ITrackInfo.MEDIA_TRACK_TYPE_VIDEO:
                return context.getString(R.string.TrackType_video);
            case ITrackInfo.MEDIA_TRACK_TYPE_AUDIO:
                return context.getString(R.string.TrackType_audio);
            case ITrackInfo.MEDIA_TRACK_TYPE_SUBTITLE:
                return context.getString(R.string.TrackType_subtitle);
            case ITrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT:
                return context.getString(R.string.TrackType_timedtext);
            case ITrackInfo.MEDIA_TRACK_TYPE_METADATA:
                return context.getString(R.string.TrackType_metadata);
            case ITrackInfo.MEDIA_TRACK_TYPE_UNKNOWN:
            default:
                return context.getString(R.string.TrackType_unknown);
        }
    }

    private String buildLanguage(String language) {
        if (TextUtils.isEmpty(language))
            return "und";
        return language;
    }

    public ITrackInfo[] getTrackInfo() {
        if (mMediaPlayer == null)
            return null;

        return mMediaPlayer.getTrackInfo();
    }

    public void selectTrack(int stream) {
        MediaPlayerCompat.selectTrack(mMediaPlayer, stream);
    }

    public void deselectTrack(int stream) {
        MediaPlayerCompat.deselectTrack(mMediaPlayer, stream);
    }

    public int getSelectedTrack(int trackType) {
        return MediaPlayerCompat.getSelectedTrack(mMediaPlayer, trackType);
    }

    //--------------------------------------------------
    // TODO Extend: Aspect Ratio -- 以下新增的代码
    //--------------------------------------------------

    // add,是否为视频正常显示状态
    private boolean mIsNormalScreen = true;
    // add,屏幕将要旋转的角度
    private int mVideoTargetRotationDegree;
    // add，视频缩放比例
    private float mVideoScale = 1.0f;
    // add,原始的Matrix
    private Matrix mOriginalMatrix;
    // add,保存全屏状态下的屏幕宽高
    private int mScreenOrWidth;
    private int mScreenOrHeight;
    // add,保存的临时Matrix
    private Matrix mSaveMatrix;

    private static final int[] s_allAspectRatio = {
            IRenderView.AR_ASPECT_FIT_PARENT,
            IRenderView.AR_ASPECT_FILL_PARENT,
            IRenderView.AR_ASPECT_WRAP_CONTENT,
            // IRenderView.AR_MATCH_PARENT,
            IRenderView.AR_16_9_FIT_PARENT,
            IRenderView.AR_4_3_FIT_PARENT};
    private int mCurrentAspectRatio = s_allAspectRatio[0];

    public void setAspectRatio(int aspectRatio) {
        mCurrentAspectRatio = aspectRatio;
        if (mRenderView != null)
            mRenderView.setAspectRatio(mCurrentAspectRatio);
    }

    public void destroy() {
        release(true);
    }

    public void reload() {
        mCurrentState = MediaPlayerParams.STATE_PLAYING;
        mTargetState = MediaPlayerParams.STATE_PLAYING;
    }

    /**
     * 还原界面
     * @param isForever 是否永久还原
     */
    public void resetVideoView(boolean isForever) {
        mIsNormalScreen = isForever;
        mVideoRotationDegree = 0;
        if (isForever) {
            mVideoTargetRotationDegree = 0;
            mVideoScale = 1.0f;
        }
        mRenderView.setTransform(mOriginalMatrix);
        mRenderView.setVideoRotation(mVideoRotationDegree);
    }

    /**
     * 调整视频界面，居中显示
     * @param scale 本次缩放比例
     * @return true则做了变换，false则没变化
     */
    public boolean adjustVideoView(float scale) {
        // 计算当前缩放比例
        mVideoScale *= scale;
        // 计算旋转角度
        final int degree = (mVideoTargetRotationDegree + 360) % 360;
        if (mVideoScale == 1.0f && degree == 0) {
            return false;
        }

        if (degree > 315 || degree <= 45) {
            mVideoRotationDegree = 0;
        } else if (degree > 45 && degree <= 135) {
            mVideoRotationDegree = 90;
        } else if (degree > 135 && degree <= 225) {
            mVideoRotationDegree = 180;
        } else if (degree > 225 && degree <= 315) {
            mVideoRotationDegree = 270;
        } else {
            mVideoRotationDegree = 0;
        }
//        mRenderView.setVideoRotation(mVideoRotationDegree);
        final int deltaDegree = mVideoRotationDegree - mVideoTargetRotationDegree;
        mVideoTargetRotationDegree = mVideoRotationDegree;

        final Matrix matrix = getVideoTransform();
        if (mScreenOrWidth == 0 || mScreenOrHeight == 0) {
            mScreenOrWidth = mRenderView.getView().getWidth();
            mScreenOrHeight = mRenderView.getView().getHeight();
        }
        if (!mIsNormalScreen) {
            // 还原之前旋转缩放状态
            matrix.preScale(mVideoScale, mVideoScale);
            matrix.postTranslate(mScreenOrWidth * (1 - mVideoScale) / 2,
                    mScreenOrHeight * (1 - mVideoScale) / 2);
            mRenderView.setTransform(matrix);
            mIsNormalScreen = true;
        } else {
            // 移动居中显示
            float[] points = new float[2];
            // 获取视频左上角离屏幕左上角的位移
            matrix.mapPoints(points);
            final float deltaX = mScreenOrWidth * (1 - mVideoScale) / 2 - points[0];
            final float deltaY = mScreenOrHeight * (1 - mVideoScale) / 2 - points[1];
//            matrix.postTranslate(mScreenOrWidth * (1 - mVideoScale) / 2 - points[0],
//                    mScreenOrHeight * (1 - mVideoScale) / 2 - points[1]);
//            mRenderView.setTransform(matrix);
            if (mSaveMatrix == null) {
                mSaveMatrix = new Matrix();
            }
            // 使用动画慢慢居中
            ValueAnimator animator = ValueAnimator.ofFloat(0, 1.0f).setDuration(300);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float percent = (float) valueAnimator.getAnimatedValue();
                    mSaveMatrix.set(matrix);
                    mSaveMatrix.postTranslate(deltaX * percent, deltaY * percent);
                    mRenderView.setTransform(mSaveMatrix);
                    mRenderView.setVideoRotation((int) (mVideoRotationDegree - deltaDegree * (1 - percent)));
                }
            });
            animator.start();
        }
        return true;
    }

    /**
     * 设置视频 Matrix
     * @param transform
     */
    public void setVideoTransform(Matrix transform) {
        mRenderView.setTransform(transform);
    }


    /**
     * add，获取视频 Matrix
     * @return
     */
    public Matrix getVideoTransform() {
        if (mOriginalMatrix == null) {
            mOriginalMatrix = mRenderView.getTransform();
        }
        return mRenderView.getTransform();
    }

    /**
     * add，设置旋转角度
     * @param degree
     */
    public void setVideoRotation(int degree) {
        mVideoTargetRotationDegree = mVideoRotationDegree + degree;
        mRenderView.setVideoRotation(mVideoTargetRotationDegree);
    }

    /**
     * add
     * 获取中断的进度
     * @return  进度
     */
    public int getInterruptPosition() {
        if (mMediaPlayer != null) {
            return (int) mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }


    /**
     * add，返回解码器 -- 在initBackground去除掉逻辑
     * @return  解码器
     */
    public IMediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public Uri getUri() {
        return mUri;
    }

    /**
     * 截图
     * @return
     */
    public Bitmap getScreenshot() {
        if (mRenderView != null) {
            return mRenderView.getVideoScreenshot();
        }
        return null;
    }



}
