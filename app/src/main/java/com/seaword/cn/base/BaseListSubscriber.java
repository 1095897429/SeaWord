package com.seaword.cn.base;

import com.seaword.cn.network.response.HttpResponse;

import java.util.List;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zl on 2018/5/22.
 * T 代表要处理的数据泛型
 * 主要处理以下情况 -- 返回result -- 剥壳类似
 * {
 "code":300,
 "msg":"成功",
 "result":[]
 }
 */

public abstract class BaseListSubscriber<T> extends ResourceSubscriber<HttpResponse<List<T>>> {
    private BaseContract.BaseView mView;

    public BaseListSubscriber(BaseContract.BaseView view) {
        this.mView = view;
    }

    public abstract void onSuccess(List<T> t);


    /** 统一做处理，这里response就是返回的后台数据 */
    @Override
    public void onNext(HttpResponse<List<T>> response) {
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
