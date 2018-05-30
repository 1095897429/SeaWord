package com.seaword.cn.adapter.home.section.region;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seaword.cn.R;
import com.seaword.cn.adapter.home.RegionEntranceAdapter;
import com.seaword.cn.bean.region.RegionEnter;
import com.seaword.cn.bean.region.RegionType;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zl on 2018/5/30.
 */

public class RegionEntranceSection extends StatelessSection {

    private List<RegionType> mRegionTypeList;
    private List<RegionEnter> mList;

    /** 头部类型就是 SectionedRVAdapter.VIEW_TYPE_HEADER*/
    public RegionEntranceSection(List<RegionType> data) {
        super(R.layout.layout_item_home_region_entrance, R.layout.layout_empty);
        this.mRegionTypeList = data;
        init();
    }

    private void init() {
        mList = Arrays.asList(
                new RegionEnter("直播", R.mipmap.ic_category_live),
                new RegionEnter("番剧", R.mipmap.ic_category_fanju),
                new RegionEnter("动画", R.mipmap.ic_category_donghua),
                new RegionEnter("国创", R.mipmap.ic_category_guochuang),
                new RegionEnter("音乐", R.mipmap.ic_category_yinyue),
                new RegionEnter("舞蹈", R.mipmap.ic_category_wudao),
                new RegionEnter("游戏", R.mipmap.ic_category_youxi),
                new RegionEnter("科技", R.mipmap.ic_category_keji),
                new RegionEnter("生活", R.mipmap.ic_category_shenghuo),
                new RegionEnter("鬼畜", R.mipmap.ic_category_guichu),
                new RegionEnter("时尚", R.mipmap.ic_category_shishang),
                new RegionEnter("广告", R.mipmap.ic_category_ad),
                new RegionEnter("娱乐", R.mipmap.ic_category_yuele),
                new RegionEnter("电影", R.mipmap.ic_category_dianying),
                new RegionEnter("电视剧", R.mipmap.ic_category_guichu),
                new RegionEnter("游戏中心", R.mipmap.ic_category_game_center));
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        RecyclerView recyclerView = holder.getView(R.id.recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RegionEntranceAdapter(mList,mRegionTypeList));
    }

}
