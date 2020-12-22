package com.example.myapplication.mvp.model;

import com.example.myapplication.calback.RxObserverCallBack;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<T> implements Observer {
    private RxObserverCallBack<T> callBack;
    protected Disposable mDisPosable;

    public RxObserver(RxObserverCallBack<T> callBack) {
        this.callBack = callBack;
    }

    public RxObserver(Disposable mDisPosable) {
        this.mDisPosable = mDisPosable;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Object o) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
