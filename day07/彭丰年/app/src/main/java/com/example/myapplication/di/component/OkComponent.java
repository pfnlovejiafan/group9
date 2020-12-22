package com.example.myapplication.di.component;

import com.example.myapplication.di.module.OKManager;
import com.example.myapplication.mvp.model.RxOpretorImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = OKManager.class)
public interface OkComponent {
    void getSingleApiServicr(RxOpretorImpl impl);
}
