package com.example.mvp.callback;

public interface RxCallBack<T> {
    void onSuccessData(T t);
    void onErrorMsg(String msg);
}