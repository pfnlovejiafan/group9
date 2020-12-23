package com.example.ceshi_06.mvp.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}