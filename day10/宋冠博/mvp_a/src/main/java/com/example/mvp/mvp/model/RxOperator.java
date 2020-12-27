package com.example.mvp.mvp.model;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.mvp.base.App;
import com.example.mvp.callback.RxObserverCallBack;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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