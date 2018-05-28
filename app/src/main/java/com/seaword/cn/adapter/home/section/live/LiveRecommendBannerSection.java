package com.seaword.cn.adapter.home.section.live;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.live.LiveRecommend;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;

/**
 * Created by zl on 2018/5/28.
 * 首页直播推荐主播section（广告）
 */

public class LiveRecommendBannerSection extends StatelessSection {

    private LiveRecommend.RecommendDataBean.BannerDataBean mData;

    public LiveRecommendBannerSection(LiveRecommend.RecommendDataBean.BannerDataBean mData ) {
        super(R.layout.layout_item_home_live_body, R.layout.layout_empty);
        this.mData = mData;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        Glide.with(mContext)
                .load(mData.getCover().getSrc())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));
        holder.setText(R.id.tv_video_live_up, TextUtils.isEmpty(mData.getNew_title()) ? "未知" : mData.getNew_title())
                .setText(R.id.tv_video_online, 0 + "");//在线人数;
        holder.setText(R.id.tv_video_title,mData.getTitle());
    }
}
