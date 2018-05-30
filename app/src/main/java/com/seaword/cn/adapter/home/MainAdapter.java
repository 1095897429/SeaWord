package com.seaword.cn.adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.seaword.cn.R;
import com.seaword.cn.bean.chase.ChaseBangumi;
import com.seaword.cn.module.home.ChaseBangumiFragment;
import com.seaword.cn.module.home.LiveFragment;
import com.seaword.cn.module.home.RecommendFragment;
import com.seaword.cn.module.home.RegionFragment;
import com.seaword.cn.utils.AppUtils;

/**
 * Created by zl on 2018/5/23.
 * 提供有几个Fragment,不包括标题
 */

public class MainAdapter extends FragmentPagerAdapter {
    private String [] mTitle;
    private Fragment[] mFragments;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    /** 初始化标题 和 实例化Fragment*/
    private void init() {
        mTitle = AppUtils.getStringArray(R.array.main_title);
        mFragments = new Fragment[mTitle.length];
    }


    @Override
    public Fragment getItem(int position) {
        if (mFragments[position] == null) {
            switch (position) {
                case 0:
                    //直播
                    mFragments[position] = LiveFragment.newInstance();
                    break;
                case 1:
                    //推荐
                    mFragments[position] = RecommendFragment.newInstance();
                    break;
                case 2:
                    //追番
                    mFragments[position] = ChaseBangumiFragment.newInstance();
                    break;
                case 3:
                    //分区
                    mFragments[position] = RegionFragment.newInstance();
                    break;
                case 4:
                    //动态
                    mFragments[position] = RecommendFragment.newInstance();
                    break;
                case 5:
                    //发现
                    mFragments[position] = RecommendFragment.newInstance();
                    break;
                default:
                    break;
            }
        }
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
