package com.seaword.cn.adapter.home.section.live;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seaword.cn.R;
import com.seaword.cn.adapter.home.LiveEntranceAdapter;
import com.seaword.cn.adapter.home.section.live.support.LiveEnter;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zl on 2018/5/28.
 * 首页直播入口Section
 * section中需要数据
 */

public class LiveEntranceSection extends StatelessSection {
    private List<LiveEnter> mList;

    public LiveEntranceSection() {
        super(R.layout.layout_item_home_live_entrance, R.layout.layout_empty);
        init();
    }

    private void init() {
        mList = Arrays.asList(
                new LiveEnter("关注",R.mipmap.live_home_follow_anchor),
                new LiveEnter("中心",R.mipmap.live_home_live_center),
                new LiveEnter("小视频",R.mipmap.live_home_clip_video),
                new LiveEnter("搜索",R.mipmap.live_home_search_room),
                new LiveEnter("分类",R.mipmap.live_home_all_category)
        );
    }

    /** 创建时的holder是对应View的持有者 */
    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        RecyclerView recyclerView = holder.getView(R.id.recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 5);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new LiveEntranceAdapter(mList));
    }
}
