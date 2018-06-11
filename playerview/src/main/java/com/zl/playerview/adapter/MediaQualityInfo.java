package com.zl.playerview.adapter;


import com.zl.playerview.media.IjkPlayerView;

/**
 * Created by Rukey7 on 2016/10/29.
 */

public final class MediaQualityInfo {

    private int index;//默认对应几种类型
    private String desc;
    private boolean isSelect;

    public MediaQualityInfo(@IjkPlayerView.MediaQuality int index, String desc, boolean isSelect) {
        this.index = index;
        this.desc = desc;
        this.isSelect = isSelect;
    }

    public @IjkPlayerView.MediaQuality
    int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "MediaQualityInfo{" +
                "index=" + index +
                ", desc='" + desc + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
