package com.seaword.cn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.seaword.cn.MyApplication;
import com.seaword.cn.R;
import com.seaword.cn.di.component.ActivityComponent;
import com.seaword.cn.di.component.DaggerActivityComponent;
import com.seaword.cn.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by zl on 2018/5/18.
 * 1.基类也是一个View
 */

public  abstract class BaseAcivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView{

    @Inject
    protected T mPresenter;//某一个界面具体的P
    protected Context mContext;//某一个界面具体的上下文
    protected Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        ButterKnife.bind(this);
        /** 子类的视图以缓存，找到toolbar*/
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        if (mToolbar != null) {
            //这段代码如果不加，会显示SeaWord项目名
            mToolbar.setTitle("");
            //初始化Toolbar
            initToolbar();
            //让组件支持Toolbar
            setSupportActionBar(mToolbar);
        }
        initStatusBar();
        initInject();
        initPresenter();
        initVariables();
        initWidget();
        initDatas();
        MyApplication.getInstance().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    /** 完成请求 */
    protected void finishTask() {}

    /** 初始化Toolbar */
    protected void initToolbar() {}

    /** 初始化控件 */
    protected void initWidget() {}

    /** 加载数据 */
    protected void initDatas() {}

    /** 初始化变量*/
    protected void initVariables() {}

    /** 注入dagger2依赖 */
    protected void initInject() {}


    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }



    /** P 绑定 V */
    private void initPresenter() {
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    /**初始化StatusBar*/
    private void initStatusBar() {

    }

    /**设置布局*/
    protected abstract int getLayoutId();

    @Override
    public void complete() {

    }

    @Override
    public void showError(String msg) {

    }


    /**
     * 显示View
     * @param id
     */
    protected void visible(final @IdRes int... id) {
        if (id != null && id.length > 0) {
            for (int resId : id) {
                View view = $(resId);
                if (view != null)
                    visible(view);
            }
        }
    }

    private View $(@IdRes int id) {
        View view;
        view = this.findViewById(id);
        return view;
    }

    /**
     * 显示View
     * @param views 视图
     */
    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    /**
     * 隐藏View
     * @param id
     */
    protected void gone(final @IdRes int... id){
        if(id != null && id.length > 0){
            for (int resId : id) {
                View view = $(resId);
                if(view != null){
                    gone(view);
                }
            }
        }
    }

    /**
     * 隐藏View
     * @param views 视图
     */
    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

}
