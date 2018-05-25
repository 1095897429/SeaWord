package com.seaword.cn.base;

import com.seaword.cn.network.response.HttpResponse;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zl on 2018/5/25.
 *  主要处理以下情况 -- 返回result对应的是JsonBean
 *    {
     "code":200,
     "msg":"成功",
     "result":{}
     }
 */

public abstract class BaseObjectSubscriber<T> extends ResourceSubscriber<HttpResponse<T>> {
    private BaseContract.BaseView mView;

    public BaseObjectSubscriber(BaseContract.BaseView view) {
        this.mView = view;
    }

    public abstract void onSuccess(T t);

    @Override
    public void onNext(HttpResponse<T> response) {
        mView.complete();
        if(response.code == 0){
            if(response.data != null)
                onSuccess(response.data);
            if(response.result != null)
                onSuccess(response.result);
        }else{
            onFailure(response.code, response.message);
        }
    }

    /** 返回码错误 方法*/
    public void onFailure(int code, String message) {}

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
