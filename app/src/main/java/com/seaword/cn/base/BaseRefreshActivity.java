package com.seaword.cn.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2018/6/7.
 */

public abstract class BaseRefreshActivity<T extends BaseContract.BasePresenter,K> extends BaseAcivity<T>{

    protected RecyclerView mRecycler;
    private List<K> mKList = new ArrayList<>();

}
