package com.seaword.cn.module.app.video;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seaword.cn.R;
import com.seaword.cn.adapter.app.video.SummaryAdapter;
import com.seaword.cn.base.BaseContract;
import com.seaword.cn.base.BaseFragment;
import com.seaword.cn.bean.app.video.MulSummary;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.event.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
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

    private SummaryAdapter mSummaryAdapter;
    private VideoDetail.DataBean mVideoDetail;
    private List<MulSummary> mList = new ArrayList<>();


    public static SummaryFragment newInstance(){
        return new SummaryFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /** 接收事件 重新构建新的实体MulSummary Arrays.asList方便了添加过程 -- 没有放在Presenter中*/
    @Subscribe
    public void onVideoDetailEvent(Event.VideoDetailEvent event){
        mVideoDetail = event.videoDetail;
        mList.addAll(Arrays.asList(new MulSummary()
                        .setItemType(MulSummary.TYPE_DES)
                        .setDesc(mVideoDetail.getDesc())
                        .setTitle(mVideoDetail.getTitle())
                        .setStat(mVideoDetail.getStat()),
                new MulSummary()
                        .setItemType(MulSummary.TYPE_OWNER)
                        .setOwner(mVideoDetail.getOwner())
                        .setCtime(mVideoDetail.getCtime())
                        .setTags(mVideoDetail.getTag()),
                new MulSummary()
                        .setItemType(MulSummary.TYPE_RELATE_HEAD)
        ));

        for (VideoDetail.DataBean.RelatesBean relatesBean : mVideoDetail.getRelates()) {
            mList.add(new MulSummary()
                    .setItemType(MulSummary.TYPE_RELATE)
                    .setRelates(relatesBean));
        }

        finishTask();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_summary;
    }

    @Override
    public void initWidget() {
        initRecyclerView();
    }

    @Override
    protected void initRecyclerView() {
        mSummaryAdapter = new SummaryAdapter(mList);
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mSummaryAdapter);
    }


    @Override
    protected void finishTask() {
        mSummaryAdapter.notifyDataSetChanged();
    }
}
