package com.example.p7mvp.di;

import com.example.p7mvp.mvp.model.rxjava.OkManager;
import com.example.p7mvp.mvp.model.rxjava.RxExecutor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = OkManager.class)
public interface RxComponent {
    void getSingleApiService(RxExecutor executor);
}
