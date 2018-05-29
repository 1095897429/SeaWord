package com.seaword.cn.adapter.home.section.chase;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.adapter.bangumi.ChaseRecommendCNAdapter;
import com.seaword.cn.adapter.bangumi.ChaseRecommendJPAdapter;
import com.seaword.cn.bean.chase.RecommendBangumi;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;

/**
 * Created by zl on 2018/5/29.
 * 首页追番 推荐 section
 */

public class ChaseRecommendCNSection extends StatelessSection{
    private RecommendBangumi.RecommendCnBean.FootBean mFootBean;
    private List<RecommendBangumi.RecommendCnBean.RecommendBean> mList;

    public ChaseRecommendCNSection(List<RecommendBangumi.RecommendCnBean.RecommendBean> data, RecommendBangumi.RecommendCnBean.FootBean footBean) {
        super(R.layout.layout_item_home_chase_head, R.layout.layout_item_home_chase_footer, R.layout.layout_item_home_chase_body);
        this.mFootBean = footBean;
        this.mList = data;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_title, "国产推荐")
                .setImageResource(R.id.iv_icon, R.mipmap.bangumi_follow_home_ic_domestic);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        RecyclerView recyclerView = holder.getView(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ChaseRecommendCNAdapter(mList));
    }

    @Override
    public void onBindFooterViewHolder(ViewHolder holder) {
        Glide.with(mContext)
                .load(mFootBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.bili_default_image_tv)
                .dontAnimate()
                .into((ImageView) holder.getView(R.id.iv_video_preview));
        holder.setText(R.id.tv_title, mFootBean.getTitle())
                .setText(R.id.tv_des, mFootBean.getDesc());
        if (mFootBean.getIs_new() == 1) {
            holder.setVisible(R.id.tv_new_tag, true);
        } else {
            holder.setVisible(R.id.tv_new_tag, false);
        }
    }
}
