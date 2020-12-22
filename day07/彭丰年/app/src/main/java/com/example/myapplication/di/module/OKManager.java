package com.example.myapplication.di.module;

import com.example.myapplication.mvp.model.api.APiSelect;
import com.example.myapplication.mvp.model.constants.ApiConStants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class OKManager {
    @Singleton
    @Provides
    public OkHttpClient.Builder proBuilder(){
        return new OkHttpClient.Builder();
    }
    @Singleton
    @Provides
    public OkHttpClient okHttpClient(){
        return proBuilder().build();
    }
    @Singleton
    @Provides
    public Retrofit.Builder proRetroBuilder(){
        return new Retrofit.Builder();
    }
    @Singleton
    @Provides
    public Retrofit proRetfit(){
        return proRetroBuilder().baseUrl(ApiConStants.DEBUG_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    public APiSelect aPiSelect(){
        return proRetfit().create(APiSelect.class);
    }
}
