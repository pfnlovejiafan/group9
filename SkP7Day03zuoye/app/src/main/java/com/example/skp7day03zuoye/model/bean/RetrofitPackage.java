package com.example.skp7day03zuoye.model.bean;

import com.example.skp7day03zuoye.model.ApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPackage {
    private static volatile RetrofitPackage retrofitPackage;
    public final ApiService apiService;


    private RetrofitPackage() {
        apiService = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.BASE_URL).build().create(ApiService.class);

    }

    public static synchronized RetrofitPackage getRetrofitPackage(){
        if (retrofitPackage  == null){
            synchronized (RetrofitPackage.class){
                if (retrofitPackage == null){
                    retrofitPackage = new RetrofitPackage();
                }
            }
        }
        return retrofitPackage;
    }



}
