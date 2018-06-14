package com.seaword.cn.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.seaword.cn.R;
import com.seaword.cn.base.BaseAcivity;
import com.seaword.cn.base.BaseContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zl on 2018/5/31.
 * 因为在视频明细界面都差不多，封装成一个类，初始化一些必要的类
 * TODO 1.appbar中的右边设置选项 以及 在父类中定义AppBar的研究 -- 2018.6.14
 */

public abstract class BaseRegionActivity<T extends BaseContract.BasePresenter,K> extends BaseAcivity<T> {
    protected TextView mTvTitle;//toolbar中的标题
    protected ImageView mIvBack;//toolbar中的返回
    protected SlidingTabLayout mSlidingTabLayout;
    protected ViewPager mViewPager;
    protected List<K> mList = new ArrayList<>();
    protected List<String> mTitles = new ArrayList<>();//标题
    protected List<Fragment> mFragments = new ArrayList<>();//标题对应的Fragment

    @Override
    protected void initToolbar() {
        mTvTitle = ButterKnife.findById(this, R.id.tv_title);
        mIvBack = ButterKnife.findById(this, R.id.iv_back);
        if (mIvBack != null)
            mIvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }

    protected void setTitle(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    @Override
    protected void initWidget() {
        mSlidingTabLayout = ButterKnife.findById(this, R.id.sliding_tabs);
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
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    /** 初始化事件 */
    protected void initEvent() {}

    /** Fragment的适配器 */
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

    /** 添加Action Buttons */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_region, menu);
        return true;
    }

    /** Action Buttons 的点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.id_action_download:
                Toast.makeText(mContext,"下载",Toast.LENGTH_SHORT).show();
            break;
            case R.id.id_action_search:
                Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
