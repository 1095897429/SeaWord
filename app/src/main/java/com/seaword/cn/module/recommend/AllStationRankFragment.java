package com.seaword.cn.module.recommend;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.seaword.cn.R;
import com.seaword.cn.adapter.recommend.AllStationRankAdapter;
import com.seaword.cn.base.BaseRefreshFragment;
import com.seaword.cn.bean.recommend.AllStationRank;
import com.seaword.cn.constant.Constants;
import com.seaword.cn.module.BaseRegionActivity;
import com.seaword.cn.mvp.contract.recommend.AllStationRankContract;
import com.seaword.cn.mvp.presenter.recommend.AllStationRankPresenter;

import java.util.List;

/**
 * Created by zl on 2018/6/14.
 * 1.构建好
 * 2.获取它的方法
 */

public class AllStationRankFragment extends BaseRefreshFragment<AllStationRankPresenter,AllStationRank.RankBean.ListBean> implements AllStationRankContract.View{

    private String mType;
    private AllStationRankAdapter mAdapter;

    /** 通过这种形式给fragment设置参数 */
    public static AllStationRankFragment newInstancs(String type){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_TYPE,type);
        AllStationRankFragment rankFragment = new AllStationRankFragment();
        rankFragment.setArguments(bundle);
        return rankFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_region_type;
    }

    @Override
    public void initVariables() {
        Bundle bundle = getArguments();
        if(bundle != null){
            mType = bundle.getString(Constants.EXTRA_TYPE);
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    /** 默认这里的mList为空 */
    @Override
    protected void initRecyclerView() {
        mAdapter = new AllStationRankAdapter(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getAllStationRankData(mType);
    }

    /** 这里的mList添加后台数据 */
    @Override
    public void showRankData(List<AllStationRank.RankBean.ListBean> regionRank) {
        mList.addAll(regionRank);
        finishTask();
    }

    @Override
    protected void finishTask() {
        mAdapter.notifyDataSetChanged();
    }

}
