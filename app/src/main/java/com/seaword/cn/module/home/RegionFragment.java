package com.seaword.cn.module.home;

import android.support.v7.widget.GridLayoutManager;

import com.seaword.cn.R;
import com.seaword.cn.adapter.home.section.region.RegionActivityCenterSection;
import com.seaword.cn.adapter.home.section.region.RegionEntranceSection;
import com.seaword.cn.adapter.home.section.region.RegionSection;
import com.seaword.cn.adapter.home.section.region.RegionTopicSection;
import com.seaword.cn.base.BaseRefreshFragment;
import com.seaword.cn.bean.region.Region;
import com.seaword.cn.bean.region.RegionType;
import com.seaword.cn.mvp.contract.home.RegisonContract;
import com.seaword.cn.mvp.presenter.home.RegisonPresenter;
import com.seaword.cn.widget.section.SectionedRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2018/5/30.
 */

public class RegionFragment extends BaseRefreshFragment<RegisonPresenter,Region> implements RegisonContract.View{

    SectionedRVAdapter mSectionedRVAdapter;
    private volatile List<RegionType> mRegionTypeList = new ArrayList<>();

    public static RegionFragment newInstance(){
        return new RegionFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_region;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getRegionData();
    }

    @Override
    protected void clear() {
        mRegionTypeList.clear();
        mSectionedRVAdapter.removeAllSections();
    }

    @Override
    protected void initRecyclerView() {
        mSectionedRVAdapter = new SectionedRVAdapter();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedRVAdapter.getSectionItemViewType(position)) {
                    case SectionedRVAdapter.VIEW_TYPE_HEADER:
                        return 2;//2格
                    case SectionedRVAdapter.VIEW_TYPE_FOOTER:
                        return 2;//2格
                    default:
                        return 1;
                }
            }
        });
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mSectionedRVAdapter);
    }


    @Override
    public void showRegionTypeData(List<RegionType> regionTypes) {
        mRegionTypeList.addAll(regionTypes);
    }

    @Override
    public void showRegionData(List<Region> regionList) {
        mList.addAll(regionList);
        finishTask();
    }

    @Override
    protected void finishTask() {
        mSectionedRVAdapter.addSection(new RegionEntranceSection(mRegionTypeList));
        for (Region region:mList) {
            /** 逻辑判断 type = topic -- 话题 -- 加入Section  type = activity -- 活动中心-- 加入Section  */
            if("topic".equals(region.getType())){
                mSectionedRVAdapter.addSection(new RegionTopicSection(region.getBody().get(0)));
            }else if("activity".equals(region.getType())){
                mSectionedRVAdapter.addSection(new RegionActivityCenterSection(region.getBody()));
            }else
                mSectionedRVAdapter.addSection(new RegionSection(region.getTitle(),region.getBody()));
        }
        mSectionedRVAdapter.notifyDataSetChanged();
    }
}
