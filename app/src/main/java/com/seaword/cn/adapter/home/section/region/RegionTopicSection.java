package com.seaword.cn.adapter.home.section.region;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.region.Region;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

/**
 * Created by zl on 2018/5/30.
 * 分区中话题的Section
 */

public class RegionTopicSection extends StatelessSection<Region.BodyBean> {
    private Region.BodyBean mBodyBean;

    public RegionTopicSection(Region.BodyBean data) {
        super(R.layout.layout_item_home_region_topic,R.layout.layout_empty);
        this.mBodyBean = data;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        //设置标题图片
        holder.setText(R.id.tv_title, "话题")
                .setImageResource(R.id.iv_icon, R.mipmap.ic_header_topic);
        Glide.with(mContext)
                .load(mBodyBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));
    }
}
