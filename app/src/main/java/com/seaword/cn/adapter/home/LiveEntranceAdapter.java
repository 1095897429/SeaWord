package com.seaword.cn.adapter.home;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.adapter.home.section.live.support.LiveEnter;

import java.util.List;

/**
 * Created by zl on 2018/5/28.
 * 继承封装的统一布局类
 *
 */

public class LiveEntranceAdapter extends BaseQuickAdapter<LiveEnter,BaseViewHolder> {

    /** 传入构造函数 */
    public LiveEntranceAdapter(List<LiveEnter> list) {
        super(R.layout.item_live_entrance,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveEnter item) {
        helper.setText(R.id.tv_title,item.title)
                .setImageResource(R.id.iv_icon,item.img);
    }

}
