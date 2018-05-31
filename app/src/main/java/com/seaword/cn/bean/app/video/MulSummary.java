package com.seaword.cn.bean.app.video;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zl on 2018/5/31.
 */

public class MulSummary implements MultiItemEntity {

    public static final int TYPE_DES = 34;//描述
    public static final int TYPE_OWNER = 35;//个人信息
    public static final int TYPE_RELATE = 36;//相关视频
    public static final int TYPE_RELATE_HEAD = 37;//相关视频头部

    public int itemType;
    public String desc;//描述
    public String title;//标题

    @Override
    public int getItemType() {
        return itemType;
    }
}
