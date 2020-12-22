package com.example.day6_one.base;

/**
 * 更新UI的两个方法  <T>是通配符 代表任意类型的数据
 */
public interface IBaseView<T> {
    void onScuccess(T t);
    void onError(String msg);
}
