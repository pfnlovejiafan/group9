package com.example.p7mvp.base;

public interface IBaseView<T> {
    void onSuccess(T t);
    void onError(String msg);
}
