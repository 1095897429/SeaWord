package com.seaword.cn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.seaword.cn.adapter.home.RecommendAdapter;

/**
 * Created by zl on 2018/6/1.
 * 用于测试的界面
 */

public class TestActivity extends AppCompatActivity {

    private RecommendAdapter mAdapter;//涉及RV的适配器


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
