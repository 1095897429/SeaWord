package com.seaword.cn.base;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zl on 2018/5/22.
 * T 代表要处理的数据泛型
 * 返回后台整个json字符串中的整体为的泛型 -- Splash为例
 */

public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {
    private BaseContract.BaseView mView;

    public BaseSubscriber(BaseContract.BaseView view) {
        this.mView = view;
    }

    public abstract void onSuccess(T t);

    @Override
    public void onNext(T response) {
       onSuccess(response);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
