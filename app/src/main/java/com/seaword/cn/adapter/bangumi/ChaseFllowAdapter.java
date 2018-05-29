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

import java.util.List;

/**
 * Created by zl on 2018/5/29.
 * 将数据传递给父类了，父类统一管理
 */

public class ChaseFllowAdapter extends BaseQuickAdapter<ChaseBangumi.FollowsBean,BaseViewHolder> {

    public ChaseFllowAdapter(List<ChaseBangumi.FollowsBean> data) {
        super(R.layout.item_home_chase_body, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ChaseBangumi.FollowsBean item) {
        Glide.with(mContext)
                .load(item.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));
        holder.setText(R.id.tv_video_title, item.getTitle());
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("更新至第 " + item.getNewest_ep_index() + " 话");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                mContext.getResources().getColor(R.color.pink_text_color));
        stringBuilder.setSpan(foregroundColorSpan, 0,
                stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.setText(R.id.tv_video_update, stringBuilder);
        holder.setText(R.id.tv_video_state, "尚未观看");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
