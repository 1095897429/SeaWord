package com.seaword.cn.module.home;


import android.support.v7.widget.GridLayoutManager;

import com.seaword.cn.MyApplication;
import com.seaword.cn.R;
import com.seaword.cn.adapter.home.section.live.LiveBannerSection;
import com.seaword.cn.adapter.home.section.live.LiveEntranceSection;
import com.seaword.cn.adapter.home.section.live.LiveRecommendBannerSection;
import com.seaword.cn.adapter.home.section.live.LiveRecommendSection;
import com.seaword.cn.base.BaseRefreshFragment;
import com.seaword.cn.bean.live.LivePartition;
import com.seaword.cn.bean.live.LiveRecommend;
import com.seaword.cn.di.component.DaggerFragmentComponent;
import com.seaword.cn.di.module.FragmentModule;
import com.seaword.cn.mvp.contract.home.LiveContract;
import com.seaword.cn.mvp.presenter.home.LivePresenter;
import com.seaword.cn.widget.section.SectionedRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2018/5/25.
 *
 */

public class LiveFragment extends BaseRefreshFragment<LivePresenter,LiveRecommend.RecommendDataBean.LivesBean> implements LiveContract.View{

    private SectionedRVAdapter mSectionedAdapter;//适配器
    private List<LivePartition.BannerBean> mBannerList = new ArrayList<>();//上面轮播条 -- 数据放在直播部分
    private List<LivePartition.PartitionsBean> mPartitionsBeanList = new ArrayList<>();
    private List<LiveRecommend.RecommendDataBean.BannerDataBean> mBannerRecommendList = new ArrayList<>();//推荐中的广告
    private LiveRecommend.RecommendDataBean.PartitionBean mPartitionBean;// 推荐中的头部相关信息
    private LivePartition mLivePartition;

    public static LiveFragment newInstance() {
        return new LiveFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_live;
    }

    @Override
    protected void initRecyclerView() {
        mSectionedAdapter = new SectionedRVAdapter();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        /** 这里是直接重写GridLayoutManager */
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedAdapter.getSectionItemViewType(position)){
                    case SectionedRVAdapter.VIEW_TYPE_HEADER:
                        return 2;//占2格
                    case SectionedRVAdapter.VIEW_TYPE_FOOTER:
                        return 2;//占2格
                    default:
                        return 1;
                }

            }
        });
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mSectionedAdapter);
    }

    @Override
    protected void initInject() {
        DaggerFragmentComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getLiveData();
    }


    @Override
    public void showLivePartition(LivePartition livePartition) {
        mLivePartition = livePartition;//直播部分获取数据
        mBannerList.addAll(mLivePartition.getBanner());
        mPartitionsBeanList.addAll(mLivePartition.getPartitions());
    }

    @Override
    public void showLiveRecommend(LiveRecommend liveRecommend) {
        mList.addAll(liveRecommend.getRecommend_data().getLives());//推荐部分获取数据
        mBannerRecommendList.addAll(liveRecommend.getRecommend_data().getBanner_data());
        mPartitionBean = liveRecommend.getRecommend_data().getPartition();
        finishTask();
    }


    protected void finishTask() {
        mSectionedAdapter.addSection(new LiveBannerSection(mBannerList));
        mSectionedAdapter.addSection(new LiveEntranceSection());
        /** 判断是否有广告 */
        if(mBannerRecommendList.size() != 0){
            int allot = mList.size() / 2 ;
            if(mBannerRecommendList.size() == 1){
                /** 显示数据的前一部分 */
                mSectionedAdapter.addSection(new LiveRecommendSection(true, false,
                        mPartitionBean.getName(),
                        mPartitionBean.getSub_icon().getSrc(), mPartitionBean.getCount() + "",
                        mList.subList(0, allot)));
                //TODO 主要显示广告栏
                mSectionedAdapter.addSection(new LiveRecommendBannerSection(mBannerRecommendList.get(0)));
                /** 显示数据的后一部分 */
                mSectionedAdapter.addSection(new LiveRecommendSection(false, true,
                        mPartitionBean.getName(),
                        mPartitionBean.getSub_icon().getSrc(), mPartitionBean.getCount() + "",
                        mList.subList(allot, mList.size())));
            }
        }else{
            mSectionedAdapter.addSection(new LiveRecommendSection(true,true,mPartitionBean.getName(),
                    mPartitionBean.getSub_icon().getSrc(),mPartitionBean.getCount() + "",mList));
        }

        mSectionedAdapter.notifyDataSetChanged();
    }


}
