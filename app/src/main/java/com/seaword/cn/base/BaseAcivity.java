package com.seaword.cn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

/**
 * Created by zl on 2018/5/18.
 * 1.基类也是一个View
 */

public  abstract class BaseAcivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void complete() {

    }

    @Override
    public void showError(String msg) {

    }
}
