package com.seaword.cn.adapter.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.region.Region;

import java.util.List;

/**
 * Created by zl on 2018/5/30.
 */

public class RegionActivityCenterAdapter extends BaseQuickAdapter<Region.BodyBean, BaseViewHolder> {

    public RegionActivityCenterAdapter(@Nullable List<Region.BodyBean> data) {
        super(R.layout.item_home_region_activity_center, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Region.BodyBean bodyBean) {
        Glide.with(mContext)
                .load(bodyBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));
        holder.setText(R.id.tv_video_title, bodyBean.getTitle());
        int position = holder.getAdapterPosition();
        /** 最后一个的时候 设置Space隐藏*/
        if (position == getItemCount() - 1) {
            holder.setVisible(R.id.space, true);
        } else {
            holder.setVisible(R.id.space, false);
        }
    }
}