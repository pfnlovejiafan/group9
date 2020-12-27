package com.example.mvp.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}