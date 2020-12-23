package com.example.ceshi_06.mvp.di.component;


import com.example.ceshi_06.mvp.mvp.model.OkManager;
import com.example.ceshi_06.mvp.mvp.model.RxOpretorImpl;

import javax.inject.Singleton;

import dagger.Component;

//注射器
@Singleton
@Component(modules = OkManager.class)
public interface OkComponent {
    //自定义的方法
    void getSingleApiService(RxOpretorImpl impl);
}