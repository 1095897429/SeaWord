package com.seaword.cn.base;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zl on 2018/5/22.
 * T 代表要处理的数据泛型
 * 将P中的View传递进来，如果有问题，直接反馈给View,错误信息都在这里处理
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
