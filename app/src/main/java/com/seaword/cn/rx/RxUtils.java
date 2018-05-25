package com.seaword.cn.rx;

import com.seaword.cn.network.exception.ApiException;
import com.seaword.cn.network.response.HttpResponse;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
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

    /** 统一返回结果处理 FlowableTransformer和compose的关系是什么*/
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleResult() {
        return new FlowableTransformer<HttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(@NonNull Flowable<HttpResponse<T>> upstream) {
                return upstream.flatMap(new Function<HttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(@NonNull HttpResponse<T> httpResponse) throws Exception {
                        if (httpResponse.code == 0) {
                            if (httpResponse.data != null)
                                return createData(httpResponse.data);
                            if (httpResponse.result != null)
                                return createData(httpResponse.result);
                            return Flowable.error(new ApiException("服务器返回error"));
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /** 生成Flowable */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
