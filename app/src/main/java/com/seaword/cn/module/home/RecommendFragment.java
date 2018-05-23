package com.seaword.cn.module.home;

import com.seaword.cn.MyApplication;
import com.seaword.cn.R;
import com.seaword.cn.base.BaseRefreshFragment;
import com.seaword.cn.bean.recommend.Recommend;
import com.seaword.cn.di.component.DaggerFragmentComponent;
import com.seaword.cn.di.module.FragmentModule;
import com.seaword.cn.mvp.contract.app.RecommendContract;
import com.seaword.cn.mvp.presenter.recommend.RecommendPresenter;
import com.socks.library.KLog;


import java.util.List;

import butterknife.OnClick;


/**
 * Created by zl on 2018/5/22.
 * 1.实现逻辑
 * 2.提供具体P
 */

public class RecommendFragment extends BaseRefreshFragment<RecommendPresenter> implements RecommendContract.View {


    /** 通过这种形式获取Fragment */
    public static RecommendFragment newInstance(){
        return new RecommendFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_recommend;
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
       mPresenter.getRecommendData();
    }

    @Override
    public void showRecommend(List<Recommend> recommends) {

    }

    @OnClick(R.id.iv_rank)
    void onClick(){

    }

}
