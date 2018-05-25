package com.seaword.cn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.seaword.cn.MyApplication;

import butterknife.ButterKnife;

/**
 * Created by zl on 2018/5/18.
 * 1.基类也是一个View
 */

public  abstract class BaseAcivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView{

    protected Context mContext;//某一个界面具体的上下文
    protected Toolbar mToolbar;
    protected T mPresenter;//某一个界面具体的P

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        ButterKnife.bind(this);
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

    /** 初始化控件 */
    protected void initWidget() {}

    /** 加载数据 */
    protected void initDatas() {}

    /** 初始化变量*/
    protected void initVariables() {}

    /** 注入dagger2依赖 */
    protected void initInject() {}

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
}
