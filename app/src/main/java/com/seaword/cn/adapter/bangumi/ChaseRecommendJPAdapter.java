package com.seaword.cn.adapter.bangumi;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.chase.ChaseBangumi;
import com.seaword.cn.bean.chase.RecommendBangumi;
import com.seaword.cn.utils.NumberUtils;

import java.util.List;

/**
 * Created by zl on 2018/5/29.
 */

public class ChaseRecommendJPAdapter extends BaseQuickAdapter<RecommendBangumi.RecommendJpBean.RecommendBeanX,BaseViewHolder> {

    public ChaseRecommendJPAdapter(List<RecommendBangumi.RecommendJpBean.RecommendBeanX> data) {
        super(R.layout.item_home_chase_body, data);
    }


    /** 隐藏了最下方的观看状态 */
    @Override
    protected void convert(BaseViewHolder holder, RecommendBangumi.RecommendJpBean.RecommendBeanX item) {
        Glide.with(mContext)
                .load(item.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));
        holder.setText(R.id.tv_video_follow, NumberUtils.format(item.getFavourites()) + "人追番")
               .setText(R.id.tv_video_title, item.getTitle())
                .setText(R.id.tv_video_update,"更新至第" + item.getNewest_ep_index())
                .setVisible(R.id.tv_video_state,false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}