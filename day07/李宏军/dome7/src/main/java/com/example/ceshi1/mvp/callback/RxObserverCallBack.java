package com.example.ceshi1.mvp.callback;

public interface RxObserverCallBack<T> {
    void onSuccessData(T t);
    void onErrorMsg(String msg);
}