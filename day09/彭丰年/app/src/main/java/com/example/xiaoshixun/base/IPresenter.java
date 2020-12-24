package com.example.xiaoshixun.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}