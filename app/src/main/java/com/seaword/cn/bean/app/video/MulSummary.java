package com.seaword.cn.bean.app.video;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by zl on 2018/5/31.
 * 一般在构造时设定itemType
 */

public class MulSummary implements MultiItemEntity {

    public static final int TYPE_DES = 1;//描述
    public static final int TYPE_OWNER = 2;//个人信息
    public static final int TYPE_RELATE_HEAD = 3;//相关视频头部
    public static final int TYPE_RELATE = 4;//相关视频

    public int itemType;
    public String desc;//描述
    public String title;//标题
    public long ctime;//时间
    public List<VideoDetail.DataBean.TagBean> tags;//TODO 标签还未添加
    public VideoDetail.DataBean.StatBean stat;//硬币，分享 播放相关
    public VideoDetail.DataBean.RelatesBean relates;//视频推荐
    public VideoDetail.DataBean.OwnerBean owner;//作者信息

    @Override
    public int getItemType() {
        return itemType;
    }

    public MulSummary setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    public MulSummary setRelates(VideoDetail.DataBean.RelatesBean relates){
        this.relates = relates;
        return this;
    }

    public MulSummary setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public MulSummary setTitle(String title) {
        this.title = title;
        return this;
    }

    public MulSummary setTags(List<VideoDetail.DataBean.TagBean> tags) {
        this.tags = tags;
        return this;
    }

    public MulSummary setStat(VideoDetail.DataBean.StatBean stat) {
        this.stat = stat;
        return this;
    }

    public MulSummary setCtime(long ctime) {
        this.ctime = ctime;
        return this;
    }

    public MulSummary setOwner(VideoDetail.DataBean.OwnerBean owner) {
        this.owner = owner;
        return this;
    }

}
