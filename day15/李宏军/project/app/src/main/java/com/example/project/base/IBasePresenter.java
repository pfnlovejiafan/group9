package com.example.project.base;

public interface IBasePresenter<T> {

    void start();

    //可传参数，至少一个
    void start(T... t);
}
