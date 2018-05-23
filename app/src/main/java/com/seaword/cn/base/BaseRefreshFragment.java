package com.seaword.cn.base;

/**
 * Created by zl on 2018/5/23.
 * 下拉刷新基类
 */

public abstract class BaseRefreshFragment<T extends BaseContract.BasePresenter> extends BaseFragment<T>{

    public BaseRefreshFragment(){
        super();
    }

}
