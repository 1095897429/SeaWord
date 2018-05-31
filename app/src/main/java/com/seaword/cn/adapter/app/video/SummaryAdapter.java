package com.seaword.cn.adapter.app.video;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.app.video.MulSummary;

import java.util.List;

/**
 * Created by zl on 2018/5/31.
 * TODO 省略了标签的部分
 * 布局都没有采用ConstraintLayout
 */

public class SummaryAdapter extends BaseMultiItemQuickAdapter<MulSummary,BaseViewHolder> {

    public SummaryAdapter(List<MulSummary> data) {
        super(data);
        addItemType(MulSummary.TYPE_DES, R.layout.layout_item_video_detail_summary_des);
        addItemType(MulSummary.TYPE_OWNER, R.layout.layout_item_video_detail_summary_owner);
        addItemType(MulSummary.TYPE_RELATE_HEAD, R.layout.layout_item_video_detail_summary_relate_head);
        addItemType(MulSummary.TYPE_RELATE, R.layout.layout_item_video_detail_summary_relate);
    }

    @Override
    protected void convert(BaseViewHolder helper, MulSummary item) {

    }
}
