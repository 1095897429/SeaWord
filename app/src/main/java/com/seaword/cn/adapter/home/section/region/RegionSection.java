package com.seaword.cn.adapter.home.section.region;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.region.Region;
import com.seaword.cn.utils.NumberUtils;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;
import java.util.Random;

/**
 * Created by zl on 2018/5/30.
 * 分区section
 * title 标题  data 内容【上传给父类】
 */

public class RegionSection extends StatelessSection<Region.BodyBean>{
    private String mTitle;
    private Random mRandom;

    public RegionSection(String title, List<Region.BodyBean> data) {
        super(R.layout.layout_item_home_region_head, R.layout.layout_item_home_region_footer, R.layout.layout_item_home_region_body, data);
        this.mTitle = title;
        this.mRandom = new Random();
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        setTypeIcon(holder, mTitle);
        holder.setText(R.id.tv_title,mTitle);
    }

    @Override
    public void convert(ViewHolder holder, Region.BodyBean bodyBean, int position) {
        Glide.with(mContext)
                .load(bodyBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));
        holder.setText(R.id.tv_video_title, bodyBean.getTitle());//标题
        holder.setText(R.id.tv_video_play_num, NumberUtils.format(bodyBean.getPlay() + ""));
        /** 逻辑判断 如果是番剧，隐藏弹幕量图片，显示喜欢量 || 否则，是弹幕量 */
        if(TextUtils.equals("番剧区",mTitle)){
            holder.setVisible(R.id.iv_video_online_region,false)
                    .setVisible(R.id.tv_video_online,true)
                    .setText(R.id.tv_video_favourite,NumberUtils.format(bodyBean.getFavourite() + ""));
        }else{
            holder.setVisible(R.id.tv_video_online,false)
                    .setVisible(R.id.iv_video_online_region,true)
                    .setText(R.id.tv_video_favourite,NumberUtils.format(bodyBean.getDanmaku() + ""));
        }
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

    private void setTypeIcon(ViewHolder holder, String title) {
        switch (title) {
            case "番剧区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_fanju);
                break;
            case "动画区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_donghua);
                break;
            case "国创区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_guochuang);
                break;
            case "音乐区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_yinyue);
                break;
            case "舞蹈区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_wudao);
                break;
            case "游戏区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_youxi);
                break;
            case "科技区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_keji);
                break;
            case "生活区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_shenghuo);
                break;
            case "鬼畜区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_guichu);
                break;
            case "时尚区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_shishang);
                break;
            case "广告区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_guochuang);
                break;
            case "娱乐区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_yuele);
                break;
            case "电影区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_dianying);
                break;
            case "电视剧区":
                holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_dianying);
                break;
        }
    }
}
