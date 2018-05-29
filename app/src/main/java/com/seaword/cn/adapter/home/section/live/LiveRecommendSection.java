package com.seaword.cn.adapter.home.section.live;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.live.LiveRecommend;
import com.seaword.cn.utils.NumberUtils;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;
import java.util.Random;

/**
 * Created by zl on 2018/5/28.
 * 首页直播推荐主播section
 */

public class LiveRecommendSection extends StatelessSection<LiveRecommend.RecommendDataBean.LivesBean>{

    private boolean mHasHead;
    private boolean mHasFooter;
    private String mUrl;
    private String mTitle;
    private String mCount;
    private Random mRandom;//底部更多数据的测试数据
    private LiveRecommend.RecommendDataBean.BannerDataBean mBannerDataBean;//广告数据

    /***
     *
     * @param hasHead 是否有头部
     * @param hasFooter 是否有尾部
     * @param title 头部的标题
     * @param url 头部的图片的url
     * @param count 头部的在线数量
     * @param data 泛型集合
     */
    public LiveRecommendSection(boolean hasHead, boolean hasFooter, String title, String url, String count,
                                List<LiveRecommend.RecommendDataBean.LivesBean> data) {
        super(R.layout.layout_item_home_live_head, R.layout.layout_item_home_live_footer, R.layout.layout_item_home_live_body, data);
        this.mCount = count;
        this.mHasHead = hasHead;
        this.mHasFooter = hasFooter;
        this.mTitle = title;
        this.mUrl = url;
        this.mRandom = new Random();
    }

    public LiveRecommendSection(boolean hasHead, boolean hasFooter, String title, String url, String count,
                                List<LiveRecommend.RecommendDataBean.LivesBean> data, LiveRecommend.RecommendDataBean.BannerDataBean bannerDataBean) {

        this(hasHead, hasFooter, title, url, count, data);
        this.mBannerDataBean = bannerDataBean;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        if(mHasHead){
            holder.setVisible(R.id.cl_type_root, true);
            Glide.with(mContext)
                    .load(mUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into((ImageView) holder.getView(R.id.iv_icon));
            holder.setText(R.id.tv_title, mTitle);//标题
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder("当前" + mCount + "个直播");
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                    mContext.getResources().getColor(R.color.pink_text_color));
            stringBuilder.setSpan(foregroundColorSpan, 2,
                    stringBuilder.length() - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.setText(R.id.tv_online, stringBuilder);
        }else{
            /** 如果没有头部，则让itemView的布局设置为隐藏 */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
            holder.itemView.setLayoutParams(params);
        }

    }

    @Override
    public void convert(ViewHolder holder, LiveRecommend.RecommendDataBean.LivesBean livesBean, int position) {
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
        if (mHasFooter) {
            holder.setVisible(R.id.cl_more_root, true)
                    .setText(R.id.tv_dynamic, String.valueOf(mRandom.nextInt(200) + "条新动态，点击这里刷新"));
            holder.getView(R.id.iv_refresh).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.animate()
                            .rotation(360)
                            .setInterpolator(new LinearInterpolator())
                            .setDuration(1000).start();
                }
            });

        }else{
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0, 0);
            holder.itemView.setLayoutParams(params);
        }
    }


}
