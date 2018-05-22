package com.seaword.cn.rx;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zl on 2018/5/21.
 */

public class RxUtils {

    /** 统一线程处理 */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程 统一处理线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
