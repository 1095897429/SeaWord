package com.seaword.cn.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.seaword.cn.R;
import com.seaword.cn.utils.AppUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zl on 2018/5/23.
 * 涉及到Google接口 -- SwipeRefreshLayout.OnRefreshListener
 * K 是某一项泛型的实体
 */

public abstract class BaseRefreshFragment<T extends BaseContract.BasePresenter,K> extends BaseFragment<T> implements SwipeRefreshLayout.OnRefreshListener{
    protected RecyclerView mRecycler;//放在这里初始化
    protected SwipeRefreshLayout mRefresh;
    protected boolean mIsRefreshing = false;//是否手动的下拉刷新
    protected List<K> mList = new ArrayList<>();//RecyclerView的集合体

    @Override
    public void onRefresh() {
        mIsRefreshing = true;
        lazyLoadData();
    }

    /** 重新复写父类的方法，如果不调用super，就不会调用父类的，只会调用自己的 -- 截取父类的操作 -- 初始化找到控件 */
    /** isPrepared = true 代表着视图已经准备好了*/
    @Override
    public void finishCreateView() {
        mRefresh = ButterKnife.findById(mRootView, R.id.refresh);
        mRecycler = ButterKnife.findById(mRootView, R.id.recycler);
        isPrepared = true;
        lazyLoad();
    }

    //TODO 这里注入成功了，有情况会请求不到数据，可能是没有加入SwipeRefreshLayout布局
    /** isPrepared = false 代表着下次切换进来不用再请求了*/
    @Override
    protected void lazyLoad() {
        if(!isVisible || !isPrepared) return;
        initRefreshLayout();
        initRecyclerView();
        isPrepared = false;
    }

    protected  void initRefreshLayout(){
        if(mRefresh != null){
            mRefresh.setColorSchemeResources(R.color.colorPrimary);
            mRecycler.post(new Runnable() {
                @Override
                public void run() {
                    mRefresh.setRefreshing(true);
                    lazyLoadData();
                }
            });
            mRefresh.setOnRefreshListener(this);
        }
    }

    //重新父类的方法，就是截取了方法（super还是要调用父类的方法），在订阅传递数据时成功会回调该方法
    @Override
    public void complete() {
        if (mRefresh != null)
            mRefresh.setRefreshing(false);
        if (mIsRefreshing) {
            if (mList != null) mList.clear();
            clear();
            Toast.makeText(AppUtils.getmContext(),"刷新成功",Toast.LENGTH_SHORT).show();
        }
        mIsRefreshing = false;
    }

    /** 交个子类去处理数据 */
    protected void clear() {}


}
