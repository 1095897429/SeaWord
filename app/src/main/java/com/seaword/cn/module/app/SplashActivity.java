package com.seaword.cn.module.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding2.view.RxView;
import com.seaword.cn.di.module.ActivityModule;
import com.seaword.cn.module.home.MainActivity;
import com.seaword.cn.MyApplication;
import com.seaword.cn.R;
import com.seaword.cn.bean.app.Splash;
import com.seaword.cn.di.component.DaggerActivityComponent;
import com.seaword.cn.mvp.contract.app.SplashContract;
import com.seaword.cn.mvp.presenter.app.SplashPresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by zl on 2018/5/21.
 * 1.没有继承Base
 * 2.防止使用RxJava内存泄漏
 * 3.耦合性太高 （new SplashPresenter(this)） --- 旧的方式
 *   耦合性低 -- 依赖注入 -- 新的方式
 */

public class SplashActivity extends RxAppCompatActivity implements SplashContract.View{

    @Inject
    SplashPresenter mPresenter;
    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.tv_count_down)
    TextView mTvCountDown;
    @BindView(R.id.ll_count_down)
    LinearLayout mLlCountDown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initInject();
        initWidget();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    private void initInject() {
        /** 通过Dragger实例化P ,必须先实例AppComponent组件容器*/
        DaggerActivityComponent
                .builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
        mPresenter.attachView(this);
    }

    /** RxBinding 代替点击事件*/
    private void initWidget() {
        RxView.clicks(mLlCountDown)
                .throttleFirst(3,TimeUnit.SECONDS)//3秒内响应第一次发射数据
                .compose(this.bindToLifecycle())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        redirect();
                    }
                });
    }

    private void loadData() {
        mPresenter.getSpalshData();
        mPresenter.setCountDown();
    }

    /** ------------------ 以下是界面展示 ------------------*/
    @Override
    public void showCountDown(int count) {
        mTvCountDown.setText(count + "");
        if(count == 0){
            redirect();
        }
    }

    @Override
    public void showSplash(Splash splash) {
        if(!splash.getData().isEmpty()){//判断list中是否有元素
            int pos = new Random().nextInt(splash.getData().size());//生成一个随机的int值，该值介于[0,n)的区间
            Glide.with(this)
                    .load(splash.getData().get(pos).getThumb())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into(mIvSplash);
        }else{
            mIvSplash.setImageResource(R.mipmap.ic_default_bg);
        }
    }

    @Override
    public void complete() {

    }

    @Override
    public void showError(String msg) {

    }

    /** 自动跳转到首页 */
    private void redirect() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
