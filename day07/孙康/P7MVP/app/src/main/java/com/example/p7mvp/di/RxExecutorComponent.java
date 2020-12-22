package com.example.p7mvp.di;

import com.example.p7mvp.mvp.model.Model;
import com.example.p7mvp.mvp.model.rxjava.RxExecutor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RxExecutor.class)
public interface RxExecutorComponent {
    void getExecutor(Model model);
}
