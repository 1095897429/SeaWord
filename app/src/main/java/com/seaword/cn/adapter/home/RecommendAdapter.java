package com.seaword.cn.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seaword.cn.R;
import com.seaword.cn.bean.recommend.MulRecommend;
import com.seaword.cn.bean.recommend.Recommend;
import com.seaword.cn.utils.FormatUtils;
import com.seaword.cn.utils.NumberUtils;
import com.socks.library.KLog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2018/5/23.
 */

public class RecommendAdapter extends BaseMultiItemQuickAdapter<MulRecommend,BaseViewHolder>{


    /** 类型 - 布局 存放在layouts中，在次级父类的getDefItemViewType中调用,返回几种布局类型*/
    public RecommendAdapter(List<MulRecommend> data) {
        super(data);
        addItemType(MulRecommend.TYPR_HEADER, R.layout.layout_recommend_banner);
        addItemType(MulRecommend.TYPE_ITEM, R.layout.layout_item_home_recommend_body);
    }

    /** 绑定VH 和 每个模型的数据 根据对象中的类型，通过对象的type来加载不同的布局 + 实现每项的点击事件*/
    @Override
    protected void convert(BaseViewHolder helper, MulRecommend item) {
        switch (helper.getItemViewType()){
            case MulRecommend.TYPR_HEADER:
                final Banner banner = helper.getView(R.id.banner);
                final List<Recommend.BannerItemBean> beanList = item.mBannerItemBean;
                //TODO 没有用流处理
                List<String> urls = new ArrayList<>();
                for (Recommend.BannerItemBean bannerItem: beanList) {
                    urls.add(bannerItem.getImage());
                }
                banner.setIndicatorGravity(BannerConfig.RIGHT)
                        .setImages(urls)
                        .setImageLoader(new GlideImageLoader())
                        .start();
               banner.setOnBannerListener(new OnBannerListener() {
                   @Override
                   public void OnBannerClick(int position) {
                       KLog.d(beanList.get(position).getTitle());
                   }
               });
                break;
            case MulRecommend.TYPE_ITEM:
                Glide.with(mContext)
                        .load(item.mRecommend.getCover())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.mipmap.bili_default_image_tv)
                        .dontAnimate()
                        .into((ImageView) helper.getView(R.id.iv_video_preview));
                helper.setText(R.id.tv_video_play_num, NumberUtils.format(item.mRecommend.getPlay() + ""))//播放数量
                        .setText(R.id.tv_video_time, FormatUtils.formatDuration(item.mRecommend.getDuration() + ""))//播放时长
                        .setText(R.id.tv_video_danmaku,NumberUtils.format(item.mRecommend.getDanmaku() + ""))//播放弹幕
                        .setText(R.id.tv_video_title,item.mRecommend.getTitle());//播放标题
                if (item.mRecommend.getOpen() != 0) {
                    //直播
                    helper.setText(R.id.tv_video_tag, item.mRecommend.getArea());//播放的标签
                } else {
                    //推荐
                    helper.setText(R.id.tv_video_tag, item.mRecommend.getTname());
                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
    }

    //利用Glide图片加载
    private static class GlideImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load((String) path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into(imageView);
        }
    }
}
