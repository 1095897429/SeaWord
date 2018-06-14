package com.seaword.cn.adapter.app.video;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.app.video.MulSummary;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.utils.NumberUtils;
import com.seaword.cn.utils.TimeUtils;
import com.seaword.cn.widget.FlowLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2018/5/31.
 * TODO 省略了标签的部分 -- 没有用封装
 * 布局都没有采用ConstraintLayout
 */

public class SummaryAdapter extends BaseMultiItemQuickAdapter<MulSummary,BaseViewHolder> {

    List<String> mFlagList = new ArrayList<>();//存放标签的集合

    public SummaryAdapter(List<MulSummary> data) {
        super(data);
        addItemType(MulSummary.TYPE_DES, R.layout.layout_item_video_detail_summary_des);
        addItemType(MulSummary.TYPE_OWNER, R.layout.layout_item_video_detail_summary_owner);//个人信息 + 视频相关标签
        addItemType(MulSummary.TYPE_RELATE_HEAD, R.layout.layout_item_video_detail_summary_relate_head);
        addItemType(MulSummary.TYPE_RELATE, R.layout.layout_item_video_detail_summary_relate);
    }

    @Override
    protected void convert(BaseViewHolder helper, MulSummary item) {
        switch (item.getItemType()) {
            case MulSummary.TYPE_DES:
                String desc = item.desc.replaceAll(" ","");
                helper.setText(R.id.tv_title,item.title)
                        .setText(R.id.tv_des, desc)
                        .setText(R.id.tv_video_play_num, NumberUtils.format(item.stat.getView() + ""))
                        .setText(R.id.tv_video_danmaku, NumberUtils.format(item.stat.getDanmaku() + ""))
                        .setText(R.id.tv_share, NumberUtils.format(item.stat.getShare() + ""))
                        .setText(R.id.tv_coin, NumberUtils.format(item.stat.getCoin() + ""))
                        .setText(R.id.tv_favourite, NumberUtils.format(item.stat.getFavorite() + ""))
                        .setText(R.id.tv_down, "缓存");
                break;
            case MulSummary.TYPE_OWNER:
                Glide.with(mContext)
                        .load(item.owner.getFace())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into((ImageView) helper.getView(R.id.iv_avatar));
                /** 返回的时间 1497424534 不加 Math.pow(10, 3)是1970年。。 */
                String date = TimeUtils.millis2String((long) (item.ctime * Math.pow(10, 3)));
                String[] split = date.split("-");
                helper.setText(R.id.tv_name, item.owner.getName())
                        .setText(R.id.tv_time, split[0] + "年" + split[1] + "月" + (split[2].split(" "))[0] + "日" + "投递");

                //TODO 这里没有封装，直接用获取到的数据进行new 控件
                mFlagList.clear();
                for (VideoDetail.DataBean.TagBean tagbean :item.tags) {
                    mFlagList.add(tagbean.getTag_name());
                }

                initFlowView(mFlagList,helper);
                break;
            case MulSummary.TYPE_RELATE_HEAD:
                break;
            case MulSummary.TYPE_RELATE:
                Glide.with(mContext)
                        .load(item.relates.getPic())
                        .centerCrop()
                        .placeholder(R.mipmap.bili_default_image_tv)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into((ImageView) helper.getView(R.id.iv_video_preview));

                helper.setText(R.id.tv_video_title, item.relates.getTitle())
                        .setText(R.id.tv_video_up, item.relates.getOwner().getName())
                        .setText(R.id.tv_video_play, NumberUtils.format(item.relates.getStat().getView() + ""))
                        .setText(R.id.tv_video_danmaku, NumberUtils.format(item.relates.getStat().getDanmaku() + ""));
                break;
        }
    }

    private void initFlowView(final List<String> tempList, BaseViewHolder helper){
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        for (int i = 0; i < tempList.size(); i++) {
            final TextView tv = (TextView) mInflater.inflate(R.layout.layout_hot_tags_item, (FlowLayout)helper.getView(R.id.flowlayout), false);
            tv.setText(tempList.get(i));
            tv.setTag(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,tv.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
            ((FlowLayout)helper.getView(R.id.flowlayout)).addView(tv);
        }
    }
}
