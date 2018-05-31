package com.seaword.cn.module.app.video;


import android.support.v7.widget.RecyclerView;

import com.seaword.cn.R;
import com.seaword.cn.adapter.app.video.SummaryAdapter;
import com.seaword.cn.base.BaseContract;
import com.seaword.cn.base.BaseFragment;
import com.seaword.cn.bean.app.video.MulSummary;
import com.seaword.cn.event.Event;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zl on 2018/5/31.
 * 适配器采用的是BaseMultiItemQuickAdapter，没有采用Section
 *
 */

//TODO T 泛型还没有指定具体的
public class SummaryFragment extends BaseFragment<BaseContract.BasePresenter> {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private SummaryAdapter mAdapter;

    private List<MulSummary> mList = new ArrayList<>();


    public static SummaryFragment newInstance(){
        return new SummaryFragment();
    }

    /** 接收事件 */
    @Subscribe
    public void onVideoDetailEvent(Event.VideoDetailEvent event){
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_summary;
    }

    @Override
    protected void initRecyclerView() {

    }
}
