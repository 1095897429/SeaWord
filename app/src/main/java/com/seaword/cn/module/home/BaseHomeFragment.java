package com.seaword.cn.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.seaword.cn.R;
import com.seaword.cn.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by zl on 2018/5/22.
 * 1.通过在Fragment中使用Toolbar设置
 */

public abstract  class BaseHomeFragment extends BaseFragment{

    public Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//支持menu -- Fragment的onCreateOptionsMenu()回调会被调到了
    }

    @Override
    public void initWidget() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = ButterKnife.findById(mRootView, R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            // 为toolbar创建Menu
            mToolbar.inflateMenu(R.menu.menu_main);
        }
    }

}
