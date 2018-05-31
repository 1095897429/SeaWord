package com.seaword.cn.event;

import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.bean.app.video.VideoDetailComment;

/**
 * Created by zl on 2018/5/23.
 * 事件
 */

public class Event {

    /** 开启Navigation事件 */
    public static class StartNavigationEvent {
        public boolean start;
    }

    /** 视频简介事件 */
    public static class VideoDetailEvent {
        public VideoDetail.DataBean videoDetail;
    }


    /** 视频评论事件 */
    public static class VideoDetailCommentEvent {
        public VideoDetailComment.DataBean videoDetailComment;
    }

}
