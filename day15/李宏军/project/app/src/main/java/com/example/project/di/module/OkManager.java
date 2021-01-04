package com.example.project.di.module;


import com.example.project.mvp.model.api.ApiService;
import com.example.project.mvp.model.constants.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 依赖提供方,向外界提供对象
 */
@Module//向外界提供对象的注解
public class OkManager {

    @Singleton
    @Provides
    public OkHttpClient.Builder proOkBuilder() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    public OkHttpClient proOkHttpClient() {
        return proOkBuilder().build();
    }

    @Singleton
    @Provides
    public Retrofit.Builder proRetroBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    public Retrofit proRetrofit() {
        return proRetroBuilder()
                .baseUrl(Constants.DEBUG_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //获取ApiService对象
    @Singleton  //表示单例
    @Provides
    public ApiService proApiService() {
        return proRetrofit().create(ApiService.class);
    }
}
