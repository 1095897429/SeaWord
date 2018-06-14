package com.seaword.cn.adapter.recommend;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.recommend.AllStationRank;
import com.seaword.cn.module.app.video.VideoDetailActivity;
import com.seaword.cn.utils.AppUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by zl on 2018/6/14.
 */

public class AllStationRankAdapter extends BaseQuickAdapter<AllStationRank.RankBean.ListBean,BaseViewHolder> {

    public AllStationRankAdapter(@Nullable List<AllStationRank.RankBean.ListBean> data) {
        super(R.layout.item_all_region,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, AllStationRank.RankBean.ListBean item) {
        Glide.with(mContext)
                .load(item.getPic())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new RoundedCornersTransformation(mContext, 5, 0))
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));

        int position = holder.getAdapterPosition();
        if (position < 3) {
            holder.setText(R.id.tv_rank, (position + 1) + "" );
            holder.setTextColor(R.id.tv_rank, AppUtils.getColor(R.color.pink_text_color));
        } else {
            holder.setText(R.id.tv_rank, (position + 1) + "");
            holder.setTextColor(R.id.tv_rank, AppUtils.getColor(R.color.black_80));
        }

        //弹幕数量最新的数据结构中没有，直接写死
        holder.setText(R.id.tv_video_title, item.getTitle())
                .setText(R.id.tv_video_up, item.getAuthor())
                .setText(R.id.tv_video_play, item.getPlay() + "")
                .setText(R.id.tv_video_danmaku, 123 + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, VideoDetailActivity.class));
            }
        });
    }
}
