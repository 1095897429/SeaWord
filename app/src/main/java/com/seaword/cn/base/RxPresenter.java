package com.seaword.cn.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zl on 2018/5/21.
 * 1.管理绑定
 * 2.防止内存溢出
 * 3.T 泛型的View
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;
    private CompositeDisposable mCompositeDisposable;//Rx内置的Disposable，用于防止不在UI中的内存溢出

    public void addSubscribe(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void removeSubscribe(Disposable disposable){
        if(mCompositeDisposable != null)
            mCompositeDisposable.remove(disposable);
    }

    /** dispose() 即可切断所有的水管 */
    private void unSubscribe() {
        if(mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
    }


    /** 在BaseActivity中调用此方法 */
    @Override
    public void attachView(T view) {
        mView = view;
    }

    /** 当p和view之间分离，则取消订阅  */
    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }


}
