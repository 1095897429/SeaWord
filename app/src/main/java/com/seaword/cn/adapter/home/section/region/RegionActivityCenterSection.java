package com.seaword.cn.adapter.home.section.region;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.seaword.cn.R;
import com.seaword.cn.adapter.home.RegionActivityCenterAdapter;
import com.seaword.cn.bean.region.Region;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;

/**
 * Created by zl on 2018/5/30.
 * 分区中 活动中心的Section
 */

public class RegionActivityCenterSection extends StatelessSection {

    private List<Region.BodyBean> mList;

    public RegionActivityCenterSection(List<Region.BodyBean> list) {
        super(R.layout.layout_item_home_region_head, R.layout.layout_item_home_region_activity_center, R.layout.layout_empty);
        this.mList = list;
    }


    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_title, "活动中心")
                .setImageResource(R.id.iv_icon, R.mipmap.ic_header_activity_center);
    }

    @Override
    public void onBindFooterViewHolder(ViewHolder holder) {
        RecyclerView recyclerView = holder.getView(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RegionActivityCenterAdapter(mList));
    }
}
