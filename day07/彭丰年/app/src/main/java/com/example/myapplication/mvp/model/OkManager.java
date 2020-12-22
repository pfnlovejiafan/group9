package com.example.myapplication.mvp.model;

import com.example.myapplication.mvp.model.api.APiSelect;
import com.example.myapplication.mvp.model.constants.ApiConStants;

import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class OkManager {
    @Singleton
    @Provides
    public OkHttpClient.Builder proBuilder(){
        return new OkHttpClient.Builder();
    }
    @Singleton
    @Provides
    private OkHttpClient proOkhttpClient(){
        return proBuilder().build();
    }

    @Singleton
    @Provides
    public Retrofit.Builder proRetroBuilder(){
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    public Retrofit proRetrofit(){
        return proRetroBuilder().baseUrl(ApiConStants.BAIDU_LIST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    public APiSelect proApiService(){
        return proRetrofit().create(APiSelect.class);
    }
}
