package com.example.p7mvp.base;

public interface IBasePresenter<T> {
    void start();
    void start(T... t);
}
