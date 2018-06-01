package com.seaword.cn.adapter.app.video;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.app.video.MulComment;
import com.seaword.cn.utils.TimeUtils;
import com.seaword.cn.widget.CircleImageView;

import java.util.List;

/**
 * Created by zl on 2018/6/1.
 */

public class CommentAdapter extends BaseMultiItemQuickAdapter<MulComment,BaseViewHolder> {

    public CommentAdapter(List<MulComment> data) {
            super(data);
            addItemType(MulComment.TYPE_COMMENT_HOT_ITEM, R.layout.layout_item_video_detail_comment);
            addItemType(MulComment.TYPE_COMMENT_MORE, R.layout.layout_item_video_detail_more);
            addItemType(MulComment.TYPE_COMMENT_NOMAL_ITEM, R.layout.layout_item_video_detail_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, MulComment item) {
        switch (item.getItemType()){
            case MulComment.TYPE_COMMENT_HOT_ITEM:
                Glide.with(mContext)
                        .load(item.hotsBean.member.avatar)
                        .centerCrop()
                        .placeholder(R.mipmap.bili_default_avatar)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into((CircleImageView) helper.getView(R.id.iv_avatar));

                helper.setText(R.id.tv_uname,item.hotsBean.member.uname)
                        .setText(R.id.tv_like, item.hotsBean.like + "")
                        .setText(R.id.tv_time, TimeUtils.millis2String((long) (item.hotsBean.ctime * Math.pow(10, 3))))
                        .setText(R.id.tv_message, item.hotsBean.content.message)
                        .setText(R.id.tv_rcount, "共有" + item.hotsBean.rcount + "条回复 >");
                break;
            case MulComment.TYPE_COMMENT_MORE:
                break;
            case MulComment.TYPE_COMMENT_NOMAL_ITEM:
                Glide.with(mContext)
                        .load(item.repliesBean.member.avatar)
                        .centerCrop()
                        .placeholder(R.mipmap.bili_default_avatar)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into((CircleImageView) helper.getView(R.id.iv_avatar));
                helper.setVisible(R.id.tv_rcount, false)
                        .setText(R.id.tv_like, item.repliesBean.like + "")
                        .setText(R.id.tv_uname, item.repliesBean.member.uname + " ")
                        .setText(R.id.tv_time, TimeUtils.millis2String((long) (item.repliesBean.ctime * Math.pow(10, 3))))
                        .setText(R.id.tv_message, item.repliesBean.content.message);
                break;
        }
    }
}
