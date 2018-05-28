package com.seaword.cn.adapter.home.section.live;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seaword.cn.R;
import com.seaword.cn.bean.live.LivePartition;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2018/5/28.
 * 首页直播轮播条 -- 轮播条Section的部分
 * section中需要数据
 */

public class LiveBannerSection  extends StatelessSection {

    private List<LivePartition.BannerBean> mList;

    public LiveBannerSection(List<LivePartition.BannerBean> mList){
        super(R.layout.layout_banner,R.layout.layout_empty);//并没有把list传给父类
        this.mList = mList;
    }

    /** 复写父类的方法*/
    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        Banner banner = holder.getView(R.id.banner);
        List<String> urls = new ArrayList<>();
        for (LivePartition.BannerBean bannerBean : mList) {
            urls.add(bannerBean.getImg());
        }
        banner.setIndicatorGravity(BannerConfig.RIGHT)
                .setImages(urls)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }


    static class GlideImageLoader extends ImageLoader {
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
