package com.seaword.cn.bean.app.video;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zl on 2018/6/1.
 * 注意类型的顺序
 */

public class MulComment implements MultiItemEntity  {

    public static final int TYPE_COMMENT_HOT_ITEM = 1;
    public static final int TYPE_COMMENT_MORE = 2;
    public static final int TYPE_COMMENT_NOMAL_ITEM = 3;

    public VideoDetailComment.DataBean.HotsBean hotsBean;//热评
    public VideoDetailComment.DataBean.RepliesBean repliesBean;//带有回复的评
    public int itemType;

    public MulComment setHot(VideoDetailComment.DataBean.HotsBean hot) {
        this.hotsBean = hot;
        return this;
    }

    public MulComment setReplies(VideoDetailComment.DataBean.RepliesBean replies) {
        this.repliesBean = replies;
        return this;
    }

    public MulComment setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
