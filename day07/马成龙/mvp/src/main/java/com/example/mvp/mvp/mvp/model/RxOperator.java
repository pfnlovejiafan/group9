package com.example.mvp.mvp.mvp.model;


import com.example.mvp.mvp.callback.RxObserverCallBack;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 用来封装各种操作符
 */
public class RxOperator {

    //封装了线程切换
    public static Observable threadtransformer(Observable observable) {
        return observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }

    //封装了ConcatMap操作符   网络嵌套(注册完成之后自动登录)
    public <T> ObservableSource concatMap(Observable<T> registerObservable,
                                          Observable<T> loginObservable, RxObserverCallBack<T> callBack) {
        return threadtransformer(registerObservable).
                doOnNext(obj -> {
                    callBack.onSuccessData((T) obj);
                }).observeOn(Schedulers.io()).
                concatMap(obj -> {
                    return loginObservable;
                });
    }


}