package com.seaword.cn.module.app.video;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seaword.cn.R;
import com.seaword.cn.adapter.app.video.CommentAdapter;
import com.seaword.cn.base.BaseContract;
import com.seaword.cn.base.BaseFragment;
import com.seaword.cn.bean.app.video.MulComment;
import com.seaword.cn.bean.app.video.VideoDetailComment;
import com.seaword.cn.event.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zl on 2018/6/1.
 */

public class CommentFragment extends BaseFragment<BaseContract.BasePresenter> {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private CommentAdapter mCommentAdapter;
    private VideoDetailComment.DataBean mVideoDetailComment;
    private List<MulComment> mList = new ArrayList<>();

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_comment;
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


    @Override
    public void initWidget() {
        initRecyclerView();
    }

    @Override
    protected void initRecyclerView() {
        mCommentAdapter = new CommentAdapter(mList);
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mCommentAdapter);
    }


    @Subscribe
    public void onVideoDetailCommentEventt(Event.VideoDetailCommentEvent event){
        mVideoDetailComment = event.videoDetailComment;
        for (VideoDetailComment.DataBean.HotsBean hotsBean: mVideoDetailComment.hots) {
            mList.add(new MulComment()
                    .setItemType(MulComment.TYPE_COMMENT_HOT_ITEM)
                    .setHot(hotsBean));
        }

        mList.add(new MulComment().setItemType(MulComment.TYPE_COMMENT_MORE));
        for (VideoDetailComment.DataBean.RepliesBean repliesBean: mVideoDetailComment.replies) {
            mList.add(new MulComment()
                    .setItemType(MulComment.TYPE_COMMENT_NOMAL_ITEM)
                    .setReplies(repliesBean));
        }
        finishTask();
    }

    @Override
    protected void finishTask() {
        mCommentAdapter.notifyDataSetChanged();
    }


}
