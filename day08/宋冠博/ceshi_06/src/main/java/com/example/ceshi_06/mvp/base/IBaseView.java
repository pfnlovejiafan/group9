package com.example.ceshi_06.mvp.base;

public interface IBaseView<T> {
    void onScuccess(T t);
    void onError(String msg);
}