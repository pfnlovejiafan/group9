package com.example.myapplication.bean;

public interface IPresenter<T> {
    void start();
    void start(T...t);
}
