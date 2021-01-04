package com.example.project.di.component;
//注射器：将提供方提供的对象注射到消费方


import com.example.project.di.module.OkManager;
import com.example.project.mvp.model.RxPoretorImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = OkManager.class)
public interface OkComponent {

    //自定义方法
    void getSingleApiService(RxPoretorImpl impl);
}
