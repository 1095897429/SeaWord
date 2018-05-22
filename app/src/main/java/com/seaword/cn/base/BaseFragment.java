package com.seaword.cn.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zl on 2018/5/22.
 * 1.基类也是一个View
 * 2.T 是每个界面具体传过来的P
 * 3.懒加载
 */

public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends RxFragment implements BaseContract.BaseView{

    protected T mPresenter;
    protected boolean isVisible;  //标志位 fragment是否可见
    protected boolean isPrepared;// 标志位 标志已经控件初始化完成
    protected Activity mActivity;
    protected View mRootView;
    private Unbinder mUnbinder;


    /**
     * Fragment数据的懒加载
     * fragment的生命周期的第一个方法,每次切换都会调用此方法
     * 调用getUserVisibleHint()获得Fragment的状态是可见还是不可见的，如果可见则进行懒加载操作
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {}

    protected void onVisible() {
        lazyLoad();
    }

    /** 懒加载方法 需要状态可见 + 控件初始化完成 都完成*/
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) return;
        lazyLoadData();
        isPrepared = false;
    }

   /**  加载数据 */
    protected void lazyLoadData() {
    }


    /** 当activity的onCreate方法执行后，onAttach就会调用 -- 接着调用onCreate -- 再调用onCreateView -- onViewCreate */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mRootView == null){
            mRootView = inflater.inflate(getLayoutId(),container,false);
        }else{//TODO 逻辑

        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this,view);
        initInject();
        initPresenter();
        initVariables();
        initWidget();
        finishCreateView();
    }

    @Override
    public void onDetach() {
        this.mActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();//解绑
        if (mPresenter != null) mPresenter.detachView();
    }

    public abstract int getLayoutId();

    /** 注入dagger2依赖 */
    protected void initInject() {}

    /** 初始化Presenter，与对应的view绑定 */
    private void initPresenter() {
        if (mPresenter != null) mPresenter.attachView(this);
    }

    /** 初始化变量*/
    public void initVariables() {}

    /** 对各种控件进行设置、适配、填充数据 */
    public void initWidget() {}


    /** 当状态和控件都可见时才加载数据 */
    public void finishCreateView() {
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void complete() {

    }

    @Override
    public void showError(String msg) {

    }
}
