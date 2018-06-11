package com.zl.playerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zl.playerview.R;

import java.util.List;

/**
 * Created by Rukey7 on 2016/10/29.
 */

public class AdapterMediaQuality extends BaseListAdapter<MediaQualityInfo> {


    public AdapterMediaQuality(Context context, List<MediaQualityInfo> datas) {
        super(context, datas);
    }

    public AdapterMediaQuality(Context context) {
        super(context);
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_media_quality, parent, false);
        }
        TextView qualityDesc = (TextView) view.findViewById(R.id.tv_media_quality);
        qualityDesc.setText(mDatas.get(i).getDesc());
        qualityDesc.setSelected(mDatas.get(i).isSelect());
        return view;
    }

    //默认都为没有选中
    private void _cleanSelected() {
        for (MediaQualityInfo info : mDatas) {
            if (info.isSelect()) {
                info.setSelect(false);
            }
        }
    }

    public void setMediaQuality(int quality) {
        for (MediaQualityInfo info : mDatas) {
            if (info.getIndex() == quality) {
                if (!info.isSelect()) {
                    _cleanSelected();
                    //设置点击的为选中状态
                    info.setSelect(true);
                    //更新视图
                    notifyDataSetChanged();
                }
                return;
            }
        }
    }
}
