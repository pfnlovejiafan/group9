package com.example.ceshi1.mvp.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}