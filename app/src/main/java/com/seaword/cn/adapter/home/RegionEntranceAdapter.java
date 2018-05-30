package com.seaword.cn.adapter.home;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.region.RegionEnter;
import com.seaword.cn.bean.region.RegionType;

import java.util.List;

/**
 * Created by zl on 2018/5/30.
 */

public class RegionEntranceAdapter extends BaseQuickAdapter<RegionEnter, BaseViewHolder> {

    private List<RegionType> mRegionTypeList;

    public RegionEntranceAdapter(@Nullable List<RegionEnter> data, List<RegionType> regionTypeList) {
        super(R.layout.item_home_region_entrance, data);
        this.mRegionTypeList = regionTypeList;
    }

    @Override
    protected void convert(BaseViewHolder helper, RegionEnter item) {
        helper.setText(R.id.tv_title,item.title)
                .setImageResource(R.id.iv_icon,item.img);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
