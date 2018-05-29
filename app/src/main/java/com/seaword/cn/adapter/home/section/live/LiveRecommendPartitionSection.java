package com.seaword.cn.adapter.home.section.live;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.live.LivePartition;
import com.seaword.cn.bean.live.LiveRecommend;
import com.seaword.cn.utils.NumberUtils;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;
import java.util.Random;

/**
 * Created by zl on 2018/5/29.
 * 首页直播分区section
 */

public class LiveRecommendPartitionSection extends StatelessSection<LivePartition.PartitionsBean.LivesBean> {

   private String mUrl;
    private String mTitle;
    private Random mRandom;
    private String mCount;
    private boolean mhasMore = false;

    public LiveRecommendPartitionSection(String title, String url, String count,
                                         List<LivePartition.PartitionsBean.LivesBean> data) {
        super(R.layout.layout_item_home_live_head, R.layout.layout_item_home_live_footer, R.layout.layout_item_home_live_body, data);
        this.mUrl = url;
        this.mTitle = title;
        this.mCount = count;
        this.mRandom = new Random();
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        Glide.with(mContext)
                .load(mUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_icon));
        holder.setText(R.id.tv_title, mTitle);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("当前" + mCount + "个直播");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                mContext.getResources().getColor(R.color.pink_text_color));
        stringBuilder.setSpan(foregroundColorSpan, 2,
                stringBuilder.length() - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.setText(R.id.tv_online, stringBuilder);
    }


    @Override
    public void convert(ViewHolder holder,LivePartition.PartitionsBean.LivesBean livesBean, int position) {
        Glide.with(mContext)
                .load(livesBean.getCover().getSrc())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));

        holder.setText(R.id.tv_video_live_up, livesBean.getOwner().getName())//up
                .setText(R.id.tv_video_online, NumberUtils.format(livesBean.getOnline() + ""));//在线人数;
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("#" + livesBean.getArea() + "#");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                mContext.getResources().getColor(R.color.pink_text_color));
        stringBuilder.setSpan(foregroundColorSpan, 0,
                stringBuilder.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//#类型#
        stringBuilder.append(livesBean.getTitle());
        holder.setText(R.id.tv_video_title, stringBuilder);//#类型# + 标题

    }

    @Override
    public void onBindFooterViewHolder(ViewHolder holder) {

        holder.setText(R.id.tv_dynamic, String.valueOf(mRandom.nextInt(200) + "条新动态，点击这里刷新"));
        holder.getView(R.id.iv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.animate()
                        .rotation(360)
                        .setInterpolator(new LinearInterpolator())
                        .setDuration(1000).start();
            }
        });
    }

}
