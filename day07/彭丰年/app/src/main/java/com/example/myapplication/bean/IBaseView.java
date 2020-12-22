package com.example.myapplication.bean;

public interface IBaseView<T> {
    void ShowOk(T t);
    void ShowNo(String string);
}
