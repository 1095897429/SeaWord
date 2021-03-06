package com.seaword.cn.module.home;


import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.seaword.cn.R;
import com.seaword.cn.base.BaseAcivity;
import com.seaword.cn.event.Event;
import com.seaword.cn.utils.AppUtils;
import com.seaword.cn.widget.StatusBarUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/***
 * 1.界面包含主主体（Fragment） + 左侧菜单
 */

public class MainActivity extends BaseAcivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    HomeFragment mHomeFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initStatusBar() {
        /** 经过测试，还需要在xml中定义fitsSystemWindows属性，不知道为啥 */
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, mDrawerLayout, AppUtils.getColor(R.color.colorPrimary));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /** 接收StartNavigationEvent事件 */
    @Subscribe
    public void onStartNavigationEvent(Event.StartNavigationEvent event){
        if(event.start){
            toggleDrawer();//打开
        }
    }

    /** DrawerLayout侧滑菜单开关 */
    public void toggleDrawer() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    /** 初始化主界面的Fragment  navigationview中的item点击事件*/
    @Override
    protected void initVariables() {
        initFragment();
        disableNavigationViewScrollbars(mNavigationView);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initWidget() {
        switchFragmentIndex();//初始化位置

    }

    /** 去掉滚动条 */
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    private void switchFragmentIndex() {
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_content,mHomeFragment).show(mHomeFragment).commit();
    }

    private void initFragment() {
        mHomeFragment = HomeFragment.newInstance();
    }

    /** 侧滑面板监听事件 */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_vip:
                break;
            case R.id.item_unicom:

                break;
            case R.id.item_down:

                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);//START- LEFT RIGHT - END
        return true;
    }
}
