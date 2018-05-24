package com.seaword.cn.bean.recommend;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by zl on 2018/5/24.
 */

public class MulRecommend implements MultiItemEntity {

    public static final int TYPR_HEADER = 1;//第一种类型
    public static final int TYPE_ITEM = 2;//第二种类型
    public static final int HEADER_SPAN_SIZE = 2;// 占2单元格
    public static final int ITEM_SPAN_SIZE = 1;// 占1的单元格
    public int itemType;//返回几种类型
    public int spanSize;
    public Recommend mRecommend;
    public List<Recommend.BannerItemBean> mBannerItemBean;

    public MulRecommend(int itemType, int spanSize, Recommend recommend) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.mRecommend = recommend;
    }

    public MulRecommend(int itemType, int spanSize, List<Recommend.BannerItemBean> bannerItemBeans) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.mBannerItemBean = bannerItemBeans;

    }

    /** 返回有几种布局类型  */
    @Override
    public int getItemType() {
        return itemType;
    }
}
