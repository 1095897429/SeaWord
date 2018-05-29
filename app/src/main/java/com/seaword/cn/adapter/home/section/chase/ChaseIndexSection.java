package com.seaword.cn.adapter.home.section.chase;

import android.content.Intent;
import android.view.View;

import com.seaword.cn.R;
import com.seaword.cn.module.app.SplashActivity;
import com.seaword.cn.widget.section.StatelessSection;
import com.seaword.cn.widget.section.ViewHolder;

import java.util.List;

/**
 * Created by zl on 2018/5/29.
 */

public class ChaseIndexSection extends StatelessSection {


    public ChaseIndexSection() {
        super(R.layout.layout_item_home_chase_bangumi_index, R.layout.layout_empty);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.getView(R.id.ll_bangumi_timeline)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        holder.getView(R.id.ll_bangumi_index)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
    }
}
