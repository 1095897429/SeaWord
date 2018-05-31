package com.seaword.cn.module;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.seaword.cn.R;
import com.seaword.cn.base.BaseAcivity;
import com.seaword.cn.base.BaseContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zl on 2018/5/31.
 * 因为在视频明细界面都差不多，封装成一个类，初始化一些必要的类
 */

public abstract class BaseRegionActivity<T extends BaseContract.BasePresenter,K> extends BaseAcivity<T> {
    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;
    protected List<K> mList = new ArrayList<>();
    protected List<String> mTitles = new ArrayList<>();
    protected List<Fragment> mFragments = new ArrayList<>();


    @Override
    protected void initWidget() {
        mTabLayout = ButterKnife.findById(this, R.id.tab);
        mViewPager = ButterKnife.findById(this, R.id.view_pager);
    }

    /** 界面绘制完成，数据获取完成 ，走的逻辑*/
    @Override
    protected void finishTask() {
        initTitle();
        initFragment();
        initViewPager();
        initEvent();
    }

    protected void initTitle() {}

    protected void initFragment() {}

    protected void initViewPager(){
        BaseRegionTypeAdapter adapter = new BaseRegionTypeAdapter(getSupportFragmentManager(),mTitles,mFragments);
        mViewPager.setOffscreenPageLimit(mTitles.size());//设置viewpager左右预加载页
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);//设置viewpager的位置
        mTabLayout.setupWithViewPager(mViewPager); //将tablayout和ViewPager关联起来
    }

    /** 初始化事件 */
    protected void initEvent() {}

    protected static class BaseRegionTypeAdapter extends FragmentStatePagerAdapter {
        private List<String> mTitles;
        private List<Fragment> mFragments;
        /**  titels 标题  fragments 内容*/
        public BaseRegionTypeAdapter(FragmentManager fm, List<String> titels, List<Fragment> fragments) {
            super(fm);
            this.mTitles = titels;
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }


}
