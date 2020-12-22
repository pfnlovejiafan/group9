package com.example.p7mvp.mvp.model.rxjava;

import com.example.p7mvp.callBcak.RxCallBack;


import dagger.Module;
import dagger.Provides;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<T> implements Observer<T> {

    private RxCallBack<T> callBack;

    public RxObserver(RxCallBack<T> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        callBack.getDisposable(d);
    }

    @Override
    public void onNext(Object value) {
        callBack.resultSucceed((T) value);
    }

    @Override
    public void onError(Throwable e) {
        callBack.error(e);
    }

    @Override
    public void onComplete() {

    }
}
