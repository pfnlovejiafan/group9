package com.example.day6_one.base;

/**
 * 这是P层
 * T... t中的三个点 代表可变参数
 * 同名参数的参数列表、个数、类型不一样、就是方法重载
 */
public interface IPresenter<T>{
    //P层共有的方法 比如start

    //  有的不需要传参数
    void start();
    //有的需传参数
    void start(T... t);
}
