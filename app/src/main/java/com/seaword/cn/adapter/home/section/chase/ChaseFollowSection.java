package com.seaword.cn.adapter.home.section.chase;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seaword.cn.R;
import com.seaword.cn.adapter.bangumi.ChaseFllowAdapter;
import com.seaword.cn.bean.chase.ChaseBangumi;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;

/**
 * Created by zl on 2018/5/29.
 * 首页追番我的追番section
 */

public class ChaseFollowSection extends StatelessSection {
    private String mCount;//头部的信息
    private List<ChaseBangumi.FollowsBean> mList;//内容布局的数据

    public ChaseFollowSection(String count, List<ChaseBangumi.FollowsBean> data) {
        super(R.layout.layout_item_home_chase_head, R.layout.layout_item_home_chase_body);
        this.mCount = count;
        mList = data;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_title,"我的番剧")
                .setImageResource(R.id.iv_icon,R.mipmap.bangumi_follow_ic_mine);
        if(!"0".equals(mCount)){
            holder.setText(R.id.tv_more,"最近更新");
        }
    }

    /** 如果mList为空，回调这个方法 */
    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        RecyclerView recyclerView = holder.getView(R.id.recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ChaseFllowAdapter(mList));
    }
}
