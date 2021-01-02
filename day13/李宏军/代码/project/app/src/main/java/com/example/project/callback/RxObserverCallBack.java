package com.example.project.callback;

import io.reactivex.disposables.Disposable;

public interface RxObserverCallBack<T> {
    void onSucceed(T t);

    void onFiled(String errorMsg);

    void onSubscribe(Disposable disposable);
}