package com.seaword.cn.module.home;


import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.seaword.cn.R;
import com.seaword.cn.adapter.home.MainAdapter;
import com.seaword.cn.event.Event;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zl on 2018/5/22.
 */

public class HomeFragment extends BaseHomeFragment {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.stl_tabs)
    SlidingTabLayout mSlidingTabLayout;


    /** 通过这种形式获取Fragment */
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initViewPager();
    }


    private void initViewPager() {
        MainAdapter adapter = new MainAdapter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(5);//设置viewpager左右预加载页
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);//设置viewpager的位置
        //将tablayout和ViewPager关联起来
        mSlidingTabLayout.setViewPager(mViewPager);
    }


    /** 重写onCreateOptionsMenu方法来给当前Toolbar控件设置具体菜单条目 */
    /** 先clear一下，这样只有Fragment中设置的自己的了, 不会有Activity中的按钮 */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_game:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 点击打开侧滑栏 */
    @OnClick(R.id.ll_navigation)
    void onClick(){
        Event.StartNavigationEvent event = new Event.StartNavigationEvent();
        event.start = true;
        EventBus.getDefault().post(event);
    }



}
