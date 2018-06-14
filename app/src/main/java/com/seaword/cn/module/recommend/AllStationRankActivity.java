package com.seaword.cn.module.recommend;

import com.seaword.cn.R;
import com.seaword.cn.module.BaseRegionActivity;

/**
 * Created by zl on 2018/6/14.
 */

public class AllStationRankActivity  extends BaseRegionActivity{

    private String[] mTitlesArr = new String[]{"原创","全站","番剧"};
    private String[] mTypesArr = new String[]{"origin-03.json", "all-03.json", "all-3-33.json"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_region_type;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitle("全区排行榜");
    }


    /** 所需的实例都在父类初始化完 */
    @Override
    protected void initVariables() {
        for (int i = 0; i < mTitlesArr.length; i++) {
            mTitles.add(mTitlesArr[i]);
            mFragments.add(AllStationRankFragment.newInstancs(mTypesArr[i]));
        }
    }

    /** 设置关联*/
    @Override
    protected void initWidget() {
        super.initWidget();
        mViewPager.setOffscreenPageLimit(mTitlesArr.length + 1);
        mViewPager.setAdapter(new BaseRegionTypeAdapter(getSupportFragmentManager(),mTitles,mFragments));
        mSlidingTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }
}
