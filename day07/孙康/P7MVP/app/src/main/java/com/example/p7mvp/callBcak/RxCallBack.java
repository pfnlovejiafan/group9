package com.example.p7mvp.callBcak;


import io.reactivex.disposables.Disposable;

public interface RxCallBack<T> {
    void getDisposable(Disposable disposable);
    void resultSucceed(T t);
    void error(Throwable error);
}
