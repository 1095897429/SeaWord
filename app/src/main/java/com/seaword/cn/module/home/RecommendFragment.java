package com.seaword.cn.module.home;

import android.support.v7.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seaword.cn.MyApplication;
import com.seaword.cn.R;
import com.seaword.cn.adapter.home.RecommendAdapter;
import com.seaword.cn.base.BaseRefreshFragment;
import com.seaword.cn.bean.recommend.MulRecommend;
import com.seaword.cn.bean.recommend.Recommend;
import com.seaword.cn.di.component.DaggerFragmentComponent;
import com.seaword.cn.di.module.FragmentModule;
import com.seaword.cn.mvp.contract.home.RecommendContract;
import com.seaword.cn.mvp.presenter.home.RecommendPresenter;
import com.seaword.cn.utils.EmptyUtils;


import java.util.List;

import butterknife.OnClick;


/**
 * Created by zl on 2018/5/22.
 * 1.实现逻辑
 * 2.提供具体P
 */

public class RecommendFragment extends BaseRefreshFragment<RecommendPresenter,MulRecommend> implements RecommendContract.View {

    private RecommendAdapter mAdapter;//涉及RV的适配器


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

    /** 假设同步拿到数据后 -- 设置布局 -- 设置相对的适配器*/
    @Override
    protected void initRecyclerView() {
        mAdapter = new RecommendAdapter(mList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        //TODO onAttachedToRecyclerView中接口回调了SpanSizeLookup接口 -- 封装了一层而已，它先实现了，用它的接口即可
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mList.get(position).spanSize;
            }
        });
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showRecommend(List<Recommend> recommends) {
        for (Recommend recommend: recommends) {
            /** 注意下添加的顺序，后面添加的时候type是2,代表有2种布局,可以大于2*/
            if(!EmptyUtils.isEmpty(recommend.getBanner_item())){
                mList.add(new MulRecommend(MulRecommend.TYPR_HEADER,MulRecommend.HEADER_SPAN_SIZE,recommend.getBanner_item()));
            }else{
                mList.add(new MulRecommend(MulRecommend.TYPE_ITEM,MulRecommend.ITEM_SPAN_SIZE,recommend));
            }
        }
        finishTask();
    }

    @Override
    protected void finishTask() {
        mAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.iv_rank)
    void onClick(){

    }

}
