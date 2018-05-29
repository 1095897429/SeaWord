package com.seaword.cn.module.home;

import android.support.v7.widget.LinearLayoutManager;

import com.seaword.cn.R;
import com.seaword.cn.adapter.home.section.chase.ChaseFollowSection;
import com.seaword.cn.adapter.home.section.chase.ChaseIndexSection;
import com.seaword.cn.adapter.home.section.chase.ChaseRecommendCNSection;
import com.seaword.cn.adapter.home.section.chase.ChaseRecommendJPSection;
import com.seaword.cn.base.BaseRefreshFragment;
import com.seaword.cn.bean.chase.ChaseBangumi;
import com.seaword.cn.bean.chase.RecommendBangumi;
import com.seaword.cn.mvp.contract.home.ChaseBangumiContract;
import com.seaword.cn.mvp.presenter.home.ChaseBangumiPresenter;
import com.seaword.cn.widget.section.SectionedRVAdapter;


/**
 * Created by zl on 2018/5/29.
 */

public class ChaseBangumiFragment extends BaseRefreshFragment<ChaseBangumiPresenter,ChaseBangumi.FollowsBean> implements ChaseBangumiContract.View {
    SectionedRVAdapter mSectionedRVAdapter;
    private ChaseBangumi mChaseBangumi;//我的追番
    private RecommendBangumi.RecommendCnBean mRecommendCnBean;//国产推荐
    private RecommendBangumi.RecommendJpBean mRecommendJpBean;//番剧推荐
    private RecommendBangumi mRecommendBangumi;//获取其中的广告

    public static ChaseBangumiFragment newInstance(){
        return new ChaseBangumiFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_chase_bangumi;
    }

    /** 放在父类 */
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initRecyclerView() {
        mSectionedRVAdapter = new SectionedRVAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(linearLayoutManager);
        mRecycler.setAdapter(mSectionedRVAdapter);
    }

    @Override
    protected void clear() {
        mSectionedRVAdapter.removeAllSections();
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getChaseBangumiData();
    }

    @Override
    public void showChaseBangumi(ChaseBangumi chaseBangumi) {
        mChaseBangumi = chaseBangumi;
    }

    /** cn : 叫我僵小鱼 日常篇  jp:恋爱暴君 */
    @Override
    public void showRecommendBangumi(RecommendBangumi recommendBangumi) {
        mList.addAll(mChaseBangumi.getFollows());
        mRecommendBangumi = recommendBangumi;
        mRecommendCnBean = recommendBangumi.getRecommend_cn();
        mRecommendJpBean = recommendBangumi.getRecommend_jp();
        finishTask();
    }


    @Override
    protected void finishTask() {
        mSectionedRVAdapter.addSection(new ChaseIndexSection());//索引
        mSectionedRVAdapter.addSection(new ChaseFollowSection(mChaseBangumi.getUpdate_count() + "",mList));//我的追番
        mSectionedRVAdapter.addSection(new ChaseRecommendJPSection(mRecommendJpBean.getRecommend(),mRecommendJpBean.getFoot().get(0)));//这样就返回了一个Bean
        mSectionedRVAdapter.addSection(new ChaseRecommendCNSection(mRecommendCnBean.getRecommend(),mRecommendCnBean.getFoot().get(0)));
        mSectionedRVAdapter.notifyDataSetChanged();
    }
}
