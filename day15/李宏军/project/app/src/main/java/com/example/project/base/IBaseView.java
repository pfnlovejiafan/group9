package com.example.project.base;

public interface IBaseView<T> {
    void onSucceed(T t);

    void onFiled(String errorMsg);
}
